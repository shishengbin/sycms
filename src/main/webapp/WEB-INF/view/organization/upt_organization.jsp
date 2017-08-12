<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="../pageControl/jstlImport.jsp" %>
<div class="pageContent">
	<form:form id="form" action="${pageContext.request.contextPath}/organization/{${organization.id}}/update" method="post" commandName="organization" class="pageForm required-validate" onsubmit="return iframeCallback(this,dialogAjaxDone);">
		<form:hidden path="id"/>
		<form:hidden path="available"/>
        <form:hidden path="parentId"/>
        <form:hidden path="parentIds"/>
		<div class="pageFormContent nowrap" layoutH="56">
		    <dl>
					<dt style="width:80px">部门编号：</dt>
					<dd style="width:200px">
						<input type="text" id="code" name="code" size="28" minlength="2" maxlength="16" class="required" value="${organization.code }"/>
					</dd>
					<dd style="width:25px"></dd>
		    </dl>          
			<br/><br/>
			<div class="divider"></div>
			<dl>
				<dt style="width:80px">部门名称：</dt>
				<dd style="width:200px">
					<input type="text" name="name" size="28" class="required" value="${organization.name }" />
				</dd>
				<dd style="width:25px"></dd>
			</dl>
		</div>
		<c:import url="../pageControl/submitButton.jsp"></c:import>
	</form:form>
</div>
