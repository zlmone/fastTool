define(function(require, exports, module) {
	var timing = require('timing');
	var timeObject = new timing();
	var btnEl, btnTagName;
	var set = function(btn, callback, time) {
		btnEl = btn;
		btnTagName = btnEl ? btnEl.tagName.toLocaleLowerCase() : '';
		time = (time || 61) * 1000;
		var dataText = btnEl.getAttribute('data-text');
		if (!(typeof dataText === 'string' && dataText.length)) {
			btnEl.className += ' timing-style';
			typeof callback === 'function' && callback.call(btnEl);
			btnTagName === 'input' ? btnEl.setAttribute('data-text', btnEl.value) : btnEl.setAttribute('data-text', btnEl.innerText);
			timeObject.add('validate', time, function(sec) {
				sec <= 0 ? timeObject.format(sec, ['second'], function(date) {
					var val = date.second + ' 秒';
					btnTagName === 'input' ? (btnEl.value = val) : (btnEl.innerText = val);
				}) : clear();
			});
		}
	};
	var clear = function() {
		if (btnEl) {
			timeObject.remove('validate');
			btnTagName === 'input' ? (btnEl.value = btnEl.getAttribute('data-text')) : (btnEl.innerText = btnEl.getAttribute('data-text'));
			btnEl.removeAttribute('data-text');
			btnEl.className = btnEl.className.replace(' timing-style', '');
		}
	};
	return {
		set: set,
		clear: clear
	}
});