define(function(require, exports, module) {
	var timeGroup = {};
	/*返回timeGroup的length*/
	var timeGroupLength = function() {
		var length = 0;
		for (var i in timeGroup) {
			length++
		}
		return length;
	};
	/*获取现在的系统时间*/
	var newGetTime = function() {
		var date = new Date();
		return date.getTime();
	};
	/*添加一个计时事件*/
	var add = function(id, date, callback) {
		if (typeof id === "string" && typeof callback === "function") {
			if (typeof timeGroup[id] !== "object") {
				if (typeof date === "string") {
					date = Date.parse(date);
				} else if (typeof date === "number") {
					date = newGetTime() + date;
				} else {
					return false;
				}
				timeGroup[id] = {
					pause: false,
					id: id,
					date: date,
					differenceAll: 0,
					differenceSingle: 0,
					callback: callback
				};
				if (timeGroupLength() && !runClock.startUp()) runClock.set();
				return true;
			}
		}
		return false;
	};
	/*删除一个计时事件*/
	var remove = function(id) {
		if (typeof timeGroup[id] !== "undefined") {
			delete timeGroup[id];
			if (!timeGroupLength() && runClock.startUp()) runClock.clear();
			return true;
		}
		return false;
	};
	/*暂停一个计时事件*/
	var set = function(id) {
		if (typeof timeGroup[id] === "object") {
			timeGroup[id].pause = false;
			timeGroup[id].differenceAll += timeGroup[id].differenceSingle;
			return true;
		}
		return false;
	};
	/*启用一个计时事件*/
	var clear = function(id) {
		if (typeof timeGroup[id] === "object") {
			timeGroup[id].pause = newGetTime();
			msec(id);
			return true;
		}
		return false;
	};
	/*计算时差，并向回调函数传输毫秒计时*/
	var msec = function(id) {
		var nowDate = newGetTime();
		if (typeof id !== "undefined") {
			timeGroup[id].callback(nowDate - timeGroup[id].differenceAll - timeGroup[id].date);
		} else {
			for (var i in timeGroup) {
				if (!timeGroup[i].pause) {
					timeGroup[i].callback(nowDate - timeGroup[i].differenceAll - timeGroup[i].date);
				} else {
					timeGroup[i].differenceSingle = nowDate - timeGroup[i].pause;
				}
			}
		}
	};
	/*每500毫秒计算一次数据*/
	var runClock = function() {
		var time, startUp = false;
		var hasStartUp = function() {
			return startUp;
		};
		var setClock = function() {
			if (startUp) clearInterval(time);
			time = setInterval(function() {
				msec();
			}, 100);
			startUp = true;
		};
		var clearClock = function() {
			clearInterval(time);
			startUp = false;
		};
		return {
			startUp: hasStartUp,
			set: setClock,
			clear: clearClock
		};
	}();
	/*毫秒转日期*/
	var format = function() {
		var formMsec = {
			day: 86400000,
			hour: 3600000,
			minute: 60000,
			second: 1000
		};
		return function(msec, form, callback) {
			if (typeof msec === "number" && typeof form === "object" && typeof callback === "function") {
				var formDate = {};
				msec = Math.abs(msec);
				for (var i in formMsec) {
					if (form.indexOf(i) >= 0) {
						formDate[i] = Math.floor(msec / formMsec[i]);
						msec = msec % formMsec[i];
					}
				}
				callback(formDate);
				return true;
			}
			return false;
		};
	}();
	return function() {
		return {
			add: add,
			remove: remove,
			set: set,
			clear: clear,
			format: format
		}
	}
});