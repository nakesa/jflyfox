<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="jsp_layout.tld" prefix="layout"%>

<layout:override name="head">
<%@include file="/static/component/include/head.jsp"%>
</layout:override>

<layout:override name="body">
<body>
	<form name="form1" action="" method="post" class="form-horizontal" role="form">
		<!-- 数据列表 -->
		<table class="table">
			<tr>
				<td>登陆名</td>
				<td>
				${model.username}
				</td>
			</tr>
			<tr>
				<td>真实姓名</td>
				<td>
				${model.realname}
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
				<button class="btn btn-default" onclick="closeIframe();">关 闭</button>
				</td>
			</tr>
		</table>
	</form>
</layout:override>
<%@include file="/static/common/_layout.jsp" %>