<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1, user-scalable=no">
	<title>申请成功</title>
	<link rel="stylesheet" href="${rc.contextPath}/weixin/js/mint-ui/mint-ui.css">
	<link rel="stylesheet" href="${rc.contextPath}/weixin/css/style.css">
	<script src="${rc.contextPath}/weixin/js/vue.min.js"></script>
	<script src="${rc.contextPath}/weixin/js/mint-ui/mint-ui.js"></script>
	<script src="${rc.contextPath}/weixin/js/sea.min.js"></script>
	<script src="${rc.contextPath}/weixin/js/sea.config.js"></script>
</head>
<body>
	<div id="app" v-cloak>
		<div class="submit-state-wrap">
			<img src="img/submit_state_ico.jpg">
			<p>转入申请提交成功</p>
			<p>请耐心等待<span><span>...</span></span></p>
		</div>
		<div class="submit-btn-wrap">
			<mt-button v-on:click="onSubmit">查看我的订单</mt-button>
		</div>
	</div>
	<script>
		(function(Vue, seajs){
			seajs.use([], function(){
				var app = new Vue({
					el: "#app",
					methods: {
						onSubmit: function(){
							location.href = "#";
						}
					}
				});
			});
		})(Vue, seajs);
	</script>
</body>
</html>