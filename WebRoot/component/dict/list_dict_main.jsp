<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="/resources/include/head.jsp"%>
<script type="text/javascript">
<!--
$(function($){

});
//-->
</script>
<style>
<!--
.tableList {
	border: 1px solid black;
	border-spacing: 0;
	border-collapse: collapse;
	width: 100%;
}

.tableList td,th {
	border: 1px solid black;
	text-align: center;
}
-->
</style>
<h1>
	数据字典管理&nbsp;&nbsp;
	<a href="javascript:void(0);"
		onclick="Iframe('add_main',460,340,'数据字典')">创建字典</a>
</h1>
<table class="tableList">
	<tr>
		<th width="4%">
			类型
		</th>
		<th width="35%">
			名称
		</th>
		<th width="35%">
			备注
		</th>
		<th width="12%">
			操作
		</th>
	</tr>
	<c:forEach items="${dictMainList}" var="dict">
		<tr>
			<td>
				${dict.dict_type}
			</td>
			<td>
				${dict.dict_name}
			</td>
			<td>
				${dict.remark}
			</td>
			<td>
				&nbsp;&nbsp;
				<a href="delete_main/${dict.dict_type}">删除</a> &nbsp;&nbsp;
				<a
					href="javascript:Iframe('add_main?dict_type=${dict.dict_type}',500,340,'数据字典')">修改</a>
			</td>
		</tr>
	</c:forEach>
</table>
<%@include file="/resources/include/foot.jsp"%>