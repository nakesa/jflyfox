<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="jsp_layout.tld" prefix="layout"%>

<layout:override name="head">
<%@include file="/static/component/include/head.jsp"%>
<% 
	String modelName = "";
	String ControllerName = "";
%>
<script type="text/javascript">
paginator = function(page){
	oper_list();
};

function oper_list(){
	form1.action = 'column/list';
	form1.submit();
}

function oper_view(pid){
	Iframe('column/view/'+pid,350,500,'栏目查看',false,false,false,EmptyFunc);
}

function oper_add(){
	var url = 'column/add/'+form1["attr.id"].value;
	Iframe(url,400,600,'栏目添加');
}

function oper_edit(pid){
	var url = 'column/edit/'+pid;
	Iframe(url,400,600,'栏目修改');
}

function oper_edit_content(pid){
	var url = 'column/edit_content/'+pid;
	IframeMax(url,'栏目内容修改',true);
}

function oper_delete(pid){
	Confirm("确认要删除该栏目信息？",function(){
		form1.action = 'column/delete/'+pid;
		form1.submit();
	});
}

</script>
</layout:override>

<layout:override name="body">
	<form name="form1" action="" method="post"  class="form-inline" role="form">
		<input type="hidden" name="attr.id" value="${attr.id }" /> 
		
		<div class="tableSearch">
			<div class="form-group">
			   <label>题目</label>
			   <input type="text" class="form-control"  name="attr.title" value="${attr.title }" placeholder="请输入题目">
			</div>
			<div class="form-group">
			   <label  class="control-label">类型</label>
			   <select name="attr.type" class="form-control" style="width: 100px;">
						<option value="">--请选择--</option>
						${flyfox:dictSelect('columnType',attr.type)}
				</select> 
			</div>
			<button type="button" class="btn btn-default" onclick="form1.pageNo.value=1;oper_list();" name="search">
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
		<table  class="table table-striped table-bordered table-hover">
			<thead>
				<tr>
					<th>序号</th>
					<th>题目</th>
					<th>类型</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${requestScope.page.list}" var="item" varStatus="row">
				<tr>
					<td width="50">${row.count }</td>
					<td>${item.title}</td>
					<td>
					${flyfox:dictValue(item.type) }
					</td>
					<td align="center">
						<a href="javascript:void(0);" class="btn btn-sm btn-success" onclick="oper_view(${item.id});">查看</a> 
						<a href="javascript:void(0);" class="btn btn-sm btn-primary" onclick="oper_edit(${item.id});">修改</a> 
						<a href="javascript:void(0);" class="btn btn-sm btn-danger" onclick="oper_delete(${item.id});">删除</a>
						<a href="javascript:void(0);" class="btn btn-sm btn-primary" onclick="oper_edit_content(${item.id});">添加内容</a>
						<a href="column/view_content/${item.id}" class="btn btn-sm  btn-default"  target="_blank">查看内容</a>
					</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
		<%@include file="/static/component/include/paginator.jsp"%>
	</form>
</layout:override>
<%@include file="/static/common/_layout.jsp" %>
