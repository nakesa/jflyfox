<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>  
	<style>
		.newtabs {
			padding: 5px 0px 5px 10px;
			margin-left: 0px;
			margin-top: 0px;
			margin-bottom: 2px;
			font-size: 12px;
			font-weight:bold;
			list-style-type: none;
			text-align: left;
			border-bottom:1px solid #93bee2; 
		}
		.newtabs li {
			display: inline;
			margin: 0;
		}
		
		.newtabs li a {
			text-decoration: none;
			padding: 5px 4px 5px 4px;
			margin: 0px -1px 0px -1px;
			border: 1px solid #93bee2;
			color: #003399; 
			background: #e8f4ff url(/images/shade.gif) top left repeat-x;
		} 
		
		.newtabs li a:visited {
			text-decoration: none;
			color: #003399;
		} 
		
		.newtabs li.selected a {  
			background-image: url(/images/shadeactive.gif);
			border-bottom:  1px solid #FFFFFF;
			/*background: #FFCC00; */
			font-weight:bold;
			color:#003399;
			background: #FFFFFF;
		}
		
		.newtabs li.selected a:hover { /*selected main tab style */
			text-decoration: none;
		}
	</style> 
	<script type="text/javascript">
		//tab菜单 
			var liTab = $("input[name='tabId']").val(); 
			$("#li"+liTab).attr('class','selected');
			function listDictionay(task,tabId){ 
				$("#li"+tabId).attr('class','selected');
				form1.task.value=task;
				form1.action="/xtgl/para?pageno=1&pageSize=15&tabId="+tabId;
				form1.submit();
			}  
	</script>
  </head> 
 <body> 
 	<input type="hidden" name="tabId" value=""/> 
     <ul class="newtabs">
		<li id="li1">
			<a href="javascript:void(0)" onclick="queryByCondition('/xtgl/para?task=listSY_D_ParaType&pageno=1',document.form1)">参数类别管理</a>		
		</li>
		<li id="li2">
			<a href="javascript:void(0)" onclick="queryByCondition('/xtgl/para?task=listSY_D_Para&pageno2=1',document.form1)">参数值管理</a>
		</li>
	</ul>
  </body>
</html>
