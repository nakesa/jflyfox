<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="/resources/include/head.jsp"%>
<h1>
	数据字典管理：<a href="main" >切换</a>
	&nbsp;&nbsp;
	<a href="javascript:void(0);"
		onclick="Iframe('add',460,340,'数据字典')">创建字典</a>
</h1>
<table class="tableList">
	<tr>
		<th width="4%">
			ID
		</th>
		<th>
			类型
		</th>
		<th>
			值
		</th>
		<th>
			CODE
		</th>
		<th>
			备注
		</th>
		<th>
			操作
		</th>
	</tr>
	<c:forEach items="${dictList}" var="dict">
		<tr>
			<td>
				${dict.dict_id}
			</td>
			<td>
				${dict.dict_type}
			</td>
			<td>
				${dict.dict_value}
			</td>
			<td>
				${dict.dict_code}
			</td>
			<td>
				${dict.remark}
			</td>
			<td>
				&nbsp;&nbsp;
				<a href="delete_main/${dict.dict_id}">删除</a> &nbsp;&nbsp;
				<a
					href="javascript:Iframe('add_main?dict_type=${dict.dict_id}',500,340,'数据字典')">修改</a>
			</td>
		</tr>
	</c:forEach>
</table>
<%@include file="/resources/include/foot.jsp"%>