<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1, user-scalable=no">
	<title>个人信息</title>
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
		<div class="cut"></div>
		<field-input label="姓名" type="name" v-model="name" placeholder="请输入"></field-input>
		<div class="hr"></div>
		<picker-select label="性别" v-model="sex.value" v-bind:options="sex.options"></picker-select>
		<div class="hr"></div>
		<picker-age label="年龄" v-model="age" placeholder="将根据出身年月计算"></picker-age>
		<div class="hr"></div>
		<field-input label="身份证号" type="idNumber" maxlength="18" v-model="idcard" placeholder="请输入"></field-input>
		<div class="hr"></div>
		<field-input label="手机" type="phone" v-model="phone" placeholder="请输入"></field-input>
		<div class="hr"></div>
		<field-input label="邮箱帐号" type="email" v-model="email" placeholder="请输入"></field-input>
		<div class="hr"></div>
		<picker-select label="婚姻状况" v-model="marriage.value" v-bind:options="marriage.options"></picker-select>
		<div class="hr"></div>
		<picker-select label="最高学历" v-model="education.value" v-bind:options="education.options"></picker-select>
		<div class="hr"></div>
		<picker-address label="户口所在地" v-model="birth" accuracy="3"></picker-address>
		<div class="hr"></div>
		<field-input label="现居住地址" type="text" v-model="address" placeholder="您的现居住地址"></field-input>
		<div class="hr"></div>
		<picker-age label="来本市年份" v-model="live" placeholder="将根据来本市年月计算" unit="年"></picker-age>
		<div class="cut"></div>
		<field-input label="银行卡号" type="card" maxlength="19" v-model="bank.id" placeholder="请输入"></field-input>
		<div class="hr"></div>
		<picker-select label="开户银行" v-model="bank.name" v-bind:options="bank.options"></picker-select>
		<div class="hr"></div>
		<field-input label="开户行" type="text" v-model="bank.address" placeholder="请输入"></field-input>
		<div class="cut bottom"></div>
		<div class="submit-btn-wrap bottom">
			<mt-button v-on:click="onSubmit">继续申请</mt-button>
		</div>
	</div>
	<script>
		(function(Vue, seajs){
			seajs.use(["pickerSelect", "pickerAge", "pickerAddress", "fieldInput","zeptoAjax"], function(pickerSelect, pickerAge, pickerAddress, fieldInput,$){
				Vue.component(pickerAge.name, pickerAge);
				Vue.component(pickerSelect.name, pickerSelect);
				Vue.component(pickerAddress.name, pickerAddress);
				Vue.component(fieldInput.name,fieldInput);
				var app = new Vue({
					el: "#app",
					data: {
						name: "",
						age: "",
						idcard: "",
						phone: "",
						email: "",
						birth: "",
						address: "",
						live: "",
						sex: {
							value: "男",
							options: [{
								values: ["男","女"]
							}]
						},
						marriage: {
							value: "未婚",
							options: [{
								values: ["未婚","已婚","离婚"]
							}]
						},
						education: {
							value: "文盲",
							options: [{
								values: ["文盲","小学","初中","职高","高中","大专","本科","硕士","博士"]
							}]
						},
						bank: {
							id: "",
							name: "",
							address: "",
							options: [{
								values: ["中国建设银行1","中国建设银行2","中国建设银行3"]
							}]
						}
					},
					methods: {
						validate: function(){
							/*var bankId = this.bank.id.replace(" ","");
							if(!this.name.length){
								this.$toast('请填写姓名!');
								return false
							}else if(!this.age.length){
								this.$toast('请填写年龄!');
								return false
							}else if(!(this.idcard.length === 15 || this.idcard.length === 18)){
								this.$toast('身份证号长度要等于15或18位!');
								return false
							}else if(this.phone.length !== 11){
								this.$toast('手机号长度要等于11位!');
								return false
							}else if(!/^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\.[a-zA-Z0-9-]+)*\.[a-zA-Z0-9]{2,6}$/.test(this.email)){
								this.$toast('邮箱帐号格式不正确!');
								return false
							}else if(!this.address.length){
								this.$toast('请填写您的现居住地址!');
								return false
							}else if(!this.live.length){
								this.$toast('请填写来本市年份!');
								return false
							}else if(bankId.length < 16){
								this.$toast('银行卡号长度要大于16位!');
								return false
							}else if(!this.bank.address.length){
								this.$toast('请填写开户行!');
								return false
							}
							this.bank.id = bankId;*/
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
								data:data1,
								url:"${rc.contextPath}/ngres/dingdanguanli/order/add1?id="+id,
								dataType:"json",
								contentType:"application/json;charset=UTF-8",
								processData:false,
								success:function(data1){
								  var data2 = eval(data1);
								  console.log(data2);
								  if(data1.status == "success"){
								   window.location.href="${rc.contextPath}/ngres/dingdanguanli/order/tiaozhuan2?id="+data2.id;
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