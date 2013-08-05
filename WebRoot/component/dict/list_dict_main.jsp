<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="/resources/include/head.jsp"%>
<h1>
	数据字典管理：<a href="detail" >切换</a>
		&nbsp;&nbsp;
	<a href="javascript:void(0);"
		onclick="Iframe('add_main',460,340,'数据字典')">创建字典</a>
</h1>
<table class="table table-striped">
	<tr>
		<th width="5%">
			序号
		</th>
		<th width="25%">
			类型
		</th>
		<th width="25%">
			名称
		</th>
		<th width="35%">
			备注
		</th>
		<th width="10%">
			操作
		</th>
	</tr>
	<c:forEach items="${dictMainList}" var="dict" varStatus="status">
		<tr>
			<td>
				${status.count}
			</td>
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
				<a href="delete_main/${dict.dict_type}">删除</a> &nbsp;&nbsp;
				<a href="javascript:Iframe('add_main?dict_type=${dict.dict_type}',500,340,'数据字典')">修改</a>
				<a href="detail">查看</a>
			</td>
		</tr>
	</c:forEach>
</table>
<%@include file="/resources/include/foot.jsp"%>