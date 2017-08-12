<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>车保姆管理中心</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-1.11.0.min.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/index_login.css" />
<script type="text/javascript">
				//登录方法
				function doLogin() {
					var uid = $("#username").val();
					var pwd = $("#password").val();
					//验证
					if (uid == "") {
						alert("请输入账号");
						$("#username").focus();
						return false;
					} else if (pwd == "") {
						alert("请输入密码");
						$("#password").focus();
						return false;
					}
					$("#myform").submit();
					return true;
				}
				function kk(event) {
					if (event.keyCode == 13) {
						doLogin();
					}
				}
</script>
<style>
.error {
	color: red;
}
</style>
</head>
<body onkeyup="kk(event)" class="bg_body ">
	<div class="login">
		<div class="home_in">
			<a href="http://www.szsuoyuan.com" target="_blank"> <img
				src="static/images/home_in.png" />
			</a>
		</div>
		<div class="login_logo">
			<a href="#"></a>
		</div>
		<img height="450" src="static/images/login.png" />
			<div class="login_form">
			<div class="error">${error}</div>
				<form action="" id="myform" method="post">
					<div class="loginInfo" style="display: inline;">
						<p class="login_user">
							<input type="text" name="username" id="username" value="" placeholder="请输入账号"></input>
						</p>
						<p class="login_pwd">
							<input type="password" name="password" id="password" placeholder="请输入密码"></input>
						</p>
						<p class="btnlogin">
							<img src="static/images/btn_login.png" width="75" height="38" onclick="doLogin()" />
						</p>
					</div>
				</form>
			</div>
	</div>
</body>
</html>
