<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/admin/common/head.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>生成方案配置</title>

</head>
<body>
	<table width="99%">
		<tr>
			<td>
				<div style="height: auto; background: #fafafa; padding: 0px;"
					class="easyui-panel" title="生成方案管理" iconCls="icon-search"
					collapsible="true" minimizable="false" maximizable="false"
					closable="false">
					<form id="searchForm" name="searchForm" method="post" action=""
						onsubmit="return searchDGData(this.id,'dg');">
						<div>
							<table width="99%" cellspacing="7">
								<tr>
									<td>
										<form id="searchForm">
											<table style="width:98%">
												<tr>
													<td>名称:</td>
													<td style="width:40%"><input  class="easyui-textbox" style="width:100%;height:32px" name="name" maxlength="20" /></td>
													<td ></td>
													<td style="width:50%"></td>
													<td><input id="btnSubmit" class="btn btn-primary"
														type="button" onclick="serach()" value="查询" /></td>
												</tr>
											</table>
										</form>
									</td>
								</tr>
							</table>
						</div>
					</form>
				</div>
			</td>
		</tr>
		<tr>
			<td>
				<table id="dg" name="dg" title="生成方案管理" iconCls="icon-list"
					width="100%" singleSelect="true" idField="id" loadMsg="请稍候......"
					pageList="[10,20,30,40,50,100,150,200]" rownumbers="true">
				</table>
			</td>
		</tr>
	</table>
	<div>


		<div id="addDialog" class="easyui-window" title=""
			style="width:600px;height:400px"
			data-options="iconCls:'icon-save',modal:true">
			
			<iframe scrolling="auto" id='openWindIframe' frameborder="0" src=""
				style="width: 100%; height: 100%;">	
			</iframe>
				
		</div>
	</div>



	<script type="text/javascript">
	$(document).ready(function() {
		serach();
		$('#addDialog').window('close'); // close a window
	});

	function serach() {
		 var dataPara = getFormJson($("#searchForm")); 
		var url = '<%=request.getContextPath()%>/admin/codeScheme/list';
		$('#dg').datagrid({
			url : url,
			type : "post",
			datatype : "json",
 			queryParams : dataPara,
			loadMsg : "数据加载中，请稍后...",
			fitColumns: true,
	        pageSize: 10,
	        pagination: true,
	        striped: true,
	        collapsible: true,
	        remoteSort: true,
	        singleSelect: true,
			onloadsuccess : function(data) {
				conlose.log(data);
			},
			toolbar : [ {
				text : '添加',
				iconCls : 'icon-add',
				handler : function() {
					add();
				}
			} ],
			columns : [ [  {
				field : 'name',
				title : '名称',
                width:100,
				align : 'left'
			}, {
				field : 'packageName',
				title : '生成包路径',
				 width:100,
				align : 'left'
			}, {
				field : 'moduleName',
				title : '生成模块名',
				 width:100,
				align : 'left'
			}, {
				field : 'subModuleName',
				title : '生成子模块名',
				 width:100,
				align : 'left'
			}, {
				field : 'functionName',
				title : '生成功能描述',
				 width:100,
				align : 'left'
			}, {
				field : 'functionAuthor',
				title : '功能开发者',
				 width:100,
				align : 'left'
			}, {
				field : 'updatetimestr',
				title : '修改时间',
				 width:100,
				align : 'left'
			},{
				field: 'id',
				title: '操作',
				width: 100,
				align: 'center',
				formatter: function(value, row, index) {
					if (row!=null) 
					{
						var a = '<a href="javascript:void(0)" onclick="editdata(this)">编辑</a>';
                        var b='&nbsp;&nbsp;&nbsp;';
						var c = '<a href="javascript:void(0)" onclick="deleted(this)">删除</a>';
						return a + b+c;
					}
				  }
			    }] ]
		});
		var p = $('#dg').datagrid('getPager');
		$(p).pagination({
			pageSize : 10,//每页显示的记录条数，默认为10
			pageList : [ 2, 5, 10, 15, 20 ],//每页显示几条记录
			beforePageText : '第',//页数文本框前显示的汉字 
			afterPageText : '页    共 {pages} 页',
			displayMsg : '当前显示 {from} - {to} 条记录    共 {total} 条记录',
			onBeforeRefresh : function() {
				$(this).pagination('loading');//正在加载数据中...
				$(this).pagination('loaded'); //数据加载完毕
			}
		});
	}
    
	
	function add() {
		var title='添加生成方案';
		var url = '<%=request.getContextPath()%>/admin/codeScheme/edit';
		var height=500;
		var width=700;
			ShowiframeWindow(url,title,width,height);
		}
	
	function editdata(target) {
		var index=getRowIndex(target);
		$('#dg').datagrid('selectRow', index);
		var row = $('#dg').datagrid('getSelected');
		var title='编辑生成方案';
		var url = '<%=request.getContextPath()%>/admin/codeScheme/edit?id='+row.id;
		var height=500;
		var width=700;
			ShowiframeWindow(url,title,width,height);
	}
	
	function deleted(target){
		var index=getRowIndex(target);
		$('#dg').datagrid('selectRow', index);
		var row = $('#dg').datagrid('getSelected');
		var url='<%=request.getContextPath()%>/admin/codeScheme/deleted?id='+row.id;
		   $.ajax({  
		        type: "POST",  
		        contentType : 'application/json;charset=utf-8', //设置请求头信息
		        url: url,  
		        async: false,   
		        dataType: "json",  
		        success: function (data) {  
		        	if(data!=null&&data!=undefined&&data!="undefined")
					{
						var msg=data.msg;
						$.messager.alert("操作提示", msg);  
						$("#dg").datagrid('reload');
					}
		  
		        }  
		    });  
	}

	</script>
</body>
</html>
