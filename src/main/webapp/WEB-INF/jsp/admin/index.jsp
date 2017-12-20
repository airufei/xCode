<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/admin/common/head.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   
    <title>${sysName}</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  
 <body class="easyui-layout">
    <div id="divHeader" data-options="region:'north',border:false,href:'<%=request.getContextPath()%>/admin/header'">
    </div>
    <div id="divMenuBar" data-options="region:'west',split:true,collapsed:false,title:'菜单栏',href:'<%=request.getContextPath()%>/admin/menus'">
    </div>
    <div id="divFooter" data-options="region:'south',border:false,href:'<%=request.getContextPath()%>/admin/footer'">
    </div>
    <div id="divContent" data-options="region:'center',title:false">
        <div id="tabs" class="easyui-tabs" data-options="fit:true,border:false">
            
        </div>
    </div>
</body>
</html>
