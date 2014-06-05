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
				<td>项目名称</td>
				<td>
				${model.name}
				</td>
			</tr>
			<tr>
				<td>项目说明</td>
				<td title="${model.remark}">
				<c:choose>
			          <c:when test="${fn:length(model.remark) > 20}">
			              <c:out value="${fn:substring(model.remark, 0, 20)}..." />
			          </c:when>
			         <c:otherwise>
			            <c:out value="${model.remark}" />
			          </c:otherwise>
			    </c:choose>
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