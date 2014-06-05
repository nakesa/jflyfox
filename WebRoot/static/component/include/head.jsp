<%@page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="flyfox" uri="flyfox.tld"  %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<base href="<%=basePath%>">
<title>金钱管理</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%-- //favicon.ico小图标名称 --%>
<link rel="icon" href="favicon.ico"/>
<link rel="shortcut icon" href="favicon.ico"/>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="金钱管理">
<meta http-equiv="description" content="金钱管理">
<%-- 弹出框 --%>
<%@include file="/static/component/include/ymprompt.jsp"%>
<%-- jquery --%>
<%@include file="/static/component/include/jquery.jsp"%>
<%-- bootstrap --%>
<%@include file="/static/component/include/bootstrap.jsp"%>
<%-- 分页 --%>
<link rel="stylesheet" id='skin' type="text/css" href="static/component/smartpaginator/smartpaginator.css" />
<script type="text/javascript" src="static/component/smartpaginator/smartpaginator.js"></script>
<%-- 主JS --%>
<script type="text/javascript" src="static/common/main.js"></script>
<%-- 验证工具 --%>
<script type="text/javascript" src="static/common/valid.js"></script>
<%-- 初始化JS --%>
<script type="text/javascript" src="static/common/initJs.js"></script>
<%-- 主样式 --%>
<link href="static/common/main.css" rel="stylesheet" type="text/css" />
