<%@page import="com.flyfox.component.common.Include"%>
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
<%=Include.quick()%>
<style type="text/css">
li {
	list-style-type: none;
	width: 150px;
}
</style>
</head>

</div>
<body
	onload="javascript:document.form1.loginid.focus();document.form1.loginid.select();">
	<form name="form1" method="post" action="/login" class="form">
		<input name="" class="yzm" type="hidden" />
		<div class="block block-grey x1-large x1-m-box"
			style="text-align: center; margin-top: 100px;margin-right:100px;float: right;width: 500px;height: 200px;">
			<div class="control" style="margin-top: 20px;">
				<label>帐号：</label><input name="loginid" type="text"
					onkeypress="scheck();" maxlength="128" placeholder="请您输入用户名"
					value="${empty loginid? '':loginid  }" />
			</div>
			<div class="control" style="margin-top: 20px;">
				<label>密码：</label><input name="password" type="password"
					onkeypress="scheck();" maxlength="16" placeholder="请您输入密码" value="" />
			</div>
			<div class="control" style="margin-top: 20px;">
				<span class="ts">${message }</span>
				<a style="margin-left: 30px;" href="main.jsp"
					class="button button-blue">登陆</a>
			</div>
		</div>
	</form>
</body>
</html>
