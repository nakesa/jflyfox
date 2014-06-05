<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="jsp_layout.tld" prefix="layout"%>

<layout:override name="head">
<%@include file="/static/component/include/head.jsp"%>
<%@include file="/static/component/include/my97.jsp"%>

<script type="text/javascript">
paginator = function(page){
	oper_list();
};

function oper_list(){
	form1.action = 'money/list';
	form1.submit();
}

function oper_view(pid){
	var url = 'money/view/'+pid;
	Iframe('money/view/'+pid,300,340,'金额查看',false,false,false,EmptyFunc);
}

function oper_add(){
	var url = 'money/add';
	Iframe(url,350,450,'金额添加');
}

function oper_edit(pid){
	var url = 'money/edit/'+pid;
	Iframe(url,350,450,'金额修改');
}

function oper_delete(pid){
	Confirm("确认要删除该金额信息？",function(){
		form1.action = 'money/delete/'+pid;
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
				<span class="new_page">金额管理</span>
			</div>
			<div class="tools_r">
				<%@include file="/static/component/include/menu.jsp"%>
			</div>
		</div>
		
		<div class="tableSearch">
			<div class="form-group">
			   <label  class="control-label">项目名称</label>
			   <select name="attr.project_id" class="form-control" style="width: 100px;">
						<option value="">请选择</option> ${optionList }
				</select> 
			</div>
			<div class="form-group">
				<label class="sr-only">类型</label>
				<select name="attr.type" class="form-control" >
					<option value="">请选择</option> ${flyfox:dictSelect('moneyType',attr.type)}
				</select>
			</div>
			<div class="form-group">
				<label class="sr-only">区间</label>
				<input type="text" name="pay_time_start" class="form-control"
					value="${attr.pay_time_start}" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"placeholder="开始时间" size="8"  />
				-<input type="text" name="pay_time_end" class="form-control"
					value="${attr.pay_time_end}" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"placeholder="结束时间" size="8"  />
			</div>
			<div class="form-group">
			  <label class="sr-only">费用概要</label>
			  <input type="text" class="form-control"  name="attr.name" value="${attr.name }" placeholder="请输入费用概要">
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
					<th>类型</th>
					<th>费用概要</th>
					<th>费用金额</th>
					<th>时间</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${requestScope.page.list}" var="item" varStatus="row">
				<tr>
					<td>${row.count }</td>
					<td>${item.project_name}</td>
					<td>${flyfox:dictValue(item.type) }</td>
					<td>${item.name}</td>
					<td>${item.amount}</td>
					<td>${item.pay_time}</td>
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
