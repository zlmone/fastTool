(function(seajs) {
	var options = {
		// 路径配置
		paths: {

		},
		// 别名配置
		alias: {
			'cityData': 'sea_module/cityData.js',
			'fieldText': 'sea_module/fieldText.js',
			'fieldInput': 'sea_module/fieldInput.js',
			'pickerSelect': 'sea_module/pickerSelect.js',
			'pickerAge': 'sea_module/pickerAge.js',
			'pickerAddress': 'sea_module/pickerAddress.js',
			'timing': 'sea_module/timing.js',
			'countdown': 'sea_module/countdown.js',
			'checkbox': 'sea_module/checkbox.js',
			'uploadPic': 'sea_module/uploadPic.js',
			'zepto': 'sea_module/zepto.js',
			'zeptoEvent': 'sea_module/zepto.event.js',
			'zeptoAjax': 'sea_module/zepto.ajax.js'
		},
		// 预加载项
		preload: []
	};
	seajs.config(options);
})(seajs);