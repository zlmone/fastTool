<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1, user-scalable=no">
	<title>注册</title>
	<link rel="stylesheet" href="${rc.contextPath}/weixin/js/mint-ui/mint-ui.css">
	<link rel="stylesheet" href="${rc.contextPath}/weixin/css/style.css">
	<script src="${rc.contextPath}/weixin/js/vue.min.js"></script>
	<script src="${rc.contextPath}/weixin/js/mint-ui/mint-ui.js"></script>
	<script src="${rc.contextPath}/weixin/js/sea.min.js"></script>
	<script src="${rc.contextPath}/weixin/js/sea.config.js"></script>
</head>
<body>
	<div id="app" class="bg-gray-shallow" v-cloak>
		<div class="register-head">
			<img src="img/register_head.jpg" alt="">
			<img src="img/register_head_phone.png" alt="">
			<img src="img/register_head_title.png" alt="">
			<img src="img/register_head_bottom.png" alt="">
		</div>
		<div class="register-main bg-gray-shallow">
			<field-input type="phone" placeholder="请输入手机号码" v-model="phone"></field-input>
			<field-input type="text" placeholder="短信验证码" v-model="phoneValidate">
				<button class="get-validate-btn" v-on:click="getValidate">获取验证码</button>
			</field-input>
			<mt-field type="password" placeholder="登录密码，6-20字符" v-model="password"></mt-field>
			<div class="affix-wrap">
				<checkbox-btn title="阅读并同意" v-model="affix"></checkbox-btn>
				<a href="#">《服务条款协议》</a>
			</div>
			<mt-button class="submit-btn" v-on:click="onSubmit">立即注册</mt-button>
		</div>
	</div>
	<script>
		(function(Vue, seajs){
			seajs.use(["fieldInput", "countdown", "checkbox","zeptoAjax"], function(fieldInput, countdown, checkbox,$){
				Vue.component(fieldInput.name, fieldInput);
				Vue.component(checkbox.name, checkbox);
				var app = new Vue({
					el: "#app",
					data: {
						phone: "",
						phoneValidate: "",
						password: "",
						affix: true
					},
					methods: {
						validate: function(){
							if(this.phone.length !== 11){
								this.$toast('手机号长度要等于11位!');
								return false
							}else if(!this.phoneValidate.length){
								this.$toast('请填写短信验证码!');
								return false
							}else if(!this.password.length){
								this.$toast('请填写登录密码!');
								return false
							}else if(!this.affix){
								this.$toast('请确认服务条款协议!');
								return false
							}
							return true
						},
						getValidate: function(e){
							//获取验证码开始计时
							countdown.set(e.target);
						},
						onSubmit: function(){
							if(!this.validate()) return
							$.ajax({
								type:"post",
								data:JSON.stringify(this.$data),
								url:"${rc.contextPath}/ngres/dingdanguanli/order/register",
								dataType:"json",
								contentType:"application/json;charset=UTF-8",
								processData:false,
						   /*		success:function(data){
								  var data1 = eval(data);
								  console.log(data1);
								  if(data.status == "success"){
								   window.location.href="${rc.contextPath}/ngres/dingdanguanli/order/tiaozhuan1?id="+data1.id;
								  }
									
								}   */
							})
							this.$indicator.open();
							//提交
							console.log(this.$data);
						}
					}
				});
			});
		})(Vue, seajs);
	</script>
</body>
</html>