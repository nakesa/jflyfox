<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@include file="/static/component/include/head.jsp"%>
<%@include file="/static/component/include/ueditor.jsp"%>
<script type="text/javascript">
	function oper_save() {
		form1.action = "column/save_content/"+form1["model.id"].value;
		form1.submit();
	}
	
	$(document).ready(function(){
		UE.getEditor('editor');
	});
</script>
</head>
<body>
	<form name="form1" action="" method="post">
		<input type="hidden" name="model.id" value="${model.id}" />
		<div id="tools">
			<div class="tools_l">
				<span class="new_page">栏目内容</span>
				<a href="javascript:void(0);" onclick="oper_save();"><div class="center"></div>保存</a>
				<a href="javascript:void(0);" onclick="closeIframe();"><div class="getarea"></div>关闭</a>
			</div>
		</div>
		<!-- 数据列表 -->
		<div>
			<script id="editor" type="text/plain" name="model.content" style="width:100%;height:300px;">${model.content}</script>
		</div>
		<div id="bottom">
			<input type="button" class="btn1" value="保 存" onclick="oper_save();" />
			<input type="button" class="btn1" value="关 闭" onclick="closeIframe();" />
		</div>
	</form>
</body>

</html>
