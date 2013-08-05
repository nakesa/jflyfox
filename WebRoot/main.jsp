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
<%=Include.index()%>
<script type="text/javascript">
	$(function() {
		$('ul.grid li a').on('click', function() {
			$("ul.grid li").removeClass("active");
			$(this).parent('li').addClass('active');
		});
	});
</script>
</head>

<body>
	<form name="form1" method="post" action="/login">
		<input name="" class="yzm" type="hidden" />
		<div class="col-1-1">
			<nav class="menu-horizontal">
			<ul class="grid">
				<li class="active"><a href="#"><i class="icon-home"></i>FlyFox系統</a></li>
				<li><a href="dict/main" target="main"><i class="icon-list"></i>数据字典</a></li>
				<li><a href="#"><i class="icon-sign-blank"></i>Button</a></li>
				<li><a href="#"><i class="icon-table"></i>Table</a></li>
				<li><a href="#"><i class="icon-sitemap"></i>Menu</a></li>
			</ul>
			</nav>
		</div>
		<div class="col-1-1">
			<iframe src="" name="main" width="100%" frameborder="0"
				style="min-height: 600px;"></iframe>
		</div>

		<footer class="container">
		<div class="grid">
			<div class="col-1-1 footer_fixed">
				<div class="box text-center footer_fixed_cotent">
					Copyright 2013 <a href="http://flyfox.com/" target="_blank">FlyFox
						System</a>
				</div>
			</div>
		</div>
		</footer>
	</form>
</body>
</html>
