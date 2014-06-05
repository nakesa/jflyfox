<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="jsp_layout.tld" prefix="layout"%>

<layout:override name="head">
<%@include file="/static/component/include/head.jsp"%>

<script type="text/javascript">
paginator = function(page){
	oper_list();
};

function oper_list(){
	form1.action = 'user/list';
	form1.submit();
}

function oper_view(pid){
	var url = 'user/view/'+pid;
	Iframe('user/view/'+pid,300,240,'用户查看',false,false,false,EmptyFunc);
}

function oper_add(){
	var url = 'user/add';
	Iframe(url,350,300,'用户添加');
}

function oper_edit(pid){
	var url = 'user/edit/'+pid;
	Iframe(url,350,300,'用户修改');
}

function oper_delete(pid){
	Confirm("确认要删除该用户信息？",function(){
		form1.action = 'user/delete/'+pid;
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
				<span class="new_page">用户管理</span>
			</div>
			<div class="tools_r">
				<%@include file="/static/component/include/menu.jsp"%>
			</div>
		</div>
		
		<div class="tableSearch">
			<div class="form-group">
			   <label class="sr-only">登陆名</label>
			   <input type="text" class="form-control"  name="attr.username" value="${attr.username }" placeholder="请输入登陆名">
			</div>
			<div class="form-group">
			  <label class="sr-only" for="exampleInputPassword2">真实姓名</label>
			  <input type="text" class="form-control"  name="attr.realname" value="${attr.realname }" placeholder="请输入真实姓名">
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
					<th>登陆名</th>
					<th>真实姓名</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${requestScope.page.list}" var="item" varStatus="row">
				<tr>
					<td>${row.count }</td>
					<td>${item.username}</td>
					<td>${item.realname}</td>
					<td>
					<a href="javascript:void(0);" class="btn btn-sm btn-success" onclick="oper_view(${item.userid});">查看</a> 
					<a href="javascript:void(0);" class="btn btn-sm btn-primary" onclick="oper_edit(${item.userid});">修改</a> 
					<a href="javascript:void(0);" class="btn btn-sm btn-danger" onclick="oper_delete(${item.userid});">删除</a></td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
		<%@include file="/static/component/include/paginator.jsp" %>
	</form>
</layout:override>

<%@include file="/static/common/_layout.jsp" %>
