<%@page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<base href="<%=basePath%>">
<title>Jflyfox</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%-- //favicon.ico小图标名称 --%>
<link rel="icon" href="favicon.ico"/>
<link rel="shortcut icon" href="favicon.ico"/>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="Jflyfox">
<meta http-equiv="description" content="Jflyfox">
<%-- jquery --%>
<%@include file="/static/component/include/jquery.jsp"%>

<%-- 按钮样式 --%>
<link href="static/styles/button.css" rel="stylesheet" type="text/css" />

<link href="static/styles/login.css" rel="stylesheet" type="text/css" />

<script type="text/javascript">
	function login() {
		form1.action = "login";
		form1.submit();
		// submit_js("login_login.action", "loginform");
	}
	<%-- jQuery 当键盘敲击【ENTER】键时，系统登录 --%>
	$(document).ready(function() {
		$(".main").keydown(function(event) {
			if (event.which == 13) {
				login();
			}
		});
	});
</script>
</head>

<body>
	<form id="form1" method="post">
		<div class="main">
			<div class="top"></div>
			<div class="middle">
				<div class="left"></div>
				<div class="center">
					<ul>
						<li><span class="label_txt">用户名</span><input tabindex="1"
							class="input_txt" name="username" maxLength="20"
							autofocus="autofocus" placeholder="请输入用户名" />
						</li>
						<li><span class="label_txt">密&nbsp;&nbsp;&nbsp;码</span><input tabindex="2"
							type="password" class="input_txt" name="password"
							maxLength="20" placeholder="请输入密码" />
						</li>
						<li><span class="label_txt"></span> <span
							style="font-size: 12px; color: red">${requestScope.msg}</span></li>
						<li><a href="javascript:void(0)" onclick="login();" tabindex="3"
							class="btn" onmouseover="this.className='btn_over';"
							onmouseout="this.className='btn';"></a></li>
					</ul>
				</div>
				<div class="right"></div>
			</div>
			<div class="bottom"></div>
		</div>
		<div class="logo">Jflyfox BLOG</div>
		<!-- 
		<div class="gis"></div>
		 -->
		<div class="earth"></div>
	</form>
</body>
</html>

