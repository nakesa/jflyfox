<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="jsp_layout.tld" prefix="layout"%>

<layout:override name="head">
<%@include file="/static/component/include/head.jsp"%>
<%@include file="/static/component/include/my97.jsp"%>
<script type="text/javascript">
	function oper_save() {
		form1.action = "dict/save/"+form1["model.detail_id"].value;
		form1.submit();
	}
</script>
</layout:override>

<layout:override name="body">
	<form name="form1" action="" method="post">
		<input type="hidden" name="model.detail_id" value="${item.detail_id}" />
		<!-- 数据列表 -->
		<table class="table">
			<tr>
				<td>类型</td>
				<td><c:choose>
						<c:when test="${item.detail_id>0 }">
							<input type="hidden" name="model.dict_type"
								value="${item.dict_type}" />
							<select disabled="disabled" style="width: 100px;">
								${optionList }
							</select>
						</c:when>
						<c:otherwise>
							<select name="model.dict_type" style="width: 100px;">
								${optionList }
							</select>
						</c:otherwise>
					</c:choose></td>
			</tr>
			<tr>
				<td>名称</td>
				<td><input type="text" name="model.detail_name"
					value="${item.detail_name}" /></td>
			</tr>
			<tr>
				<td>编号</td>
				<td><input type="text" name="model.detail_code"
					value="${item.detail_code}" /></td>
			</tr>
			<tr>
				<td>排序号</td>
				<td><input type="text" name="model.detail_sort"
					value="${empty item.detail_sort?'0':item.detail_sort}" /></td>
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

<%@include file="/static/common/_layout.jsp" %>