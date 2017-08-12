<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../pageControl/jstlImport.jsp" %>
<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="${pageContext.request.contextPath}/user" method="post">
	<div class="searchBar">
		<table class="searchContent" style="float: left;">
			<tr>
				<td>
					用户帐号：<input type="text" name="code" value=""/>
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
			<li><a class="add" href="${pageContext.request.contextPath}/user/create" target="dialog" title="添加用户"  width="720" height="400"><span>添加</span></a></li>
			<li><a class="delete" href="${pageContext.request.contextPath}/user/{sid_user}/delete" target="ajaxTodo" title="确认删除吗?" rel="page2"><span>删除</span></a></li>
			<li><a class="edit" href="${pageContext.request.contextPath}/user/{sid_user}/update" target="dialog" title="修改用户" width="720" height="400"><span>修改</span></a></li>
			<li class="line">line</li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="112">
		<thead>
			<tr>
				<th width="12%">用户帐号</th>
				<th width="12%">所属公司</th>
				<th width="12%">所属角色</th>
				<th width="10%">是否锁定</th>
				<th width="12%">创建时间</th>
				<th width="10%">最后修改时间</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${userList}" var="u">
					<tr target="sid_user" rel="${u.id }">
						<td>${u.username}</td>
						<td>${zhangfn:organizationName(u.organizationId)}</td>
						<td>${zhangfn:roleNames(u.roleIds)}</td>
						<td>${u.locked==false?"否":"是" }</td>
						<td><fmt:formatDate value="${u.createTime }" pattern="yyyy-MM-dd HH:mm"/></td>
						<td><fmt:formatDate value="${u.updateTime }" pattern="yyyy-MM-dd HH:mm"/></td>
					</tr>
			</c:forEach>
		</tbody>
	</table>
	<c:import url="../pageControl/paging.jsp">
		<c:param name="action" value="${pageContext.request.contextPath}/user"></c:param>
	</c:import>
</div>
