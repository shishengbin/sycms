<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../pageControl/jstlImport.jsp" %>
<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="${pageContext.request.contextPath}/organization" method="post">
	<div class="searchBar">
		<table class="searchContent" style="float: left;">
			<tr>
				<td>
					部门编号：<input type="text" name="code" value="${code}"/>
				</td>
				<td>
					部门名称：<input type="text" name="name" value="${name}"/>
				</td>
			</tr>
		</table>
		<div class="subBar" style="float: right;">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div></li>
			</ul>
		</div>
	</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li>
				<shiro:hasPermission name="organization:create">
					<a class="add" href="${pageContext.request.contextPath}/organization/{sid_user}/appendChild" target="dialog" title="添加部门"  width="720" height="400"><span>添加</span></a>
				</shiro:hasPermission>
			</li>
			<li>
			<shiro:hasPermission name="organization:delete">
				<a class="delete" href="${pageContext.request.contextPath}/organization/{sid_user}/delete" target="ajaxTodo" title="确认删除吗?" rel="page2"><span>删除</span></a>
			</shiro:hasPermission>
			</li>
			<li>
				<a class="edit" href="${pageContext.request.contextPath}/organization/{sid_user}/maintain" target="dialog" title="修改部门" width="720" height="400"><span>修改</span></a>
			
			</li>
			<li class="line">line</li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="112">
		<thead>
			<tr>
				<th width="12%">部门编号</th>
				<th width="12%">部门名称</th>
				<th width="10%">上级部门</th>
				<%-- <th width="10%">是否可用</th>--%>
				<th width="12%">创建时间</th>
				<th width="10%">最后修改时间</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${organizationList}" var="o">
					<tr target="sid_user" rel="${o.id }">
						<td>${o.code }</td>
						<td>${o.name}</td>
						<td>${zhangfn:organizationName(o.parentId)}</td>
						<%--<td>${o.available==true?"是":"否"}</td>--%>
						<td><fmt:formatDate value="${o.createTime}" pattern="yyyy-MM-dd hh:mm"></fmt:formatDate></td>
						<td><fmt:formatDate value="${o.createTime}" pattern="yyyy-MM-dd hh:mm"></fmt:formatDate></td>
					</tr>
			</c:forEach>
		</tbody>
	</table>
	<c:import url="../pageControl/paging.jsp">
		<c:param name="action" value="${pageContext.request.contextPath}/organization"></c:param>
	</c:import>
</div>
