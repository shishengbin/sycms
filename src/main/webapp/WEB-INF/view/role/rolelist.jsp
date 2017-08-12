<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../pageControl/jstlImport.jsp" %>
<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="${pageContext.request.contextPath}/role" method="post">
	<div class="searchBar">
		<table class="searchContent" style="float: left;">
			<tr>
				<td>
					角色标识：<input type="text" name="code" value=""/>
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
			<li><a class="add" href="${pageContext.request.contextPath}/role/create" target="dialog" title="添加角色"  width="720" height="400"><span>添加</span></a></li>
			<li><a class="delete" href="${pageContext.request.contextPath}/organization/{sid_user}/delete" target="ajaxTodo" title="确认删除吗?" rel="page2"><span>删除</span></a></li>
			<li><a class="edit" href="${pageContext.request.contextPath}/role/{sid_user}/update" target="dialog" title="修改角色" width="720" height="400"><span>修改</span></a></li>
			<li class="line">line</li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="112">
		<thead>
			<tr>
				<th width="10%">角色标识</th>
				<th width="10%">角色名称</th>
				<th width="18%">拥有权限</th>
				<th width="8%">是否可用</th>
				<th width="10%">创建时间</th>
				<th width="10%">最后修改时间</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${roleList}" var="role">
					<tr target="sid_user" rel="${role.id }">
						<td>${role.role}</td>
						<td>${role.description}</td>
						<td>${zhangfn:resourceNames(role.resourceIds)}</td>
						<td>${role.available==false?"否":"是" }</td>
						<td><fmt:formatDate value="${role.createTime }" pattern="yyyy-MM-dd HH:mm"/></td>
						<td><fmt:formatDate value="${role.updateTime }" pattern="yyyy-MM-dd HH:mm"/></td>
					</tr>
			</c:forEach>
		</tbody>
	</table>
	<c:import url="../pageControl/paging.jsp">
		<c:param name="action" value="${pageContext.request.contextPath}/role"></c:param>
	</c:import>
</div>
