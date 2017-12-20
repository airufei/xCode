<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/admin/common/head.jsp"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>日志管理管理</title>
</head>
<body>
<center>
		<div style="background: #fafafa; padding: 10px;">
			<form id="editForm" name="editForm" method="post">
				<input  type="hidden"  value="${entity.id}" name="id" maxlength="20" />				
				<table>
					<tr>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td></td>
						<td><a href="javascript:void(0)"  onclick="save()" class="easyui-linkbutton">保存</a></td>
						<td><a href="javascript:void(0)"  onclick="CloseWind()" class="easyui-linkbutton">关闭</a></td>
					</tr>
				</table>
			</form>
		</div>
	</center>

	<script type="text/javascript">

	   function save()
	   {
		   var parms= $("#editForm").serializeJson();		   
		   $.ajax({  
		        type: "POST",  
		        contentType : 'application/json;charset=utf-8', //设置请求头信息
		        url: "<%=request.getContextPath()%>/admin/log/save",  
		        async: false,  
		        data: parms,  
		        dataType: "json",  
		        success: function (data) {  
		        	if(data!=null&&data!=undefined&&data!="undefined")
					{
						var res=data.result;
						var msg=data.msg;
						$.messager.alert("操作提示", msg);  
						
					}
		  
		        }  
		    });  
	   }
	</script>
</body>
</html>