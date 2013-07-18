<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Flyfox平台</title>
<link type="image/x-icon" href="/resources/default/images/logo.ico"
	rel="icon" />
<link href="http://java.bjepn.com/resources/default/images/logo.ico"
	rel="shortcut icon" />
</head>

<body
	onload="javascript:document.form1.loginid.focus();document.form1.loginid.select();">
	<form name="form1" method="post" action="/login">
		<input name="" class="yzm" type="hidden" />
		<div id="hsptdl_register">
			<ul>
				<li><span>登录帐号：</span> <input name="loginid" type="text"
					onkeypress="scheck();" maxlength="128" placeholder="请您输入用户名"
					value="${empty loginid? '':loginid  }" /></li>
				<li><span>密码：</span> <input name="password" type="password"
					onkeypress="scheck();" maxlength="16" placeholder="请您输入密码" value="" /></li>
			</ul>
			<span class="ts">${message }</span>
		</div>
	</form>
</body>
</html>
