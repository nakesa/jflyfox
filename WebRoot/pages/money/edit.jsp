<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="jsp_layout.tld" prefix="layout"%>

<layout:override name="head">
	<%@include file="/static/component/include/head.jsp"%>
	<%@include file="/static/component/include/my97.jsp"%>
	<script type="text/javascript">
		function oper_save() {
			form1.action = "money/save/" + form1["model.id"].value;
			form1.submit();
		}
	</script>
</layout:override>

<layout:override name="body">
	<form name="form1" action="" method="post" class="form-horizontal"
		role="form">
		<input type="hidden" name="model.id" value="${model.id}" />
		<table class="table">
			<tr>
				<td>项目名称</td>
				<td>
					<select name="model.project_id" class="form-control">
						${optionList }
					</select>
				</td>
			</tr>
			<tr>
				<td>概要</td>
				<td>
				<input class="form-control" type="text" name="model.name" value="${model.name}" />
				</td>
			</tr>
			<tr>
				<td>金额(RMB)</td>
				<td>
				<input class="form-control" type="text" name="model.amount" value="${model.amount}" />
				</td>
			</tr>
			<tr>
				<td>类型</td>
				<td>
				<select name="model.type" class="form-control" >
					${flyfox:dictSelect('moneyType',model.type)}
				</select>
				</td>
			</tr>
			<tr>
				<td>时间</td>
				<td>
				<input type="text" name="model.pay_time" class="form-control"
					value="${model.pay_time}" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"  />
				</td>
			</tr>
			<tr>
				<td>备注</td>
				<td>
					<textarea class="form-control" rows="3" cols="30" name="model.remark">${model.remark}</textarea>
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
