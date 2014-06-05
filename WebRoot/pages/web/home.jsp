<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="flyfox" uri="flyfox.tld"  %>
<%@taglib uri="jsp_layout.tld" prefix="layout"%>

<layout:override name="head">
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
	
	<link rel="stylesheet" href="static/styles/home.css" type="text/css" media="screen, projection">
	<%-- jquery --%>
	<%@include file="/static/component/include/jquery.jsp"%>
	<%@include file="/static/component/include/my97.jsp"%>
	<%-- 弹出框 --%>
	<%@include file="/static/component/include/ymprompt.jsp"%>
	<%-- 主JS --%>
	<script type="text/javascript" src="static/common/main.js"></script>
	<script language="javascript" type="text/javascript" src="static/component/echarts/echarts-plain.js"></script>
	<script language="javascript" type="text/javascript" >
		var totalMoneyData = ${totalMoney};
		var projectMoneyData = ${projectMoney};
		var trendMoneyData = "";
	</script>
	<script type="text/javascript" src="pages/web/home.js"></script>
</layout:override>

<layout:override name="body">
<div id="container">
    <div id="header">
        	   
        <h1 ><a href="web"></a></h1>
          
        <h2>金钱管理</h2>
        <ul class="nav">
          <li><a href="web">首页</a></li>
          <li><a href="#project">项目信息</a></li>
          <li><a href="#money">费用明细</a></li>
          <li><a href="admin" target="_blank">系统管理</a></li>
          <li><a href="#contact">联系我们</a></li>
          <li><a class="last" href="web"><img src="static/images/web/logo_menu.png"></a></li>
        </ul>
    </div> <!-- header -->
    
	<div id="column">
		<h1>项目费用报表</h1>
		<div id="echarts2" style="height:300px;width:100%border:1px solid #ccc;padding:10px;"></div>
		
		<h1>总费用报表</h1>
		<div id="echarts1" style="height:300px;width:100%border:1px solid #ccc;padding:10px;"></div>
	
		<%--
		<h1>费用趋势图</h1>
		<div id="echarts3" style="height:300px;width:100%border:1px solid #ccc;padding:10px;"></div>
		 --%>
		<%--
		参考：http://dl.pconline.com.cn/download/102948.html
		 --%>
		
		<h1 id="project">项目信息</h1>
		<div id="" style="width:100%;padding:10px;">
			<c:forEach items="${requestScope.listProject}" var="item" varStatus="row">
				<h3>${item.name}</h3>
				<p title="${item.remark}">${item.remark}!</p>
				<div style="width: 50%;font-size: 11pt;">
					<table>
						<thead><tr><th>日期</th><th>类型</th><th>金额</th></tr></thead> 
						<c:forEach items="${requestScope.mapMoney[item.id]}" var="item" varStatus="row">
						<tr>
							<td>${item.pay_time}</td>
							<td>${flyfox:dictValue(item.type) }</td>
							<td>${item.name}（${item.amount}）</td>
						</tr>
						</c:forEach>
					</table>
				</div>
			</c:forEach>
		</div>
	
		<h1 id="money">费用明细</h1>
		<form action="" name="form_money">
		<div class="tableSearch">
		    <select name="project_id" style="width: 100px;">
					<option value="">所有</option> ${optionList }
			</select> 
			<select name="type">
				<option value="">所有</option> 
				${flyfox:dictSelect('moneyType',attr.type)}
			</select>
			<input type="text" name="pay_time_start"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" size="8" />
			-
			<input type="text" name="pay_time_end"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"size="8"   />
			<button type="button" class="btn" onclick="oper_list();" >查 询</button>
			<button type="button" class="btn" onclick="oper_reset();" >重 置</button>
			<button type="button" class="btn" onclick="oper_add();" >添 加</button>
		</div>
		
		<div id="tableMoney" style="width:70%;padding:10px;font-size: 11pt;">
			<table>
				<thead><tr><th>日期</th><th>项目</th><th>类型</th><th>金额</th></tr></thead> 
				<c:forEach items="${requestScope.listMoney}" var="item" varStatus="row">
					<tr class="time${item.pay_time} project${item.project_id} type${item.type}">
						<td>${item.pay_time}</td>
						<td>${item.project_name}</td>
						<td>${flyfox:dictValue(item.type) }</td>
						<td>${item.name}（${item.amount}）</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		</form>
	</div>
	
	<div id="column_left">
	<h1 id="contact">开发团队</h1>
		Fly的狐狸
		<br /> ……
	</div>
	
	<div id="column_right">
	<h1>联系我们</h1>
		QQ：330627517
   		<iframe class="ueditor_baidumap" src="http://localhost/money/ueditor/dialogs/map/show.html#center=116.391496,39.913783&zoom=13&width=330&height=320&markers=116.404,39.915&markerStyles=l,A" frameborder="0" width="334" height="324"></iframe>
	</div>
	

	<div id="footer">
		<div class="map">
		<h1>网站地图</h1>
		<ul>
		  <li><a href="#">首页</a></li>
		  <li><a href="#project">项目信息</a></li>
		  <li><a href="#money">费用明细</a></li>
		  <li><a href="admin" target="_blank">系统管理</a></li>
		  <li><a href="#contact">联系我们</a></li>
		</ul>
		</div>
		
		<div class="map" itemscope="" itemtype="http://www.schema.org/Organization">
		<h1 itemprop="name">网站支持</h1>
		<meta itemscope="" itemprop="description" content="The PostGIS PSC manages the future direction of the PostGIS project such as timing of releases and new features.">
		<ul>
		  <li itemscope="" itemprop="contactPoint" itemtype="http://www.schema.org/ContactPoint"><a href="#" itemprop="url"><span itemprop="name">移动网络</span> (<span itemprop="contactType">移动</span>)</a></li>
		  <li itemscope="" itemprop="contactPoint" itemtype="http://www.schema.org/ContactPoint"><a href="#" itemprop="url"><span itemprop="name">联通电力</span></a></li>
		  <li itemscope="" itemprop="contactPoint" itemtype="http://www.schema.org/ContactPoint"><a href="#" itemprop="url"><span itemprop="name">电信</span></a></li>
		  <li itemscope="" itemprop="contactPoint" itemtype="http://www.schema.org/ContactPoint"><a href="#" itemprop="url"><span itemprop="name">中海油</span></a></li>
		   <li itemscope="" itemprop="contactPoint" itemtype="http://www.schema.org/ContactPoint"><a href="#" itemprop="url"><span itemprop="name">中石化</span></a></li>
		</ul>
		</div>
		
		<p class="clear">&nbsp;</p>
	</div> <!-- footer -->
	
  </div> <!-- container -->
  
  <a id="scrollTop" href="#container" title="返回顶部" style="display: inline;"></a>
  
</layout:override>

<%@include file="/static/common/_layout.jsp"%>
