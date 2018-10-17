<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1, user-scalable=no">
	<title>借款信息</title>
	<link rel="stylesheet" href="${rc.contextPath}/weixin/js/mint-ui/mint-ui.css">
	<link rel="stylesheet" href="${rc.contextPath}/weixin/css/style.css">
	<script src="${rc.contextPath}/weixin/js/vue.min.js"></script>
	<script src="${rc.contextPath}/weixin/js/mint-ui/mint-ui.js"></script>
	<script src="${rc.contextPath}/weixin/js/sea.min.js"></script>
	<script src="${rc.contextPath}/weixin/js/sea.config.js"></script>
</head>
<body>
	<div id="app" class="bg-gray" v-cloak>
		<div class="cut"></div>
		<ul class="superiority flex-center">
			<li>
				<img src="${rc.contextPath}/weixin/img/superiority_ico_03.jpg">
				<p>月费率低至<span>0.78%</span></p>
			</li>
			<li>
				<img src="${rc.contextPath}/weixin/img/superiority_ico_02.jpg">
				<p>可快至<span>30分钟</span>到账</p>
			</li>
			<li>
				<img src="${rc.contextPath}/weixin/img/superiority_ico_01.jpg">
				<p>审批<span>通过率高</span></p>
			</li>
		</ul>
		<div class="cut"></div>
		<field-input label="商品类型" type="name" v-model="goodsmodel" placeholder="请输入"></field-input>
		<div class="hr"></div>
		<field-input label="商品名称" v-model="goodsname" placeholder="请输入"></field-input>
		<div class="hr"></div>
		<field-input label="商品售价" type="money" v-model="goodsprice" v-on:blur="moneyStartBlur" placeholder="请输入" unit="元"></field-input>
		<div class="hr"></div>
		<field-input label="首付金额" type="money" v-model="firstpay" v-on:blur="moneyStartBlur" placeholder="请输入" unit="元"></field-input>
		<div class="hr"></div>
		<field-input label="销售备注" v-model="seller_note" placeholder="请输入"></field-input>
		<div class="hr"></div>
		<field-input label="销售代码" v-model="sellercode" placeholder="请输入"></field-input>
		<div class="hr"></div>
		<picker-select label="借款期限" v-model="monthnumber.value" v-bind:options="monthnumber.options"></picker-select>
		<div class="hr"></div>
		<field-input label="服务网点" type="name" v-model="merchant" placeholder="请输入"></field-input>
		<div class="hr"></div>
		<field-text label="开始计算" title="每月应还：" v-bind:value="getMoneyMonth"></field-text>
		<div class="cut bottom"></div>
		<div class="submit-btn-wrap bottom">
			<mt-button v-on:click="onSubmit">立即申请</mt-button>
		</div>
	</div>
	<script>
		(function(Vue, seajs){
			seajs.use(["fieldText", "fieldInput", "pickerSelect","zeptoAjax"], function(fieldText, fieldInput, pickerSelect,$){
				Vue.component(fieldText.name,fieldText);
				Vue.component(fieldInput.name,fieldInput);
				Vue.component(pickerSelect.name,pickerSelect);
				var app = new Vue({
					el: "#app",
					data: {
						goodsmodel: "",
						goodsname: "",
						goodsprice: 0,
						firstpay: 0,
						seller_note: "",
						sellercode: "",
						monthnumber: {
							value: "12个月",
							options: [{
								values: ["12个月","6个月","3个月"]
							}]
						},
						merchant: "",
						moneyMonth: ""
					},
					computed: {
						getMoneyMonth: function(){
							this.moneyMonth = Math.round((parseFloat(this.goodsprice) - parseFloat(this.firstpay)) / parseInt(this.monthnumber.value) * 100) / 100;
							return this.moneyMonth >=0 ? this.moneyMonth : 0;
						}
					},
					methods: {
						moneyStartBlur: function(e){
							if(parseFloat(this.firstpay) > parseFloat(this.goodsprice)){
								this.$toast('首付金额应小于等于商品售价!');
							}
						},
						validate: function(){
							if(!this.goodsmodel.length){
								this.$toast('请填写商品类型!');
								return false
							}else if(!this.goodsname.length){
								this.$toast('请填写商品名称!');
								return false
							}else if(parseFloat(this.goodsprice) <= 0){
								this.$toast('请填写商品售价!');
								return false
							}else if(parseFloat(this.firstpay) <= 0){
								this.$toast('请填写首付金额!');
								return false
							}else if(parseFloat(this.moneyStart) > parseFloat(this.moneyAll)){
								this.$toast('首付金额应小于等于商品售价!');
								return false
							}else if(!this.seller_note.length){
								this.$toast('请填写销售备注!');
								return false
							}else if(!this.sellercode.length){
								this.$toast('请填写销售代码!');
								return false
							}else if(!this.merchant.length){
								this.$toast('请填写服务网点!');
								return false
							}
							return true
						},
					onSubmit: function(){
							if(!this.validate()) return
							$.ajax({
								type:"post",
								data:JSON.stringify(this.$data),
								url:"${rc.contextPath}/ngres/dingdanguanli/order/add2",
								dataType:"json",
								contentType:"application/json;charset=UTF-8",
								processData:false,
								success:function(data){
								  var data1 = eval(data);
								  console.log(data1);
								  if(data.status == "success"){
								   window.location.href="${rc.contextPath}/ngres/dingdanguanli/order/tiaozhuan1?id="+data1.id;
								  }
									
								}
							})
							/* this.$indicator.open();
							//提交
							console.log(this.$data); */
						}
					}
				});
			});
		})(Vue, seajs);
	</script>
</body>
</html>