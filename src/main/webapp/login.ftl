
<!DOCTYPE html>
<!-- 
蓝茂软件,创造无限可能
-->
<!--[if IE 8]> <html lang="zh" class="ie8 no-js"> <![endif]
-->
<!--[if IE 9]> <html lang="zh" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="zh">
<!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
<meta charset="utf-8"/>
<title> 风控| 登录</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1.0" name="viewport"/>
<meta http-equiv="Content-type" content="text/html; charset=utf-8">
<meta content="" name="description"/>
<meta content="" name="author"/>
<!-- BEGIN GLOBAL MANDATORY STYLES -->
<link href="ng/css／useso.css" rel="stylesheet" type="text/css"/>
<link href="${rc.contextPath}/ng/assets/global/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
<link href="${rc.contextPath}/ng/assets/global/plugins/simple-line-icons/simple-line-icons.min.css" rel="stylesheet" type="text/css"/>
<link href="${rc.contextPath}/ng/assets/global/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<link href="${rc.contextPath}/ng/assets/global/plugins/uniform/css/uniform.default.css" rel="stylesheet" type="text/css"/>
<!-- END GLOBAL MANDATORY STYLES -->
<!-- BEGIN PAGE LEVEL STYLES -->
<link href="${rc.contextPath}/ng/assets/global/plugins/select2/select2.css" rel="stylesheet" type="text/css"/>
<link href="${rc.contextPath}/ng/assets/admin/pages/css/login-soft.css" rel="stylesheet" type="text/css"/>
<!-- END PAGE LEVEL SCRIPTS -->
<!-- BEGIN THEME STYLES -->
<link href="${rc.contextPath}/ng/assets/global/css/components.css" id="style_components" rel="stylesheet" type="text/css"/>
<link href="${rc.contextPath}/ng/assets/global/css/plugins.css" rel="stylesheet" type="text/css"/>
<link href="${rc.contextPath}/ng/assets/admin/layout/css/layout.css" rel="stylesheet" type="text/css"/>
<link id="style_color" href="${rc.contextPath}/ng/assets/admin/layout/css/themes/default.css" rel="stylesheet" type="text/css"/>
<link href="${rc.contextPath}/ng/assets/admin/layout/css/custom.css" rel="stylesheet" type="text/css"/>
<!-- END THEME STYLES -->
<link rel="shortcut icon" href="favicon.ico"/>
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body class="login">
<!-- BEGIN LOGO -->
<div class="logo">
	<a href="index.html">
	<!-- <img src="${rc.contextPath}/ng/assets/admin/layout/img/logo-lantu.png" style="height:30px;width:150px" alt=""/>-->
	</a>
</div>
<!-- END LOGO -->
<!-- BEGIN SIDEBAR TOGGLER BUTTON -->
<div class="menu-toggler sidebar-toggler">
</div>
<!-- END SIDEBAR TOGGLER BUTTON -->
<!-- BEGIN LOGIN -->
<div class="content">
	<!-- BEGIN LOGIN FORM -->
	<form class="login-form"  method="post" action="${rc.contextPath}/login">
		<h3 class="form-title">登录风控后台管理系统</h3>
		<div class="alert alert-danger display-hide">
			<button class="close" data-close="alert"></button>
			<span>请输入用户名和密码.</span>
		</div>
		<#if errorMeg??><span style="color: red">${errorMeg}</span></#if>
		<div class="form-group">
			<!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->
			<label class="control-label visible-ie8 visible-ie9">用户名</label>
			<div class="input-icon">
				<i class="fa fa-user"></i>
				<input class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="用户名" name="loginname"/>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label visible-ie8 visible-ie9">密码</label>
			<div class="input-icon">
				<i class="fa fa-lock"></i>
				<input class="form-control placeholder-no-fix" type="password" autocomplete="off" placeholder="密码" name="passwd"/>
			</div>
		</div>
		<div class="form-actions">
			<label class="checkbox">
			<input type="checkbox" name="remember" value="1"/> 记住我 </label>
			<button type="submit" class="btn blue pull-right">
			登录 <i class="m-icon-swapright m-icon-white"></i>
			</button>
		</div>
		
		
		<div class="create-account">
			<p>
				 还没有账号 ?&nbsp; <a href="javascript:;">
				请联系管理员 </a>
			</p>
		</div>
	</form>
	<!-- END LOGIN FORM -->
	
</div>
<!-- END LOGIN -->
<!-- BEGIN COPYRIGHT -->
<!-- <div class="copyright">
	 2017 &copy; 上海蓝茂软件技术有限公司版权所有.
</div> -->
<!-- END COPYRIGHT -->
<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
<!-- BEGIN CORE PLUGINS -->
<!--[if lt IE 9]>
<script src="${rc.contextPath}/ng/assets/global/plugins/respond.min.js"></script>
<script src="${rc.contextPath}/ng/assets/global/plugins/excanvas.min.js"></script> 
<![endif]-->
<script src="${rc.contextPath}/ng/assets/global/plugins/jquery.min.js" type="text/javascript"></script>
<script src="${rc.contextPath}/ng/assets/global/plugins/jquery-migrate.min.js" type="text/javascript"></script>
<script src="${rc.contextPath}/ng/assets/global/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${rc.contextPath}/ng/assets/global/plugins/jquery.blockui.min.js" type="text/javascript"></script>
<script src="${rc.contextPath}/ng/assets/global/plugins/uniform/jquery.uniform.min.js" type="text/javascript"></script>
<script src="${rc.contextPath}/ng/assets/global/plugins/jquery.cokie.min.js" type="text/javascript"></script>
<!-- END CORE PLUGINS -->
<!-- BEGIN PAGE LEVEL PLUGINS -->
<script src="${rc.contextPath}/ng/assets/global/plugins/jquery-validation/js/jquery.validate.min.js" type="text/javascript"></script>
<script src="${rc.contextPath}/ng/assets/global/plugins/backstretch/jquery.backstretch.min.js" type="text/javascript"></script>
<script type="text/javascript" src="${rc.contextPath}/ng/assets/global/plugins/select2/select2.min.js"></script>
<!-- END PAGE LEVEL PLUGINS -->
<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="${rc.contextPath}/ng/assets/global/scripts/metronic.js" type="text/javascript"></script>
<script src="${rc.contextPath}/ng/assets/admin/layout/scripts/layout.js" type="text/javascript"></script>
<script src="${rc.contextPath}/ng/assets/admin/layout/scripts/demo.js" type="text/javascript"></script>
<script src="${rc.contextPath}/ng/assets/admin/pages/scripts/login-soft.js" type="text/javascript"></script>
<!-- END PAGE LEVEL SCRIPTS -->
<script>
jQuery(document).ready(function() {     
  Metronic.init(); // init metronic core components
	Layout.init(); // init current layout
  Login.init();
  Demo.init();
       // init background slide images
       $.backstretch([
        "${rc.contextPath}/ng/assets/admin/pages/media/bg/2.jpg",
        "${rc.contextPath}/ng/assets/admin/pages/media/bg/3.jpg",
        "${rc.contextPath}/ng/assets/admin/pages/media/bg/4.jpg",
        "${rc.contextPath}/ng/assets/admin/pages/media/bg/1.jpg"
        ], {
          fade: 1000,
          duration: 4000
    }
    );
});
</script>
<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>