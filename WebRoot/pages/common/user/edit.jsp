<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="jsp_layout.tld" prefix="layout"%>

<layout:override name="head">
	<%@include file="/static/component/include/head.jsp"%>
	<%@include file="/static/component/include/my97.jsp"%>
	<script type="text/javascript">
		function oper_save() {
			form1.action = "user/save/" + form1["model.userid"].value;
			form1.submit();
		}
	</script>
</layout:override>

<layout:override name="body">
	<form name="form1" action="" method="post" class="form-horizontal"
		role="form">
		<input type="hidden" name="model.userid" value="${model.userid}" />
		<table class="table">
			<tr>
				<td>登陆名</td>
				<td>
				<input class="form-control" type="text" name="model.username" value="${model.username}" />
				</td>
			</tr>
			<tr>
				<td>真实姓名</td>
				<td>
				<input class="form-control" type="text" name="model.realname" value="${model.realname}" />
				</td>
			</tr>
			<tr>
				<td></td>
				<td>
				<button class="btn btn-default" onclick="oper_save();">保 存</button>
				<button class="btn btn-default" onclick="closeIframe();">关 闭</button>
				</td>
			</tr>
		</table>
	</form>
</layout:override>
<%@include file="/static/common/_layout.jsp"%>
