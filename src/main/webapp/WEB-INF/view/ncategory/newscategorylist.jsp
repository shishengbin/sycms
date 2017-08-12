<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../pageControl/jstlImport.jsp" %>
<div class="pageHeader">
	<form id="pagerForm" onsubmit="return navTabSearch(this);" action="${pageContext.request.contextPath}/ncategory" method="post">
	<input type="hidden" name="pageNum" value="1" id="pageNum" /><!-- 代表当前页 -->
	<input type="hidden" name="numPerPage" value="${ncategorylist.pageSize}" id="numPerPage"/><!-- 每页显示多少条 -->
	<div class="searchBar">
		<table class="searchContent"  style="float: left;">
			<tr>
				<td>
					分类名称：
					<input type="text"  name="newsName" value="">
				</td>
			</tr>
		</table>
		<c:import url="../pageControl/retrieval.jsp"></c:import>
	</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="${pageContext.request.contextPath}/ncategory/create" target="navTab" width="500" height="240" title="添加文章分类"><span>添加</span></a></li>
			<li><a class="delete" href="${pageContext.request.contextPath}/ncategory/delete?nid={sid_user}" target="ajaxTodo" title="确定要删除吗?" ><span>删除</span></a></li>
			<li><a class="edit" href="${pageContext.request.contextPath}/ncategory/update?nid={sid_user}" target="navTab" width="500" height="240" title="修改文章分类" ><span>修改</span></a></li>
			<li class="line">line</li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="112">
		<thead>
			<tr>
				<th width="10%">分类名称</th>
				<th width="20%">分类描述</th>
				<th width="10%">是否显示</th>
				<th width="12%">创建时间</th>
				<th width="12%">最后修改时间</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${ncategorylist.list}" var="nc">
				<tr target="sid_user" rel="${nc.nid }">
					<td>${nc.newsName }</td>
					<td>${nc.newsRemark }</td>
					<td>${nc.newsStatus==0?"是":"否" }</td>
					<td><fmt:formatDate value="${nc.createTime }" pattern="yyyy-MM-dd HH:mm"/></td>
					<td><fmt:formatDate value="${nc.updateTime }" pattern="yyyy-MM-dd HH:mm"/></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>显示</span>
			<select class="combox"  name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value})" showvalue="${numPerPage }">
			<option value="10">10</option>
				<option value="15">15</option>
				<option value="20">20</option>
				<option value="30">30</option>
				<option value="40">40</option>
				<option value="50">50</option>
			</select>
			<span>条，共${ncategorylist.total}条</span>
		</div>
		 <div class="pagination" targetType="navTab" totalCount="${ncategorylist.total}" numPerPage="${ncategorylist.pageSize}"
			pageNumShown="${ncategorylist.pageSize}" currentPage="${ncategorylist.pageNum}"></div>
    </div>
	</div>
</div>