<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<%@include file="/static/component/include/head.jsp"%>
    <%@include file="/static/component/include/ztree.jsp" %>
  <SCRIPT type="text/javascript" >
  <!--
	var zTree;
	var demoIframe;

	var setting = {
		view: {
			//dblClickExpand: true, // 双击展开
			dblClickExpand:dblClickExpand,
			showLine: true,  		//虚线
			selectedMulti: false
		},
		data: {
			simpleData: {
				enable:true,
				idKey: "id",
				pIdKey: "pId",
				rootPId: ""
			}
		},
		callback: {
			beforeClick: function(treeId, treeNode) {
				var zTree = $.fn.zTree.getZTreeObj("tree");
				if (treeNode.isParent) {
					$("#testIframe").attr("src","http://www.baidu.com/s?wd="+treeNode.name);
					return false;
				} else {
					$("#testIframe").attr("src","http://www.baidu.com/s?wd="+treeNode.name);
					return true;
				}
			}
		}
	};

	function dblClickExpand(treeId, treeNode) {
		return treeNode.level > 0;
	}
	
	var zNodes =[
		{id:1, pId:0, name:"栏目", open:true},
		{id:3, pId:1, name:"[exedit] 编辑功能 演示", open:true},
		{id:301, pId:3, name:"拖拽 节点 基本控制"},
		{id:302, pId:3, name:"拖拽 节点 高级控制"},
		{id:303, pId:3, name:"用 zTree 方法 移动 / 复制 节点"},
		{id:304, pId:3, name:"基本 增 / 删 / 改 节点"},
		{id:305, pId:3, name:"高级 增 / 删 / 改 节点"},
		{id:306, pId:3, name:"用 zTree 方法 增 / 删 / 改 节点"},
		{id:307, pId:3, name:"异步加载 & 编辑功能 共存"},
		{id:308, pId:3, name:"多棵树之间 的 数据交互"},

		{id:4, pId:1, name:"大数据量 演示", open:true},
		{id:401, pId:4, name:"一次性加载大数据量"},
		{id:402, pId:4, name:"分批异步加载大数据量"},
		{id:403, pId:4, name:"分批异步加载大数据量"},

		{id:5, pId:1, name:"组合功能 演示", open:true},
		{id:501, pId:5, name:"冻结根节点"},
		{id:502, pId:5, name:"单击展开/折叠节点"},
		{id:503, pId:5, name:"保持展开单一路径"},
		{id:504, pId:5, name:"添加 自定义控件"},
		{id:505, pId:5, name:"checkbox / radio 共存"},
		{id:506, pId:5, name:"左侧菜单"},
		{id:513, pId:5, name:"OutLook 样式的左侧菜单"},
		{id:507, pId:5, name:"下拉菜单"},
		{id:509, pId:5, name:"带 checkbox 的多选下拉菜单"},
		{id:510, pId:5, name:"带 radio 的单选下拉菜单"},
		{id:508, pId:5, name:"右键菜单 的 实现"},
		{id:511, pId:5, name:"与其他 DOM 拖拽互动"},
		{id:512, pId:5, name:"异步加载模式下全部展开"},

		{id:6, pId:1, name:"其他扩展功能 演示", open:true},
		{id:601, pId:6, name:"隐藏普通节点"},
		{id:602, pId:6, name:"配合 checkbox 的隐藏"},
		{id:603, pId:6, name:"配合 radio 的隐藏"}
	];

	$(document).ready(function(){
		var t = $.fn.zTree.init($("#tree"), setting, zNodes);
		var zTree = $.fn.zTree.getZTreeObj("tree");
		zTree.selectNode(zTree.getNodeByParam("id", 508));
	});

  //-->
  </SCRIPT>
  <style type="text/css">
.ztree li span.button.switch.level0 {visibility:hidden; width:1px;}
.ztree li ul.level0 {padding:0; background:none;}
	</style>
 </HEAD>

<BODY>
<div style="height: 100%;width: 100%;overflow: hidden;">
	<div style="height: 100%;width: 25%;background-color: #DDD;float: left;">
		<ul id="tree" class="ztree" style="width:260px; overflow:auto;"></ul>
	</div>
	<div style="height: 100%;width: 75%;float: left;">
		<IFRAME ID="testIframe" Name="testIframe" FRAMEBORDER=0 SCROLLING=AUTO width=100%  height=100% SRC=""></IFRAME>
	</div>
</div>

</BODY>
</HTML>
