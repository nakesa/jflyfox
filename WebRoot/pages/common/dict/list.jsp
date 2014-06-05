<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="jsp_layout.tld" prefix="layout"%>

<layout:override name="head">
<%@include file="/static/component/include/head.jsp"%>
<script type="text/javascript">
paginator = function(page){
	oper_list();
};

function oper_list(){
	form1.action = 'dict/list';
	form1.submit();
}

function oper_view(pid){
	Iframe('dict/view/'+pid,300,300,'任务状态查看',false,false,false,EmptyFunc);
}

function oper_add(){
	var url = 'dict/add'+'?dict_type='+form1["attr.dict_type"].value;
	Iframe(url,350,300,'数据字典添加');
}

function oper_edit(pid){
	var url = 'dict/edit/'+pid+'?dict_type='+form1["attr.dict_type"].value;
	Iframe(url,350,300,'数据字典修改');
}

function oper_delete(pid){
	Confirm("确认要删除该数据字典信息？",function(){
		form1.action = 'dict/delete/'+pid;
		form1.submit();
	});
}

function oper_edit_dict(){
	var url = 'dict/edit_dict/'+form1["attr.dict_type"].value;
	Iframe(url,350,250,'类型操作');
}

$(function(){
	//显示Menu索引
	$(".menu_index").show();
});
</script>
</layout:override>


<layout:override name="body">
<form name="form1" action="" method="post" class="form-inline" role="form">
		<div id="tools">
			<div class="tools_l">
				<span class="new_page">数据管理</span>
			</div>
			<div class="tools_r">
				<%@include file="/static/component/include/menu.jsp"%>
			</div>
		</div>
		
		<div class="tableSearch">
			<div class="form-group">
			   <label  class="control-label">类型</label>
			   <select name="attr.dict_type" class="form-control" style="width: 100px;">
						<option value="">请选择</option> ${optionList }
				</select> 
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
			<button type="button" class="btn btn-default" onclick="oper_edit_dict();">
			 		<span class="glyphicon glyphicon-edit"></span> 操作类型
			</button>
		</div>
		
		<!-- 数据列表 -->
		<table class="table table-striped table-bordered table-hover">
			<thead>
				<tr>
					<th>序号</th>
					<th>类型</th>
					<th>名称</th>
					<th>编号</th>
					<th>排序号</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${requestScope.page.list}" var="item" varStatus="row">
				<tr>
					<td>${row.count }</td>
					<td>${item.dict_name}</td>
					<td>${item.detail_name}</td>
					<td>${item.detail_code}</td>
					<td>${item.detail_sort}</td>
					<td>
						<a href="javascript:void(0);" class="btn btn-sm btn-success" onclick="oper_view(${item.detail_id});">查看</a> 
						<a href="javascript:void(0);" class="btn btn-sm btn-primary" onclick="oper_edit(${item.detail_id});">修改</a> 
						<a href="javascript:void(0);" class="btn btn-sm btn-danger" onclick="oper_delete(${item.detail_id});">删除</a></td>
					</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
		<%@include file="/static/component/include/paginator.jsp"%>
	</form>
</body>
</layout:override>

<%@include file="/static/common/_layout.jsp" %>