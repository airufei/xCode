<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/admin/common/head.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>生成方案配置</title>
</head>
<body>
	<center>
		<div style="background: #fafafa; padding: 10px;">
			<form id="addForm" name="addForm" method="post">
				<input  type="hidden" name="id"  value="${genScheme.id}" maxlength="20" />
				<table>
					<tr>
						<td>方案名称:</td>
						<td><input  class="easyui-textbox" style="width:100%;height:32px" name="name" value="${genScheme.name}" maxlength="200" /></td>
						<td></td>
					</tr>
					<tr>
						<td>模板分类:</td>
						<td><select name="category"
									class="easyui-combobox" style="width:180px;">
										<c:forEach items="${config.categoryList}" var="tb">
											<option value="${tb.dictKey}"
												${tb.dictKey==genScheme.category?'selected':''}
												title="${tb.dictValue}">${tb.dictValue}</option>
										</c:forEach>
								</select></td>
						<td>生成结构：{包名}/{模块名}/{分层(dao,entity,service,web)}/{java类}</td>
					</tr>
					<tr>
						<td>生成包路径:</td>
						<td><input class="easyui-textbox" style="width:100%;height:32px"  value="${genScheme.packageName}" name="packageName" maxlength="200" /></td>
						<td>建议模块包：com.cn.cooxin</td>
					</tr>
					<tr>
						<td>子包路径:</td>
						<td><input class="easyui-textbox" style="width:100%;height:32px"  value="${genScheme.subPackageName}" name="subPackageName" maxlength="200" /></td>
						<td>API层子包(如lcapi,pcapi)</td>
					</tr>
					<tr>
						<td>java模块名:</td>
						<td><input class="easyui-textbox" style="width:100%;height:32px" value="${genScheme.moduleName}"  name="moduleName" maxlength="200" /></td>
						<td>java模块，例如 admin、mipay、order</td>
					</tr>
					<tr>
						<td>页面模块名:</td>
						<td><input class="easyui-textbox" style="width:100%;height:32px" value="${genScheme.modulePageName}"  name="modulePageName" maxlength="200" /></td>
						<td>页面端系统名，例如 admin</td>
					</tr>
					<tr>
						<td>访问路径:</td>
						<td><input  class="easyui-textbox" style="width:100%;height:32px" value="${genScheme.subModuleName}" name="subModuleName" maxlength="200" /></td>
						<td>如：admin/user</td>
					</tr>
					<tr>
						<td>生成功能描述:</td>
						<td><input  class="easyui-textbox" style="width:100%;height:32px"  value="${genScheme.functionName}" name="functionName" maxlength="200" /></td>
						<td>将设置到类描述</td>
					</tr>
					<tr>
						<td>生成功能名:</td>
						<td><input class="easyui-textbox" style="width:100%;height:32px"  value="${genScheme.functionNameSimple}" name="functionNameSimple" maxlength="200" /></td>
						<td>用作功能提示，如：保存“某某”成功</td>
					</tr>
					<tr>
						<td>生成功能作者:</td>
						<td><input class="easyui-textbox" style="width:100%;height:32px"  value="${genScheme.functionAuthor}" name="functionAuthor" maxlength="200" /></td>
						<td>功能开发者</td>
					</tr>
					<tr>
						<td>业务表名:</td>
						<td><select name="tableId"
									class="easyui-combobox" style="width:180px;">
										<c:forEach items="${tableList}" var="tb">
											<option value="${tb.id}"
												${tb.id==genScheme.tableId?'selected':''}
												title="${tb.name}">${tb.name}</option>
										</c:forEach>
								</select></td>
						<td>生成的数据表，一对多情况下请选择主表</td>
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
						<td><a href="javascript:void(0)"  onclick="save()" class="easyui-linkbutton">保存并生成代码</a></td>
						<td><a href="javascript:void(0)"  onclick="closeDialogAndreload()" class="easyui-linkbutton">关闭窗口</a></td>
					</tr>
				</table>
			</form>
		</div>
	</center>

	<script type="text/javascript">

	   function save()
	   {
		   var parms= $("#addForm").serializeJson();		   
		   $.ajax({  
		        type: "POST",  
		        contentType : 'application/json;charset=utf-8', //设置请求头信息
		        url: "<%=request.getContextPath()%>/admin/codeScheme/save",  
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
