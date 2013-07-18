<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="/resources/include/head.jsp"%>
<form action="save_main" method="post">
	<fieldset class="solid">
		<legend>
			创建数据字典
		</legend>
		<div>
			<label>
				类型
			</label>
			<input type="text" name="dictMain.dict_type" <c:if test="${!empty dictMain}">readonly="readonly"</c:if> value="${dictMain.dict_type}" />
			${dict_typeMsg}
		</div>
		<div>
			<label>
				名称
			</label>
			<input type="text" name="dictMain.dict_name" value="${dictMain.dict_name}" />
			${dict_nameMsg}
		</div>
		<div>
			<label>
				备注
			</label>
			<textarea name="dictMain.remark" cols="40" rows="10">${dictMain.remark}</textarea>
			${remarkMsg}
		</div>
		<div style="text-align: center;">
			<input value="提交" type="submit" />
			<input value="关闭" type="button" onclick="closeIframe();" />
		</div>
	</fieldset>
</form>
<%@include file="/resources/include/foot.jsp"%>