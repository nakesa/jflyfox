<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="jsp_layout.tld" prefix="layout"%>

<layout:override name="head">
<%@include file="/static/component/include/head.jsp"%>
</layout:override>

<layout:override name="body">
	<form name="form1" action="" method="post">
		<!-- 数据列表 -->
			<table class="table">
				<tr>
					<td>类型</td>
					<td>
						<input type="hidden" name="dictType" value="${item.dict_type}" />
						<select disabled="disabled" style="width: 100px;">
							${optionList }
						</select>
					</td>
				</tr>
				<tr>
					<td>名称</td>
					<td>${item.detail_name}</td>
				</tr>
				<tr>
					<td>编号</td>
					<td>${item.detail_code}</td>
				</tr>
				<tr>
					<td>排序号</td>
					<td>${empty item.detailSort?'0':item.detail_sort}</td>
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
