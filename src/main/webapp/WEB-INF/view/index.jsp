<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" %>
<%@ include file="pageControl/jstlImport.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>微家园内容管理系统</title>

<link href="${pageContext.request.contextPath}/public/dwz/themes/default/style.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="${pageContext.request.contextPath}/public/dwz/themes/css/core.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="${pageContext.request.contextPath}/public/dwz/themes/css/print.css" rel="stylesheet" type="text/css" media="print"/>
<link href="${pageContext.request.contextPath}/public/dwz/uploadify/css/uploadify.css" rel="stylesheet" type="text/css" media="screen"/>
<!--[if IE]>
<link href="themes/css/ieHack.css" rel="stylesheet" type="text/css" media="screen"/>
<![endif]-->

<!--[if lte IE 9]>
<script src="js/speedup.js" type="text/javascript"></script>
<![endif]-->

<script src="${pageContext.request.contextPath}/public/dwz/js/jquery-1.7.2.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/public/dwz/js/jquery.cookie.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/public/dwz/js/jquery.validate.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/public/dwz/js/jquery.bgiframe.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/public/dwz/xheditor/xheditor-1.2.1.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/public/dwz/xheditor/xheditor_lang/zh-cn.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/public/dwz/uploadify/scripts/jquery.uploadify.js" type="text/javascript"></script>

<!-- svg图表  supports Firefox 3.0+, Safari 3.0+, Chrome 5.0+, Opera 9.5+ and Internet Explorer 6.0+ -->
<script type="text/javascript" src="${pageContext.request.contextPath}/public/dwz/chart/raphael.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/public/dwz/chart/g.raphael.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/public/dwz/chart/g.bar.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/public/dwz/chart/g.line.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/public/dwz/chart/g.pie.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/public/dwz/chart/g.dot.js"></script>

<script src="${pageContext.request.contextPath}/public/dwz/js/dwz.core.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/public/dwz/js/dwz.util.date.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/public/dwz/js/dwz.validate.method.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/public/dwz/js/dwz.regional.zh.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/public/dwz/js/dwz.barDrag.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/public/dwz/js/dwz.drag.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/public/dwz/js/dwz.tree.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/public/dwz/js/dwz.accordion.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/public/dwz/js/dwz.ui.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/public/dwz/js/dwz.theme.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/public/dwz/js/dwz.switchEnv.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/public/dwz/js/dwz.alertMsg.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/public/dwz/js/dwz.contextmenu.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/public/dwz/js/dwz.navTab.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/public/dwz/js/dwz.tab.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/public/dwz/js/dwz.resize.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/public/dwz/js/dwz.dialog.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/public/dwz/js/dwz.dialogDrag.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/public/dwz/js/dwz.sortDrag.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/public/dwz/js/dwz.cssTable.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/public/dwz/js/dwz.stable.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/public/dwz/js/dwz.taskBar.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/public/dwz/js/dwz.ajax.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/public/dwz/js/dwz.pagination.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/public/dwz/js/dwz.database.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/public/dwz/js/dwz.datepicker.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/public/dwz/js/dwz.effects.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/public/dwz/js/dwz.panel.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/public/dwz/js/dwz.checkbox.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/public/dwz/js/dwz.history.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/public/dwz/js/dwz.combox.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/public/dwz/js/dwz.print.js" type="text/javascript"></script>
<!--
<script src="bin/dwz.min.js" type="text/javascript"></script>
-->
<script src="${pageContext.request.contextPath}/public/dwz/js/dwz.regional.zh.js" type="text/javascript"></script>

