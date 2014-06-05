<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="jsp_layout.tld" prefix="layout"%>

<layout:override name="head">
<%@include file="/static/component/include/head.jsp"%>
</layout:override>

<layout:override name="body">
	<form name="form1" action="" method="post">
		<!-- 数据列表 -->
		<table class="table">
			<tr>
				<td>题目(${model.id})</td>
				<td>${model.title}</td>
			</tr>
			<tr>
				<td>类型</td>
				<td>
						${flyfox:dictValue(model.type)}
				</td>
			</tr>
			<tr>
				<td>排序号</td>
				<td>${model.sort}</td>
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
				</td>
			</tr>
			<tr>
				<td>发布时间</td>
				<td>
					${model.publish_time}
				</td>
			</tr>
			<tr>
				<td>发布人</td>
				<td>
					${model.publish_user}
				</td>
			</tr>
			<tr>
				<td>开始时间</td>
				<td>
					${model.start_time}
				</td>
			</tr>
			<tr>
				<td>结束时间</td>
				<td>
					${model.end_time}					
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<button class="btn btn-default" onclick="closeIframe();">关 闭</button>
				</td>
			</tr>
		</table>
	</form>
</layout:override>

<%@include file="/static/common/_layout.jsp" %>