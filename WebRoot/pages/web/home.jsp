<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="flyfox" uri="flyfox.tld"%>
<%@taglib uri="jsp_layout.tld" prefix="layout"%>

<layout:override name="head">
	<title>Jflyfox</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<%-- //favicon.ico小图标名称 --%>
	<link rel="icon" href="favicon.ico" />
	<link rel="shortcut icon" href="favicon.ico" />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="Jflyfox">
	<meta http-equiv="description" content="Jflyfox">

	<link rel="stylesheet" href="static/web/home.css" type="text/css"
		media="screen, projection">

</layout:override>

<layout:override name="body">
	<div class="x-page-container">
		<div class="mod-diary">
			<div class="mod-diary-top"></div>
			<div class="mod-diary-inner">
				<div class="mod-diary-pen"></div>
				<div class="mod-diary-nav"></div>
				<div class="mod-diary-clip"></div>
				<div class="mod-topspaceinfo">
					<h1>
						<a class="space-name" href="#">${model.title }</a>
					</h1>
				</div>

				<div class="mod-bloglist">

					<c:forEach items="${requestScope.list}" var="item" varStatus="row">
						<div class="mod-blogitem">
							<div class="box-postdate">
							${item.publish_time}
							<%--<br >${item.publish_user} --%>
							</div>
							<div class="item-title">
								<a href="">${item.title}</a>
							</div>
							<div class="item-content">${item.content }</div>
						</div>
					</c:forEach>

				</div>
			</div>
			<div class="mod-diary-bottom"></div>
		</div>
	</div>
</layout:override>

<%@include file="/static/common/_layout.jsp"%>
