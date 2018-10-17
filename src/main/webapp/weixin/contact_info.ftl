<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1, user-scalable=no">
	<title>联系人信息</title>
	<link rel="stylesheet" href="${rc.contextPath}/weixin/js/mint-ui/mint-ui.css">
	<link rel="stylesheet" href="${rc.contextPath}/weixin/css/style.css">
	<script src="${rc.contextPath}/weixin/js/vue.min.js"></script>
	<script src="${rc.contextPath}/weixin/js/mint-ui/mint-ui.js"></script>
	<script src="${rc.contextPath}/weixin/js/sea.min.js"></script>
	<script src="${rc.contextPath}/weixin/js/sea.config.js"></script>
</head>
<body>
	<div id="app" class="bg-gray" v-cloak>
	    <input type="hidden" id="orderid" value="${RequestParameters['id']}"/>
		<div class="list-label">配偶</div>
		<field-input label="姓名" type="name" v-model="spouse.name" placeholder="请输入"></field-input>
		<div class="hr"></div>
		<field-input label="联系方式" type="number" maxlength="12" v-model="spouse.phone" placeholder="请输入"></field-input>
		<div class="list-label">亲属</div>
		<field-input label="姓名" type="name" v-model="family.name" placeholder="请输入"></field-input>
		<div class="hr"></div>
		<field-input label="联系方式" type="number" maxlength="12" v-model="family.phone" placeholder="请输入"></field-input>
		<div class="list-label">同事</div>
		<field-input label="姓名" type="name" v-model="colleague.name" placeholder="请输入"></field-input>
		<div class="hr"></div>
		<field-input label="联系方式" type="number" maxlength="12" v-model="colleague.phone" placeholder="请输入"></field-input>
		<div class="list-label">朋友</div>
		<field-input label="姓名" type="name" v-model="friend.name" placeholder="请输入"></field-input>
		<div class="hr"></div>
		<field-input label="联系方式" type="number" maxlength="12" v-model="friend.phone" placeholder="请输入"></field-input>
		<div class="cut bottom"></div>
		<div class="submit-btn-wrap bottom">
			<mt-button v-on:click="onSubmit">继续申请</mt-button>
		</div>
	</div>
	<script>
		(function(Vue, seajs){
			seajs.use(["fieldInput","zeptoAjax"], function(fieldInput,$){
				Vue.component(fieldInput.name,fieldInput);
				var app = new Vue({
					el: "#app",
					data: {
						spouse: {
							name: "",
							phone: ""
						},
						family: {
							name: "",
							phone: ""
						},
						colleague: {
							name: "",
							phone: ""
						},
						friend: {
							name: "",
							phone: ""
						}
					},
					methods: {
						validate: function(){
							if(!this.spouse.name.length){
								this.$toast('请填写配偶姓名!');
								return false
							}else if(this.spouse.phone.length < 8){
								this.$toast('座机号应该大于等于8位数!');
								return false
							}else if(!this.family.name.length){
								this.$toast('请填写亲属姓名!');
								return false
							}else if(this.family.phone.length < 8){
								this.$toast('座机号应该大于等于8位数!');
								return false
							}else if(!this.colleague.name.length){
								this.$toast('请填写同事姓名!');
								return false
							}else if(this.colleague.phone.length < 8){
								this.$toast('座机号应该大于等于8位数!');
								return false
							}else if(!this.friend.name.length){
								this.$toast('请填写朋友姓名!');
								return false
							}else if(this.friend.phone.length < 8){
								this.$toast('座机号应该大于等于8位数!');
								return false
							}
							return true
						},
						onSubmit: function(){
							if(!this.validate()) return
							var data1  ={};
							data1 = JSON.stringify(this.$data);
							var id = document.getElementById("orderid").value;
							console.log(id);
							data1.id = id;
							console.log(data1);
							$.ajax({
								type:"post",
								data:JSON.stringify(this.$data),
								url:"${rc.contextPath}/ngres/dingdanguanli/order/add111?id="+id,
								dataType:"json",
								contentType:"application/json;charset=UTF-8",
								processData:false,
							success:function(data){
								  var data1 = eval(data);
								  console.log(data1);
								  if(data.status == "success"){
								   window.location.href="${rc.contextPath}/ngres/dingdanguanli/order/tiaozhuan4?id="+data1.id;
								  }
							 } 
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