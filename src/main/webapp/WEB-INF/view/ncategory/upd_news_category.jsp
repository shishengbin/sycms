<%@ page language="java" import="java.util.*" pageEncoding="utf-8" trimDirectiveWhitespaces="true"%>
<%@ include file="../pageControl/jstlImport.jsp" %>
<div class="pageContent">
	<form:form method="post" action=""  commandName="newscategory"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this, navTabAjaxDone)">
		<p class="contentTitle" style="font-size: 15px;color: #09c;font-weight: bold;">
			修改文章分类
		</p>
		<div class="pageFormContent" layoutH="98" style="margin-left: 20px">
			<form:hidden path="nid"/>
			<br />
			<div class="unit">
				<label>分类名称：</label>
				<form:input path="newsName" size="40"  class="required"  />
			</div>
			<div class="divider"></div>
			<div class="unit">
				<label>分类描述：</label>
				<form:input  path="newsRemark" size="40"  value="" />&nbsp;
				<span class="info">30个字简短分类描述，可为空 </span>
			</div>
			<div class="divider"></div>
			<div class="unit">
				<label>是否显示：</label>
				<form:radiobutton path="newsStatus" value="0" />是<br/>
				<form:radiobutton path="newsStatus" value="1"/>否
			</div>
		</div>
		<c:import url="../pageControl/submitButton.jsp"></c:import>
	</form:form>
</div>