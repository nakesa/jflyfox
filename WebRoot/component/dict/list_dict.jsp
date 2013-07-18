<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="bjepn.xtgl.po.SY_D_ParaTypePO"%>
<%@ page import="bjepn.xtgl.po.SY_D_ParaPO"%>
<%@ page import="bjepn.xtgl.service.ParaSer"%>
<%@page import="bjepn.xtgl.po.SY_V_ParaPO"%>

<jsp:useBean class="bjepn.xtgl.form.FormPara" id="pForm" scope="request" />
<jsp:useBean class="bjepn.base.bo.Result" id="rs" scope="request" />

<%@ include file="/includes/includeHead.jsp"%>
<script language="javascript">
// 翻页
function turnPage()  {
	form1.task.value="listSY_D_Para";
	form1.action="/xtgl/para";
    form1.pageno2.value = includeTurnPageForm.queryPageNo.value;
    form1.pageno.value = includeTurnPageForm.queryPageNo.value;
    form1.submit();
}
// 修改或者增加参数
function addSY_D_Para(paraid){
	form1.paraid.value = paraid;
	form1.task.value="addSY_D_Para";
	form1.action="/xtgl/para";
    form1.submit();
}
// 改变参数的状态
function delSY_D_Para(paraid){
	if(confirm("您确定要删除该参数值吗?")){
		form1.task.value="changeSY_D_Para";
		form1.action="/xtgl/para?task=delSY_D_Para&mids="+paraid;
	    form1.submit();
	}
}
</script>
<%
	IncludeTurnPage turnPage = new IncludeTurnPage(rs, "turnPage()", out);
	java.lang.String strIncludeTableTitle = "数据字典设置";
	java.lang.String strIncludeTableDesc = "";
	java.lang.String strIncludeTableTurnPage = turnPage.displayTurnPagePerPage();
%>
<!-- tab菜单 -->
<jsp:include page="/pages/xtgl/para/paratypeTab.jsp"></jsp:include>
<form name="form1" method="post" action="/xtgl/para">
	<!-- 查询隐藏的条件 -->
	<input type="hidden" name="pageno" value="<%=pForm.getPageno()%>"/>
	<input type="hidden" name="pageSize" value="<%=pForm.getPageSize() %>"/>
	<input type="hidden" name="pageno2" value="<%=pForm.getPageno2() %>"/>
	
	<input type="hidden" name="task"/>
	<input type="hidden" name="paraid" value=""/>
	<table cellpadding="0" cellspacing="2" style="border:1px solid #CCCCC; background:"/>
	<tr> 
		<td style="text-align: right">
			&nbsp;&nbsp;参数类别名称
		</td>
		<td style="text-align: left">
			<input type="text" name="s_paratypename" size="15" search="true"
				value="<%=StringUtil.nvl2(pForm.getS_paratypename()) %>"/>
		</td>
		<td style="text-align: right">
			&nbsp;&nbsp;参数值
		</td>
		<td style="text-align: left">
			<input type="text" name="s_paravalue" size="15" search="true"
				value="<%=StringUtil.nvl2(pForm.getS_paravalue()) %>"/>
		</td> 
		<td style="text-align: left" colspan="5">
			&nbsp;&nbsp;<input type="button" value="查 询" name="search" 
				onclick="queryByCondition('/xtgl/para?task=listSY_D_Para&pageno=1',document.form1)"/>
			&nbsp;&nbsp;&nbsp;<input type="button" value="重 置" name="reset"
				onclick="resetQuery(document.form1)"/>
			&nbsp;&nbsp;<input type="button" value="增加参数值" name="add" 
				onclick="addSY_D_Para('0')"/> 
		</td>
	</tr>
	</table>
	<%@ include file="/includes/includeTableTitle.jsp" %>
	<table class="tableList">
	<tr class="trListTitle">
		<td>
			序号
		</td>
		<td>
			操作
		</td> 
		<td>
			参数类别名称
		</td>
		<td>
			参数值
		</td>
		<td>
			参数编号
		</td>
		<td>
			保存代码
		</td>
		<td>
			排序号
		</td>
		<td>
			备注
		</td>
	</tr>
	<%    
		ESSPO[] bean = rs.getPos();
        if(bean != null && bean.length > 0){
	    	SY_V_ParaPO po = null;
	    	for(int i = 0; i < bean.length; i++){
		    	po = (SY_V_ParaPO)bean[i];
    %>
	<tr>
		<td class="tdListCenter trListTitle" width="5%">
			<%=i+1%>
		</td>
		<td width="10%">
			<% if(Commons.isAdmin(userContext)||(userContext.getUserID()==po.getCreate_userid() && po.getIsdisabeld()!=Constants.yes)){ %>
			<a href="javascript:addSY_D_Para('<%= po.getParaid() %>')"><img
				src="/resources/images/modify.gif" border="0" alt="修改" align="absmiddle"></a>
			&nbsp;&nbsp;
			<%} 
				if(Commons.isAdmin(userContext)||(userContext.getUserID()==po.getCreate_userid() && po.getCannotdelete_pt()!=Constants.yes)){
			%>	
			<a  href="javascript:delSY_D_Para('<%= po.getParaid()%>')"><img
					src="/resources/images/disabled.gif"
					border="0" alt="删除" align="absmiddle" ></a>
			<% } %>
		</td> 
		<td>
			<%=StringUtil.nvl(po.getParatypename(),"&nbsp;") %>
		</td>
		<td>
			&nbsp;&nbsp;<%=StringUtil.nvl2(po.getParavalue())%>
		</td>
		<td><%=po.getParaid()%></td>
		<td><%=StringUtil.nvl(po.getParacode(),"&nbsp;")%></td>
		<td><%=po.getSort()%></td>
		<td  title="<%=StringUtil.nvl2(po.getPararemark()) %>">
			&nbsp;&nbsp;<%=StringUtil.nvl2(StringUtil.suojin(po.getPararemark(),20))%>
		</td>
	</tr>
	<%
         	}
      	} else {
    %>
	<tr>
		<td colspan="99">
			<%=Constants.noMessageString %>
		</td>
	</tr>
	<%     
		}
	%>
	</table>
	</form>
<%@ include file="/includes/includeFoot.jsp"%>
