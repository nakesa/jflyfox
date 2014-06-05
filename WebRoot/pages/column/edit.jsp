<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="jsp_layout.tld" prefix="layout"%>

<layout:override name="head">
<%@include file="/static/component/include/head.jsp"%>
<%@include file="/static/component/include/my97.jsp"%>
<%@include file="/static/component/include/ztree.jsp" %>
<script type="text/javascript">
	<!--
	function oper_save() {
		form1.action = "column/save/"+form1["model.id"].value;
		form1.submit();
	}
	
	var setting = {
		view: {
			dblClickExpand: false
		},
		data: {
			simpleData: {
				enable: true
			}
		},
		callback: {
			beforeClick: beforeClick,
			onClick: onClick
		}
	};

	var zNodes = ${nodes};

	function beforeClick(treeId, treeNode) {
		if(treeNode.id==$("[name='model.id']").val()) {
			alert('不能选择自己~！~'); 
			return false;
		}
		return treeNode;
	}
	
	function onClick(e, treeId, treeNode) {
		var zTree = $.fn.zTree.getZTreeObj("treeParent"),
		nodes = zTree.getSelectedNodes(),
		v = "";
		vals = "";
		levels = "";
		nodes.sort(function compare(a,b){return a.id-b.id;});
		for (var i=0, l=nodes.length; i<l; i++) {
			v += nodes[i].name + ",";
			vals += nodes[i].id + ",";
			levels += (nodes[i].level+2) + ",";
		}
		if (v.length > 0 ) v = v.substring(0, v.length-1);
		if (vals.length > 0 ) vals = vals.substring(0, vals.length-1);
		if (levels.length > 0 ) levels = levels.substring(0, levels.length-1);
		$("#parentSel").val(v);
		$("#parentVal").val(vals);
		$("#parentLevel").val(levels);
		hideMenu();
	}

	function showMenu() {
		var cityObj = $("#parentSel");
		var cityOffset = $("#parentSel").offset();
		$("#menuContent").css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");

		$("body").bind("mousedown", onBodyDown);
	}
	function hideMenu() {
		$("#menuContent").fadeOut("fast");
		$("body").unbind("mousedown", onBodyDown);
	}
	function onBodyDown(event) {
		if (!(event.target.id == "menuBtn" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length>0)) {
			hideMenu();
		}
	}

	$(document).ready(function(){
		$.fn.zTree.init($("#treeParent"), setting, zNodes);
	});
	//-->
</script>
</layout:override>

<layout:override name="body">
	<form name="form1" action="" method="post" enctype="multipart/form-data">
		<input type="hidden" name="model.id" value="${model.id}" />
		<!-- 数据列表 -->
		<table class="table">
			<tr>
				<td>父节点</td>
				<td>
				<input id="parentLevel" type="hidden" name="model.level" value="${model.level}"/>
				<input id="parentVal" type="hidden" name="model.parent_id" value="${model.parent_id}"/>
				<input id="parentSel" type="text" readonly style="width:120px;" value="${model.parent_val}"  />
				<c:choose>
					<c:when test="${model.parent_id > 0 or empty model.id }">
						&nbsp;<a id="menuBtn" href="javascript:void(0);" onclick="showMenu(); return false;">选择</a>
					</c:when>
				</c:choose>
				
				<div id="menuContent" class="menuContent" style="display:none; position: absolute;background-color: #DDD;border: 1px solid black;max-height: 300px;overflow-y: auto;overflow-x: hidden;">
					<ul id="treeParent" class="ztree" style="margin-top:0; width:160px;"></ul>
				</div>
				</td>
			</tr>
			<tr>
				<td>题目<c:if test="${!empty model.id}">(${model.id})</c:if></td>
				<td><input type="text" name="model.title"
					value="${model.title}" /></td>
			</tr>
			<tr>
				<td>类型</td>
				<td>
					<select name="model.type" style="width: 100px;">
						${flyfox:dictSelect('columnType',model.type)}
					</select>
				</td>
			</tr>
			<tr>
				<td>排序号</td>
				<td><input type="text" name="model.sort" value="${model.sort}" /></td>
			</tr>
			<tr>
				<td>图片</td>
				<td>
					<c:if test="${!empty model.image_url }">
						<a href="download/image_url/${model.image_url }" target="_blank" style="text-decoration:none;">
							<img alt="图片" title="点击浏览" src="download/image_url/${model.image_url }" width="40" height="40">
						</a>
						<br />
					</c:if>
					<input type="file" accept="image/*" name="model.image_url" />
				</td>
			</tr>
			<tr>
				<td>发布时间</td>
				<td>
					<input type="text" name="model.publish_time"
					value="${model.publish_time}" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"  />
				</td>
			</tr>
			<tr>
				<td>发布人</td>
				<td>
					<input type="text" name="model.publish_user" value="${model.publish_user}" />
				</td>
			</tr>
			<tr>
				<td>开始时间</td>
				<td>
				<input type="text" name="model.start_time" value="${model.start_time}"  id="startTime" class="Wdate" 
					onfocus="var endTime=$dp.$('endTime');WdatePicker({onpicked:function(){endTime.focus();},dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endTime\')}'})"/>
				</td>
			</tr>
			<tr>
				<td>结束时间</td>
				<td>
				<input type="text" name="model.end_time" value="${model.end_time}"  id="endTime" class="Wdate"
					onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'startTime\')}'})" />
				</td>
			</tr>
			<tr>
				<td></td>
				<td>
				<button class="btn btn-default" onclick="oper_save();">保 存</button>
				<button class="btn btn-default" onclick="closeIframe();">关 闭</button>
				</td>
			</tr>
		</table>
	</form>
</layout:override>

<%@include file="/static/common/_layout.jsp" %>
