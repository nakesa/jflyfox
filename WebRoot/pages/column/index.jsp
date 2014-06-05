<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="jsp_layout.tld" prefix="layout"%>

<layout:override name="head">
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
					$("#testIframe").attr("src","column/list?attr.id="+treeNode.id);
					return false;
				} else {
					$("#testIframe").attr("src","column/list?attr.id="+treeNode.id); //+treeNode.name
					return true;
				}
			}
		}
	};

	function dblClickExpand(treeId, treeNode) {
		return treeNode.level > 0;
	}
	
	var zNodes = ${nodes};
	
	$(document).ready(function(){
		var t = $.fn.zTree.init($("#tree"), setting, zNodes);
		var zTree = $.fn.zTree.getZTreeObj("tree");
		zTree.selectNode(zTree.getNodeByParam("id", 1));
		//显示Menu索引
		$(".menu_index").show();
	});
	
	function oper_update_cache(){
		ajax_post("update_cache","",function(data){
			if(data==1) Alert("更新成功");
			else Alert("更新失败");
		});
	}
  //-->
  </SCRIPT>
  <style type="text/css">
.ztree li span.button.switch.level0 {visibility:hidden; width:1px;}
.ztree li ul.level0 {padding:0; background:none;}
	</style>
</layout:override>

<layout:override name="body">
<div style="height: 100%;width: 100%;overflow: hidden;">
	<div id="tools">
			<div class="tools_l">
				<span class="new_page">栏目管理</span>
				<a href="javascript:void(0);" onclick="oper_update_cache();"><div class="area_path"></div>更新缓存</a>
			</div>
			<div class="tools_r">
				<%@include file="/static/component/include/menu.jsp"%>
			</div>
	</div>
	<div style="height: 90%;width: 20%;float: left;overflow-x:auto ">
		<ul id="tree" class="ztree" style="width:260px; overflow:auto;"></ul>
	</div>
	<div style="height: 90%;width: 80%;float: left; ">
		<IFRAME ID="testIframe" Name="testIframe" FRAMEBORDER=0 SCROLLING="no" width=100%  height=95% SRC="column/list?attr.id=1"></IFRAME>
	</div>
</div>
</layout:override>

<%@include file="/static/common/_layout.jsp" %>
