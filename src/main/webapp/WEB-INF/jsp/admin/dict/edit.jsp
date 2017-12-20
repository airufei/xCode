<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/admin/common/head.jsp"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>字典数据管理</title>
</head>
<body>
<center>
		<div style="background: #fafafa; padding: 10px;">
			<form id="editForm" name="editForm" method="post">
				<input  type="hidden"  value="${entity.id}" name="id" maxlength="20" />		
				<table>
					<tr>
						<td>值:</td>
						<td style="width:230px;">
						
						<input  class="easyui-textbox" style="width:100%;height:32px"  value="${entity.dictKey}" name="dictKey" maxlength="20" />
				        <span></span>
				          </td>
				       	<td></td>
					</tr>
					<tr>
						<td>名称:</td>
						<td style="width:230px;">
						
						<input  class="easyui-textbox" style="width:100%;height:32px"  value="${entity.dictValue}" name="dictValue" maxlength="50" />
				        <span></span>
				          </td>
				       	<td></td>
					</tr>
					<tr>
						<td>父级字典:</td>
						<td style="width:230px;">
						<select name="fid" id="fid" class="easyui-combobox" style="width:100%;">
						  
						</select>
				        <span></span>
				          </td>
				       	<td></td>
					</tr>
					<tr>
						<td>类型:</td>
						<td style="width:230px;">
						
						<input  class="easyui-textbox" style="width:100%;height:32px"  value="${entity.type}" name="type" maxlength="50" />
				        <span></span>
				          </td>
				       	<td></td>
					</tr>
					<tr>
						<td>关键词:</td>
						<td style="width:230px;">
						<textarea id="keyWords" rows=5 name="keyWords"  style="width:100%;" class="textarea easyui-validatebox">${entity.keyWords}</textarea>
				        <span></span>
				          </td>
				       	<td>注意：逗号隔开</td>
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
						<td>排序:</td>
						<td style="width:230px;">
						
						<input  class="easyui-textbox" style="width:100%;height:32px"  value="${entity.sort}" name="sort" maxlength="11" />
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

	$(document).ready(function() {
		getFDict();
	});
   //获取顶级字典数据
	function getFDict()
	{
		var url="<%=request.getContextPath()%>/admin/dict/comboxlist?fid=-1";
		$('#fid').combobox({
			url : url,
			valueField : 'id',
			textField : 'dictValue'
		});
	}
	   function save()
	   {
		   var parms= $("#editForm").serializeJson();		   
		   $.ajax({  
		        type: "POST",  
		        contentType : 'application/json;charset=utf-8', //设置请求头信息
		        url: "<%=request.getContextPath()%>/admin/dict/save",  
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