<script type="text/javascript">
$(function(){
	DWZ.init("${pageContext.request.contextPath}/public/dwz/dwz.frag.xml", {
		//loginUrl:"login_dialog.html", 
		loginTitle:"登录",	// 弹出登录对话框
//		loginUrl:"login.html",	// 跳到登录页面
		loginUrl:"${pageContext.request.contextPath}/login.html",
		statusCode:{ok:200, error:300, timeout:301}, //【可选】
		pageInfo:{pageNum:"pageNum", numPerPage:"numPerPage", orderField:"orderField", orderDirection:"orderDirection"}, //【可选】
		debug:false,	// 调试模式 【true|false】
		callback:function(){
			initEnv();
			$("#themeList").theme({themeBase:"public/dwz/themes"}); // themeBase 相对于index页面的主题base路径
		}
	});
});

</script>
</head>

<body scroll="no">
	<div id="layout">
		<div id="header">
			<div class="headerNav">
				<span class="logo">SYCMS</span>
				<ul class="nav">
					<%-- 
						<li><a href="changepwd.html" target="dialog" width="600">设置</a></li>
						<li><a href="http://www.cnblogs.com/dwzjs" target="_blank">博客</a></li>
						<li><a href="http://weibo.com/dwzui" target="_blank">微博</a></li>
						<li><a href="http://bbs.dwzjs.com" target="_blank">论坛</a></li>
					--%>
					<li><a href="${pageContext.request.contextPath}/logout">退出</a></li>
				</ul>
				<ul class="themeList" id="themeList">
					<%--
					<li theme="default"><div class="selected">蓝色</div></li>
					<li theme="green"><div>绿色</div></li>
					<!--<li theme="red"><div>红色</div></li>-->
					<li theme="purple"><div>紫色</div></li>
					<li theme="silver"><div>银色</div></li>
					<li theme="azure"><div>天蓝</div></li>
					--%>
				</ul>
			</div>
			<!-- navMenu -->	
		</div>

		<div id="leftside">
			<div id="sidebar_s">
				<div class="collapse">
					<div class="toggleCollapse"><div></div></div>
				</div>
			</div>
			<div id="sidebar">
				<div class="toggleCollapse"><h2>主菜单</h2><div>收缩</div></div>
				<div class="accordion" fillSpace="sidebar">
					<c:forEach items="${menus}" var="m">
						<c:if test="${m.parentId==0 }">
							<div class="accordionHeader">
								<h2><span>Folder</span>${m.name }</h2>
							</div>
							<div class="accordionContent">
								<ul class="tree treeFolder">
									<c:forEach items="${menus}" var="n">
										<c:if test="${n.parentId==m.id }">
		       						 		<li><a href="${pageContext.request.contextPath}/${n.url}" target="navTab" rel="${n.rel }">${n.name}</a></li>
		   								</c:if>
		   							</c:forEach>
								</ul>
							</div>
						</c:if>	
					</c:forEach>
				</div>
			</div>
		</div>
		<div id="container">
			<div id="navTab" class="tabsPage">
				<div class="tabsPageHeader">
					<div class="tabsPageHeaderContent"><!-- 显示左右控制时添加 class="tabsPageHeaderMargin" -->
						<ul class="navTab-tab">
							<li tabid="main" class="main"><a href="javascript:;"><span><span class="home_icon">我的主页</span></span></a></li>
						</ul>
					</div>
					<div class="tabsLeft">left</div><!-- 禁用只需要添加一个样式 class="tabsLeft tabsLeftDisabled" -->
					<div class="tabsRight">right</div><!-- 禁用只需要添加一个样式 class="tabsRight tabsRightDisabled" -->
					<div class="tabsMore">more</div>
				</div>
				<ul class="tabsMoreList">
					<li><a href="javascript:;">我的主页</a></li>
				</ul>
				<div class="navTab-panel tabsPageContent layoutBox">
					<div class="page unitBox">
						<div class="accountInfo">
							<div class="alertInfo">
							</div>
							
							<div class="right">
							</div>
						</div>
						<div class="pageFormContent" layoutH="80" style="margin-right:230px">

						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div id="footer">Copyright &copy; 2010 <a href="demo_page2.html" target="dialog">团队</a> 京ICP备05019125号-10</div>
</body>
</html>