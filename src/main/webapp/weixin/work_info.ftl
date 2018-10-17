<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1, user-scalable=no">
	<title>工作信息</title>
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
		<field-input label="单位名称" type="name" v-model="name" placeholder="请输入"></field-input>
		<div class="hr"></div>
		<field-input label="单位地址" type="text" v-model="address" placeholder="请输入"></field-input>
		<div class="hr"></div>
		<picker-select label="公司规模" v-model="size.value" v-bind:options="size.options"></picker-select>
		<div class="hr"></div>
		<picker-select label="公司性质" v-model="type.value" v-bind:options="type.options"></picker-select>
		<div class="cut"></div>
		<picker-age label="工作年限" v-model="jobTime" placeholder="将根据开始工作年月计算" unit="年"></picker-age>
		<div class="hr"></div>
		<field-input label="部门" type="name" v-model="division" placeholder="您所在部门"></field-input>
		<div class="hr"></div>
		<field-input label="职位" type="name" v-model="job" placeholder="您的职位"></field-input>
		<div class="hr"></div>
		<field-input label="月收入" type="money" v-model="money" placeholder="请输入"></field-input>
		<div class="hr"></div>
		<field-input label="单位座机" type="number" maxlength="12" v-model="tell" placeholder="请输入"></field-input>
		<div class="hr"></div>
		<picker-age label="入职时间" v-model="jobStart" type="date" placeholder="将根据入职年月计算"></picker-age>
		<div class="cut bottom"></div>
		<div class="submit-btn-wrap bottom">
			<mt-button v-on:click="onSubmit">继续申请</mt-button>
		</div>
	</div>
	<script>
		(function(Vue, seajs){
			seajs.use(["pickerSelect", "pickerAge", "fieldInput","zeptoAjax"], function(pickerSelect, pickerAge, fieldInput,$){
				Vue.component(pickerAge.name, pickerAge);
				Vue.component(pickerSelect.name, pickerSelect);
				Vue.component(fieldInput.name,fieldInput);
				var app = new Vue({
					el: "#app",
					data: {
						name: "",
						address: "",
						jobTime: "",
						division: "",
						job: "",
						money: "",
						tell: "",
						jobStart: "",
						size: {
							value: "0-20人",
							options: [{
								values: ["0-20人","20-50人","50-100人","100-200人","200人以上"]
							}]
						},
						type: {
							value: "私有企业",
							options: [{
								values: ["国有企业","股份企业","私有企业"]
							}]
						}
					},
					methods: {
						validate: function(){
							if(!this.name.length){
								this.$toast('请填写单位名称!');
								return false
							}else if(!this.address.length){
								this.$toast('请填写单位地址!');
								return false
							}else if(!this.jobTime.length){
								this.$toast('请填写工作年限!');
								return false
							}else if(!this.division.length){
								this.$toast('请填写您所在部门!');
								return false
							}else if(!this.job.length){
								this.$toast('请填写您的职位!');
								return false
							}else if(!this.money.length){
								this.$toast('请填写月收入!');
								return false
							}else if(this.tell.length < 8){
								this.$toast('座机号应该大于等于8位数!');
								return false
							}else if(!this.jobStart.length){
								this.$toast('请填写入职时间!');
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
								url:"${rc.contextPath}/ngres/dingdanguanli/order/add11?id="+id,
								dataType:"json",
								contentType:"application/json;charset=UTF-8",
								processData:false,
							 success:function(data){
								  var data1 = eval(data);
								  console.log(data1);
								  if(data.status == "success"){
								   window.location.href="${rc.contextPath}/ngres/dingdanguanli/order/tiaozhuan3?id="+data1.id;
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