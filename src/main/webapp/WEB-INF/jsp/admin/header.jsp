<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/admin/common/head.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
 <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title></title>
</head>
<body>
    <div class="header1">
        <div class="header2">
            <div class="logo">
                <strong>${sysName}</strong>
            </div>
            <div class="contact">
                <div class="prompt">
                    <span>${today}      ${week} </span>
                </div>
                <div class="headerMenu">
                    <ul>
                        <li><a href="#" class="easyui-linkbutton" data-options="plain:true" onclick="logout()">安全退出</a></li>
                        <li><a href="#" class="easyui-menubutton" data-options="menu:'#mm1'">您好，${userName}</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
    <div id="mm1">
        <div onclick="addNewTab('个人设置', 'BulletinBoard.html');">个人设置</div>
        <div onclick="addNewTab('个人菜单', 'BulletinBoard.html');">个人菜单</div>
    </div>
    <script type="text/javascript">
    function logout()
    {
    	var url="<%=request.getContextPath()%>/admin/baseUser/loginOut";
    	window.location.href = url;
    }
    </script>
</body>
</html>
 