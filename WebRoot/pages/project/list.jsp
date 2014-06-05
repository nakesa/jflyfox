<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="jsp_layout.tld" prefix="layout"%>

<layout:override name="head">
<%@include file="/static/component/include/head.jsp"%>

<script type="text/javascript">
paginator = function(page){
	oper_list();
};

function oper_list(){
	form1.action = 'project/list';
	form1.submit();
}

function oper_view(pid){
	var url = 'project/view/'+pid;
	Iframe('project/view/'+pid,300,240,'项目查看',false,false,false,EmptyFunc);
}

function oper_add(){
	var url = 'project/add';
	Iframe(url,350,300,'项目添加');
}

function oper_edit(pid){
	var url = 'project/edit/'+pid;
	Iframe(url,350,300,'项目修改');
}

function oper_delete(pid){
	Confirm("确认要删除该项目信息？",function(){
		form1.action = 'project/delete/'+pid;
		form1.submit();
	});
}

$(function(){
	//显示Menu索引
	$(".menu_index").show();
});
</script>
</layout:override>

<layout:override name="body">

	<form name="form1" action="" method="post"  class="form-inline" role="form">
		<div id="tools">
			<div class="tools_l">
				<span class="new_page">项目管理</span>
			</div>
			<div class="tools_r">
				<%@include file="/static/component/include/menu.jsp"%>
			</div>
		</div>
		
		<div class="tableSearch">
			<div class="form-group">
			   <label class="sr-only">项目名称</label>
			   <input type="text" class="form-control"  name="attr.name" value="${attr.name }" placeholder="请输入项目名称">
			</div>
			<button type="button" class="btn btn-default" onclick="oper_list();" name="search">
			 		<span class="glyphicon glyphicon-search"></span> 查 询
			</button>
			<button type="button" class="btn btn-default" onclick="resetForm();">
			 		<span class="glyphicon glyphicon-refresh"></span> 重 置
			</button>
			<button type="button" class="btn btn-default" onclick="oper_add();">
			 		<span class="glyphicon glyphicon-plus"></span> 新 增
			</button>
		</div>
		
		<!-- 数据列表 -->
		<table class="table table-striped table-bordered table-hover">
			<thead>
				<tr>
					<th>序号</th>
					<th>项目名称</th>
					<th>项目说明</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${requestScope.page.list}" var="item" varStatus="row">
				<tr>
					<td>${row.count }</td>
					<td>${item.name}</td>
					<td title="${item.remark}">
					<c:choose>
				          <c:when test="${fn:length(item.remark) > 20}">
				              <c:out value="${fn:substring(item.remark, 0, 20)}..." />
				          </c:when>
				         <c:otherwise>
				            <c:out value="${item.remark}" />
				          </c:otherwise>
				    </c:choose>
					</td>
					<td>
					<a href="javascript:void(0);" class="btn btn-sm btn-success" onclick="oper_view(${item.id});">查看</a> 
					<a href="javascript:void(0);" class="btn btn-sm btn-primary" onclick="oper_edit(${item.id});">修改</a> 
					<a href="javascript:void(0);" class="btn btn-sm btn-danger" onclick="oper_delete(${item.id});">删除</a></td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
		<%@include file="/static/component/include/paginator.jsp" %>
	</form>
</layout:override>

<%@include file="/static/common/_layout.jsp" %>
