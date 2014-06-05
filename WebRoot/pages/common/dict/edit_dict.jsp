<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="jsp_layout.tld" prefix="layout"%>

<layout:override name="head">
<%@include file="/static/component/include/head.jsp"%>
<%@include file="/static/component/include/my97.jsp"%>
<script type="text/javascript">
	function oper_save() {
		form1.action = "dict/save_dict/"+form1["sysDict.dict_id"].value;
		form1.submit();
	}
	
	function oper_del() {
		form1.action = "dict/delete_dict/"+form1["sysDict.dict_id"].value;
		form1.submit();
	}
</script>
</layout:override>

<layout:override name="body">
	<form name="form1" action="" method="post">
		<input type="hidden" name="sysDict.dict_id" value="${item.dict_id}" />
		<!-- 数据列表 -->
		<table class="table">
			<tr>
				<td>类型</td>
				<td>
					<input type="text" name="sysDict.dict_type"  ${item.dict_id>0?'readonly="readonly"':''} value="${item.dict_type}" />
				</td>
			</tr>
			<tr>
				<td>名称</td>
				<td><input type="text" name="sysDict.dict_name" value="${item.dict_name}" /></td>
			</tr>
			<tr>
				<td>备注</td>
				<td><input type="text" name="sysDict.dict_remark" value="${item.dict_remark}" /></td>
			</tr>
			<td></td>
			<td>
			<button class="btn btn-default" onclick="oper_save();">保 存</button>
			<c:if test="${item.dict_id>0 }">
				<button class="btn btn-default" onclick="oper_del();">删 除</button>
			</c:if>
			<button class="btn btn-default" onclick="closeIframe();">关 闭</button>
			</td>
		</table>
	</form>
</layout:override>

<%@include file="/static/common/_layout.jsp" %>