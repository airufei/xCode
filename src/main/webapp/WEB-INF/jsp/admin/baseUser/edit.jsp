<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/admin/common/head.jsp"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>用户信息管理</title>
</head>
<body>
<center>
		<div style="background: #fafafa; padding: 10px;">
			<form id="editForm" name="editForm" method="post">
				<input  type="hidden" name="id"  value="${entity.id}" maxlength="20" />
				<input  type="hidden" name="flag" value="${entity.flag}" maxlength="20" />
				<%-- <input  type="hidden" name="createtime" value="${entity.createtime}" maxlength="30" /> --%>
				<table>
				<tr>
						<td>账号:</td>
						<td style="width:230px;">
						
						<input  class="easyui-textbox" style="width:100%;height:32px"  value="${entity.email}" name="email" maxlength="30" />
				        <span></span>
				          </td>
				       	<td></td>
					</tr>
					<tr>
						<td>密码:</td>
						<td style="width:230px;">
						
						<input  class="easyui-textbox" type="password" style="width:100%;height:32px"  value="${entity.password}" name="password" maxlength="30" />
				        <span></span>
				          </td>
				       	<td></td>
					</tr>
					<tr>
						<td>昵称:</td>
						<td style="width:230px;">
						
						<input  class="easyui-textbox" style="width:100%;height:32px"  value="${entity.username}" name="username" maxlength="30" />
				        <span></span>
				          </td>
				       	<td></td>
					</tr>
					
					
					<tr>
						<td>手机:</td>
						<td style="width:230px;">
						
						<input  class="easyui-textbox" style="width:100%;height:32px"  value="${entity.phone}" name="phone" maxlength="11" />
				        <span></span>
				          </td>
				       	<td></td>
					</tr>
					<tr>
						<td>备注:</td>
						<td style="width:230px;">
						
						<textarea id="remark" rows=5 name="remark"  style="width:100%;" class="textarea easyui-validatebox">${entity.remark}</textarea>
				        <span></span>
				          </td>
				       	<td></td>
					</tr>
					<tr>
						<td>微信号:</td>
						<td style="width:230px;">
						
						<input  class="easyui-textbox" style="width:100%;height:32px"  value="${entity.wechart}" name="wechart" maxlength="20" />
				        <span></span>
				          </td>
				       	<td></td>
					</tr>
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
						<td><a href="javascript:void(0)"  onclick="closeDialogAndreload()" class="easyui-linkbutton">关闭</a></td>
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
		        url: "<%=request.getContextPath()%>/admin/baseUser/save",  
		        async: false,  
		        data: parms,  
		        dataType: "json",  
		        success: function (data) {  
		        	if(data!=null&&data!=undefined&&data!="undefined")
					{
						var res=data.result;
						var msg=data.msg;
						$.messager.alert("操作提示", msg);  
						if(res)
						{
						  closeDialogAndreload();
						} 
					}
		  
		        }  
		    });  
	   }
	</script>
</body>
</html>