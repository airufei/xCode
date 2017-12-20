<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/admin/common/head.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>角色管理管理</title>
</head>
<body>
	<center>
		<div style="background: #fafafa; padding: 10px;">
			<form id="editForm" name="editForm" method="post">
				<input id="id" type="hidden" name="id" value="${entity.id}"
					maxlength="10" />
				<table>
					<tr>
						<td>角色名称:</td>
						<td style="width:300px;"><input class="easyui-textbox"
							style="width:100%;height:32px" id="name" value="${entity.name}"
							name="name" maxlength="10" /></td>
						<td></td>
					</tr>
					<tr>
						<td>权限:</td>
						<td><ul id="tree"></ul></td>
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
						<td><a href="javascript:void(0)" onclick=" closeDialogAndreload()"
							class="easyui-linkbutton">关闭</a></td>
					</tr>
				</table>
			</form>
		</div>
	</center>

	<script type="text/javascript">
$(document).ready(function () {
    var id=$("#id").val();
    if(undefined==id||id=="")
    {
        id=0;
    }
    getTree(id);
});

function save() {
	var name = $("#name").val();
	var id= $("#id").val();
	if(id==undefined)
	{
	  id=0;
	}
	var remark = $("#remark").val();
	var mulist= getChecked();
	$.ajax({
		type: "POST",
		contentType: 'application/json;charset=utf-8', //设置请求头信息
		url: '<%=request.getContextPath()%>/admin/adminRole/save?name='+name+'&remark='+remark+'&mulist='+mulist+'&id='+id,
		async: false,
		dataType: "json",
		success: function (data) {
			if (data != null && data != undefined
				 && data != "undefined") {
				 var msg = data.msg;
				$.messager.alert("操作提示", msg);
				if(res){
				  closeDialogAndreload();
				} 
			}

		}
	});
}
//获取所有选中的节点
function getChecked() {
	var nodes = $('#tree').tree('getChecked');
	var s = '';
	for (var i = 0; i < nodes.length; i++) {
		if (s != '')
			s += ',';
		s += nodes[i].id;
	}
	return s;
}

function getTree(id) {
   var url='<%=request.getContextPath()%>/admin/adminRole/getTree?roleId='+ id;
			$('#tree').tree({
				url : url,
				checkbox : true
			});
		}
	</script>
</body>
</html>