<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1, user-scalable=no">
	<title>上传资料</title>
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
		<div class="list-label">请分别上传身份正面和反面照片</div>
		<div class="upload-data-wrap">
			<ul class="idcard flex-center">
				<li v-bind:style="idcardLeft.length ? 'background: transparent' : ''">
					<p>上传身份证正面</p>
					<label>
						<input type="file" accept="image/jpeg,image/jpg,image/png" v-on:change="onFileChange" data-target="idcardLeft">
						<img v-if="idcardLeft.length" v-bind:src="idcardLeft">
					</label>
				</li>
				<li v-bind:style="idcardRight.length ? 'background: transparent' : ''">
					<p>上传身份证反面</p>
					<label>
						<input type="file" accept="image/jpeg,image/jpg,image/png" v-on:change="onFileChange" data-target="idcardRight">
						<img v-if="idcardRight.length" v-bind:src="idcardRight">
					</label>
				</li>
			</ul>
			<ul class="oneself flex-center">
				<li v-bind:style="oneselfLeft.length ? 'background: transparent' : ''">
					<p>本人手持身份证照片</p>
					<label>
						<input type="file" accept="image/jpeg,image/jpg,image/png" v-on:change="onFileChange" data-target="oneselfLeft">
						<img v-if="oneselfLeft.length" v-bind:src="oneselfLeft">
					</label>
				</li>
				<li>
					<p>示例</p>
					<div></div>
				</li>
				<li v-bind:style="oneselfRight.length ? 'background: transparent' : ''">
					<p>与店员合影</p>
					<label>
						<input type="file" accept="image/jpeg,image/jpg,image/png" v-on:change="onFileChange" data-target="oneselfRight">
						<img v-if="oneselfRight.length" v-bind:src="oneselfRight">
					</label>
				</li>
			</ul>
		</div>
		<div class="cut"></div>
		<div class="upload-data-wrap">
			<p class="head">照片要求</p>
			<p>申请人持有本身有效二代身份证；</p>
			<p>拍摄时确保身份证边框完整，自己清晰度均匀。</p>
			<ul class="examples-list flex-center">
				<li>
					<img src="img/examples_01.png" alt="">
					<p>标准</p>
				</li>
				<li>
					<img src="img/examples_02.png" alt="">
					<p>边框缺失</p>
				</li>
				<li>
					<img src="img/examples_03.png" alt="">
					<p>照片模糊</p>
				</li>
				<li>
					<img src="img/examples_04.png" alt="">
					<p>曝光强烈</p>
				</li>
			</ul>
		</div>
		<div class="cut bottom"></div>
		<div class="submit-btn-wrap bottom">
			<mt-button v-on:click="onSubmit">继续申请</mt-button>
		</div>
	</div>
	<script>
		(function(Vue, seajs){
			seajs.use(["fieldInput", "uploadPic","zeptoAjax"], function(fieldInput, uploadPic,$){
				Vue.component(fieldInput.name,fieldInput);
				var app = new Vue({
					el: "#app",
					data: {
						idcardLeft: "",
						idcardRight: "",
						oneselfLeft: "",
						oneselfRight: ""
					},
					methods: {
						onFileChange: function(e){
							uploadPic.call(this, e, function(data){
								this[e.target.getAttribute("data-target")] = data;
							});
						},
						validate: function(){
							if(!this.idcardLeft.length){
								this.$toast('请上传身份证正面!');
								return false
							}else if(!this.idcardRight.length){
								this.$toast('请上传身份证反面!');
								return false
							}else if(!this.oneselfLeft.length){
								this.$toast('请上传本人手持身份证照片!');
								return false
							}else if(!this.oneselfRight.length){
								this.$toast('请上传与店员合影!');
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
								url:"${rc.contextPath}/ngres/dingdanguanli/order/multiUpload?id="+id,
								dataType:"json",
								contentType:"application/json;charset=UTF-8",
								processData:false,
						 success:function(data){
								  var data1 = eval(data);
								  console.log(data1);
								  if(data.status == "success"){
								   window.location.href="${rc.contextPath}/ngres/dingdanguanli/order/tiaozhuan5?id="+data1.id;
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