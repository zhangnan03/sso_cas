<%@page import="cn.symdata.service.RSAService"%>
<%@page import="cn.symdata.common.utils.SpringUtils"%>
<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@page import="java.util.UUID"%>
<%@page import="java.security.interfaces.RSAPublicKey"%>
<%@page import="org.apache.commons.codec.binary.Base64"%>
<%@page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
String base = request.getContextPath();
String captchaId = UUID.randomUUID().toString();
ApplicationContext applicationContext = SpringUtils.getApplicationContext();
if (applicationContext != null) {
%>
<shiro:authenticated>
<%
response.sendRedirect(base + "/common/main.jhtml");
%>
</shiro:authenticated>
<%
}
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="renderer" content="webkit|chrome">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>登录</title>
<link rel="stylesheet" type="text/css" href="<%=base%>/static/css/main.css">
<link rel="stylesheet" type="text/css" href="<%=base%>/static/css/lhgcalendar.css">
<link rel="stylesheet" type="text/css" href="<%=base%>/static/css/blue.css">
<script type="text/javascript" src="<%=base%>/static/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="<%=base%>/static/js/jsbn.js"></script>
<script type="text/javascript" src="<%=base%>/static/js/prng4.js"></script>
<script type="text/javascript" src="<%=base%>/static/js/rng.js"></script>
<script type="text/javascript" src="<%=base%>/static/js/rsa.js"></script>
<script type="text/javascript" src="<%=base%>/static/js/base64.js"></script>

<%
if (applicationContext != null) {
	RSAService rsaService = SpringUtils.getBean("rsaServiceImpl", RSAService.class);
	RSAPublicKey publicKey = rsaService.generateKey(request);
	String modulus = Base64.encodeBase64String(publicKey.getModulus().toByteArray());
	String exponent = Base64.encodeBase64String(publicKey.getPublicExponent().toByteArray());
	
	String message = null;
	String loginFailure = (String) request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
	if (loginFailure != null) {
		if (loginFailure.equals("org.apache.shiro.authc.pam.UnsupportedTokenException")) {
			message = "admin.captcha.invalid";
		} else if (loginFailure.equals("org.apache.shiro.authc.UnknownAccountException")) {
			message = "admin.login.unknownAccount";
		} else if (loginFailure.equals("org.apache.shiro.authc.DisabledAccountException")) {
			message = "admin.login.disabledAccount";
		} else if (loginFailure.equals("org.apache.shiro.authc.LockedAccountException")) {
			message = "admin.login.lockedAccount";
		} else if (loginFailure.equals("org.apache.shiro.authc.IncorrectCredentialsException")) {
			message = "admin.login.incorrectCredentials";
		} else if (loginFailure.equals("org.apache.shiro.authc.AuthenticationException")) {
			message = "admin.login.authentication";
		}
	}
%>
<script type="text/javascript" src="<%=base%>/static/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript">
	$().ready( function() {
		var $loginForm = $("#loginForm");
		var $enPassword = $("#enPassword");
		var $username = $("#username");
		var $password = $("#password");
		var $captcha = $("#captcha");
		var $captchaImage = $("#captchaImage");
		var $errorInfo=$("#errorInfo");
		// 更换验证码
		$captchaImage.click( function() {
			$captchaImage.attr("src", "<%=base%>/common/captcha.jhtml?captchaId=<%=captchaId%>&timestamp=" + (new Date()).valueOf());
		});
		
		// 表单验证、记住用户名
		$loginForm.submit(function() {
			if ($username.val() == "") {
				$errorInfo.text("<%=SpringUtils.getMessage("admin.login.usernameRequired")%>");
				return false;
			}
			if ($password.val() == "") {
				$errorInfo.text("<%=SpringUtils.getMessage("admin.login.passwordRequired")%>");
				return false;
			}
			if ($captcha.val() == "") {
				$errorInfo.text("<%=SpringUtils.getMessage("admin.login.captchaRequired")%>");
				return false;
			}
			var rsaKey = new RSAKey();
			rsaKey.setPublic(b64tohex("<%=modulus%>"), b64tohex("<%=exponent%>"));
			var enPassword = hex2b64(rsaKey.encrypt($password.val()));
			$enPassword.val(enPassword);
		});
		
		<%if (message != null) {%>
			$errorInfo.text("<%=SpringUtils.getMessage(message)%>");
		<%}%>
	});
</script>
<%}%>
</head>
<body>
<%if (applicationContext != null) {%>
<div class="login-main">
		<div class="login-top">信达账务管理中心</div>
		<div class="login-mid">
			<div class="box1200">
				<img src="<%=base%>/static/images/login-iocn1.png" alt="" class="" />
				<div class="login-con">
					<form id="loginForm" action="index.jsp" method="post">
					<input type="hidden" id="enPassword" name="enPassword" />
					<input type="hidden" name="captchaId" value="<%=captchaId%>" />
						<div class="login-tit">
							<span>登录</span>
						</div>
						<div class="login-row">
							<input type="text" name="username" id="username" placeholder="请输入账户" />
						</div>
						<div class="login-row">
							<input type="password" name="password" id="password" placeholder="请输入密码" />
						</div>
						<div class="login-row">
							<input type="text" class="check-code" id="captcha" name="captcha" placeholder="请输入验证码"
								name="" id="" />
							<img id="captchaImage" class="check-code-img" src="<%=base%>/common/captcha.jhtml?captchaId=<%=captchaId%>" title="<%=SpringUtils.getMessage("admin.captcha.imageTitle")%>" />	
						</div>
						<p class="error-tips" id="errorInfo"></p>
						<input type="submit" class="login-btn" value="<%=SpringUtils.getMessage("admin.login.login")%>" />
						<p class="safe-des">
							<span></span>平台账户资金由国有银行实施监管
						</p>
					</form>
				</div>
			</div>
		</div>
		<div class="bq-bot">
			<p>*解释权归北京信达天下科技有限公司</p>
		</div>
	</div>
	<%}%>
</body>
</html>