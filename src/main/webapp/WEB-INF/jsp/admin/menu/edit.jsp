<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/admin/common/head.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>菜单管理管理</title>
</head>
<body>
	<center>
		<div style="background: #fafafa; padding: 10px;">
			<form id="editForm" name="editForm" method="post">
				<input type="hidden" name="id" value="${entity.id}" maxlength="20" />
				<table>
					<tr>
						<td>菜单名称:</td>
						<td><input class="easyui-textbox" value="${entity.name}"
							style="width:100%;height:32px" name="name" maxlength="20" /></td>
						<td></td>
					</tr>

					<tr>
						<td>菜单地址:</td>
						<td><input class="easyui-textbox"
							style="width:100%;height:32px" value="${entity.url}" name="url"
							maxlength="50" /></td>
						<td></td>
					</tr>
					<tr>
						<td>菜单所属系统:</td>
						<td><select name="isadmin" class="easyui-combobox"
							style="width:300px;height:32px">
								<option value="-1" title="请选择">请选择</option>
								<option value="0"  ${entity.isadmin==0 ?'selected':''} title="后端">后端菜单</option>
								<option value="1" ${entity.isadmin==1 ?'selected':''} title="请选择">前端菜单</option>

						</select></td>
						<td></td>
					</tr>
					
					<tr>
						<td>父级菜单:</td>
						<td><select name="fid" class="easyui-combobox"
							style="width:300px;height:32px">
								<option value="-1" title="请选择">请选择</option>
								<c:forEach items="${muList}" var="mu">
									<option value="${mu.id}" ${entity.fid==mu.id ?'selected':''}
										title="${mu.name}">${mu.name}</option>
								</c:forEach>

						</select></td>
						<td></td>
					</tr>
					<tr>
						<td>备注:</td>
						<td><textarea id="remark" rows=5 name="remark"
								style="width:100%;" class="textarea easyui-validatebox">${entity.remark}</textarea>
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
						<td><a href="javascript:void(0)" onclick="save()"
							class="easyui-linkbutton">保存</a></td>
						<td><a href="javascript:void(0)"
							onclick="closeDialogAndreload()" class="easyui-linkbutton">关闭</a></td>
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
		        url: "<%=request.getContextPath()%>/admin/adminMenu/save",
				async : false,
				data : parms,
				dataType : "json",
				success : function(data) {
					if (data != null && data != undefined
							&& data != "undefined") {
						var res = data.result;
						var msg = data.msg;
						$.messager.alert("操作提示", msg);
						if (res) {
							closeDialogAndreload();
						}
					}

				}
			});
		}
	</script>
</body>
</html>