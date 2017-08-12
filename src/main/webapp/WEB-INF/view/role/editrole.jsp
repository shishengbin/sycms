<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="../pageControl/jstlImport.jsp" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/css.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/JQuery zTree v3.5.15/css/zTreeStyle/zTreeStyle.css">
<style>
	ul.ztree {margin-top: 10px;border: 1px solid #617775;background: #f0f6e4;width:220px;height:200px;overflow-y:scroll;overflow-x:auto;}
</style>

<div class="pageContent">
	<form:form id="form" action="" method="post" commandName="role" class="pageForm required-validate" onsubmit="return iframeCallback(this,dialogAjaxDone);">
		<form:hidden path=""/>
        <form:hidden path=""/>
        <form:hidden path=""/>
        <form:hidden path=""/>
		<div class="pageFormContent nowrap" layoutH="56">
			<dl>
				<dt style="width:80px">角色名：</dt>
				<dd style="width:200px">
					 <form:input path="role" size="28" class="required"/>
				</dd>
				<dd style="width:25px"></dd>
			</dl>
			<br/><br/>
			<div class="divider"></div>
			<dl>
				<dt style="width:80px">角色描述：</dt>
				<dd style="width:200px">
				 	<form:input path="description" size="28" class="required"/>
				</dd>
				<dd style="width:25px"></dd>
			</dl>
			<br/><br/>
			<div class="divider"></div>
			<dl>
				<dt style="width:80px">拥有的资源：</dt>
           	 	 <form:hidden path="resourceIds"/>
            	<dd style="width:200px">
            		<input type="text" id="resourceName" name="resourceName" value="${zhangfn:resourceNames(role.resourceIds)}" readonly>
           			<a id="menuBtn" href="#">选择</a>
				<dd style="width:200px">
			</dl>
		</div>
		<div class="formBar">
			<ul style="margin-right: 30px;">
				<li><div class="buttonActive" >
						<div class="buttonContent">
							<button type="submit">${op }</button>
						</div>
					</div></li>
				<li>
					<div class="button">
						<div class="buttonContent">
							<button type="button" class="close">取消</button>
						</div>
					</div>
				</li>
			</ul>
		</div>

	</form:form>
	  <div id="menuContent" class="menuContent" style="display:none; position: absolute;left: 95px;top: 122px;"">
        <ul id="tree" class="ztree" style="margin-top:0; width:160px;"></ul>
    </div>

    <script src="${pageContext.request.contextPath}/static/JQuery zTree v3.5.15/js/jquery.ztree.all-3.5.min.js"></script>
    <script>
        $(function () {
            var setting = {
                check: {
                    enable: true ,
                    chkboxType: { "Y": "", "N": "" }
                },
                view: {
                    dblClickExpand: false
                },
                data: {
                    simpleData: {
                        enable: true
                    }
                },
                callback: {
                    onCheck: onCheck
                }
            };

            var zNodes =[
                <c:forEach items="${resourceList}" var="r">
                <c:if test="${not r.rootNode}">
                { 
                    id:'${r.id}', 
                    pId:'${r.parentId}', 
                    name:'${r.name}', 
                    checked:'${zhangfn:in(role.resourceIds, r.id)}'
                 },
                </c:if>
                </c:forEach>
            ];

            function onCheck(e, treeId, treeNode) {
                var zTree = $.fn.zTree.getZTreeObj("tree"),
                        nodes = zTree.getCheckedNodes(true),
                        id = "",
                        name = "";
                nodes.sort(function compare(a,b){return a.id-b.id;});
                for (var i=0, l=nodes.length; i<l; i++) {
                    id += nodes[i].id + ",";
                    name += nodes[i].name + ",";
                }
                if (id.length > 0 ) id = id.substring(0, id.length-1);
                if (name.length > 0 ) name = name.substring(0, name.length-1);
                $("#resourceIds").val(id);
                $("#resourceName").val(name);
//                hideMenu();
            }

            function showMenu() {
                var cityObj = $("#resourceName");
                var cityOffset = $("#resourceName").offset();
              // $("#menuContent").css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");
				$("#menuContent").slideDown("fast");
                $("body").bind("mousedown", onBodyDown);
            }
            function hideMenu() {
                $("#menuContent").fadeOut("fast");
                $("body").unbind("mousedown", onBodyDown);
            }
            function onBodyDown(event) {
                if (!(event.target.id == "menuBtn" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length>0)) {
                    hideMenu();
                }
            }

            $.fn.zTree.init($("#tree"), setting, zNodes);
            $("#menuBtn").click(showMenu);
        });
    </script>
</div>

