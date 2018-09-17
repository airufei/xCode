<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/admin/common/head.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
</head>
<body>
	<div class="easyui-accordion" data-options="fit:true,border:false">


		<div id="div16" title="代码生成管理" style="padding:0;">
			<ul class="easyui-tree"
				data-options="animate: true,state:closed,onClick: function(node){addNewTab(node.text,node.id)}">
				<li id='100-03'><span>代码生成</span>
					<ul>
						<li id='<%=request.getContextPath()%>/admin/codetable/index'><span>数据表管理</span></li>

						<li id='<%=request.getContextPath()%>/admin/codeScheme/index'><span>生成方案管理</span></li>
					</ul></li>

			</ul>
		</div>
		<div id="div17" title="基础管理" style="padding:0;">
			<ul class="easyui-tree"
				data-options="animate: true,state:closed,onClick: function(node){addNewTab(node.text,node.id)}">
				<li id='100-01'><span>系统管理</span>
					<ul>
						<li id='<%=request.getContextPath()%>/admin/baseUser/index'><span>用户管理</span></li>
						<li id='<%=request.getContextPath()%>/admin/adminRole/index'><span>角色管理</span></li>
						<li id='<%=request.getContextPath()%>/admin/adminMenu/index'><span>菜单管理</span></li>
						
						
					</ul></li>
				<li id='100-02'><span>基础数据管理</span>
					<ul>
						<li id='<%=request.getContextPath()%>/admin/log/index'><span>日志管理</span></li>
						<li id='<%=request.getContextPath()%>/admin/dict/index'><span>数据字典管理</span></li>
						
						
					</ul></li>
			</ul>
		</div>

	</div>
</body>
</html>
