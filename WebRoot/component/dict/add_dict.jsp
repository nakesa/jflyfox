<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="/resources/include/head.jsp"%>

<form action="save" method="post">
	<input type="hidden" name="dict_id" value="${dict.dict_id}" />
	<fieldset class="solid">
		<legend>
			创建数据字典值
		</legend>
		<div>
			<label>
				类型
			</label>
			<input type="text" name="dict.dict_type" <c:if test="${!empty dict}">readonly="readonly"</c:if> value="${dict.dict_type}" />
			${dict_typeMsg}
		</div>
		<div>
			<label>
				值
			</label>
			<input type="text" name="dict.dict_value" value="${dict.dict_value}" />
			${dict_nameMsg}
		</div>
		<div>
			<label>
				code
			</label>
			<input type="text" name="dict.dict_code" value="${dict.dict_code}" />
			${dict_nameMsg}
		</div>
		<div>
			<label>
				备注
			</label>
			<textarea name="dict.remark" cols="40" rows="10">${dict.remark}</textarea>
			${remarkMsg}
		</div>
		<div style="text-align: center;">
			<input value="提交" type="submit" />
			<input value="关闭" type="button" onclick="closeIframe();" />
		</div>
	</fieldset>
</form>
<%@include file="/resources/include/foot.jsp"%>