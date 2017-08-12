<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../pageControl/jstlImport.jsp" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/css.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/JQuery zTree v3.5.15/css/zTreeStyle/zTreeStyle.css">
<style>
	ul.ztree {margin-top: 10px;border: 1px solid #617775;background: #f0f6e4;width:220px;height:200px;overflow-y:scroll;overflow-x:auto;}
</style>

<div class="pageContent">
	<form:form id="form" action="" method="post" commandName="user" class="pageForm required-validate" onsubmit="return iframeCallback(this,dialogAjaxDone);">
		 <form:hidden path="id"/>
        <form:hidden path="salt"/>
        <form:hidden path="locked"/>
		<div class="pageFormContent nowrap" layoutH="56">
			<dl>
				<dt style="width:80px"><form:label path="username">用户名：</form:label></dt>
				<dd style="width:200px">
					 <form:input path="username"  size="28" class="required" />
				</dd>
				<dd style="width:25px"></dd>
			</dl>
			<br/><br/>
			<div class="divider"></div>
			<dl>
				<dt style="width:80px"><form:label path="password">密码：</form:label></dt>
				<dd style="width:200px">
					<form:password path="password" size="28" minlength="2" maxlength="16" class="required"/>
				</dd>
				<dd style="width:25px"></dd>
			</dl>
			<br/><br/>
			<div class="divider"></div>
			<dl>
				<dt style="width:80px"><form:label path="organizationId">所属组织：</form:label></dt>
           	 	<form:hidden path="organizationId"/>
            	<dd style="width:200px">
            		<input type="text" id="organizationName" name="organizationName" value="${zhangfn:organizationNameToShow(user.organizationId)}" readonly="readonly">
            		<a id="menuBtn" href="#">选择</a>
				<dd style="width:200px">
			</dl>
			</dl>
			<div class="divider"></div>
			<dl>
				<dt style="width:80px">所属角色：</dt>
				<dd style="width:200px">
					 <form:select path="roleIds" items="${roleList}" itemLabel="description" itemValue="id" multiple="true"/>
					(按住shift键多选)
				</dd>
				<dd style="width:25px"></dd>
			</dl>
			</dl>
		</div>
		
		<c:import url="../pageControl/submitButton.jsp"></c:import>
		
	</form:form>
	
	<div id="menuContent" class="menuContent" style="display:none; position: absolute;left: 95px;top: 124px;">
        <ul id="tree" class="ztree" style="margin-top:0; width:160px;"></ul>
    </div>

    <script type="text/javascript" src="${pageContext.request.contextPath}/static/JQuery zTree v3.5.15/js/jquery.ztree.all-3.5.min.js"></script>
    <script>
        $(function () {
            var setting = {
                view: {
                    dblClickExpand: false
                },
                data: {
                    simpleData: {
                        enable: true
                    }
                },
                callback: {
                    onClick: onClick
                }
            };

            
            var zNodes =[
                <c:forEach items="${organizationList}" var="o">
                    <c:if test="${not o.rootNode}">
                        { id:'${o.id}',
                          pId:'${o.parentId}',
                          name:'${o.name}'
                        },
                    </c:if>
                </c:forEach>
            ];

			
            function onClick(e, treeId, treeNode) {
                var zTree = $.fn.zTree.getZTreeObj("tree"),
                        nodes = zTree.getSelectedNodes(),
                        id = "",
                        name = "";
                nodes.sort(function compare(a,b){return a.id-b.id;});
                for (var i=0, l=nodes.length; i<l; i++) {
                    id += nodes[i].id + ",";
                    name += nodes[i].name + ",";
                }
                if (id.length > 0 ) id = id.substring(0, id.length-1);
                if (name.length > 0 ) name = name.substring(0, name.length-1);
                $("#organizationId").val(id);
                $("#organizationName").val(name);
                hideMenu();
            }

            function showMenu() {
                var cityObj = $("#organizationName");
                var cityOffset = $("#organizationName").offset();
                //$("#menuContent").css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");
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

