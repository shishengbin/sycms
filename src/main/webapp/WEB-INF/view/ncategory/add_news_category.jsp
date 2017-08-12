<%@ page language="java" import="java.util.*" pageEncoding="utf-8" trimDirectiveWhitespaces="true"%>
<%@ include file="../pageControl/jstlImport.jsp" %>
<div class="pageContent">
	<form:form method="post" action=""  commandName="newscategory"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this, navTabAjaxDone)">
		<p class="contentTitle" style="font-size: 15px;color: #09c;font-weight: bold;">
			添加文章分类
		</p>
		<div class="pageFormContent" layoutH="98" style="margin-left: 20px">
			<br />
			<div class="unit">
				<label>分类名称：</label>
				<input type="text" name="newsName" size="40"  class="required" value="" />
			</div>
			<div class="divider"></div>
			<div class="unit">
				<label>分类描述：</label>
				<input type="text" name="newsRemark" size="40"  value="" />&nbsp;
				<span class="info">30个字简短分类描述，可为空 </span>
			</div>
			<div class="divider"></div>
			<div class="unit">
				<label>是否显示：</label>
				<input name="newsStatus" value="0" type="radio" checked="checked">是<br/>
				<input name="newsStatus" value="1" type="radio">否
			</div>
		</div>
		<c:import url="../pageControl/submitButton.jsp"></c:import>
	</form:form>
</div>