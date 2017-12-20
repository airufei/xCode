<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/admin/common/head.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>业务表配置</title>

</head>
<body>
	<table width="100%%">
		<tr>
			<td>
				<div style="height: auto; background: #fafafa; padding: 0px;"
					class="easyui-panel" title="生成方案管理" iconCls="icon-search"
					collapsible="true" minimizable="false" maximizable="false"
					closable="false">
					<form id="searchForm" name="searchForm" method="post" action=""
						onsubmit="return searchDGData(this.id,'dg');">
						<div>
							<table width="98%" cellspacing="7">
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
				<table id="dg" name="dg" title="业务表配置" iconCls="icon-list"
					width="100%" singleSelect="true" idField="id" loadMsg="请稍候......"
					pageList="[10,20,30,40,50,100,150,200]" rownumbers="true">
				</table>
			</td>
		</tr>
	</table>
	<div>

       <!-- 弹出页面 （start）-->
		<div id="addDialog" class="easyui-window" title=""
			style="width:600px;height:400px"
			data-options="iconCls:'icon-save',modal:true">

			<iframe scrolling="auto" id='openWindIframe' frameborder="0" src=""
				style="width: 100%; height: 100%;"> </iframe>

		</div>
        <!-- 弹出页面（end） -->
        <!-- 弹出层（start） -->
		<div id="dd">
			<table style="border-collapse:separate; border-spacing:20px;">
				<tr>
					<td>选择数据表:</td>
					<td colspan="4"><input id="codetable" class="easyui-combobox"
						style="width:250px" name="codetable"></td>
				</tr>
				<tr>
					<td> <input id="tableName" type="hidden"></input></td>
					<td></td>
					<td><a href="javascript:void(0);" onclick="isExsitTable()">下一步</a></td>
					<td></td>
					<td></td>
				</tr>
			</table>
		</div>
       <!-- 弹出层（end） -->
	</div>



	<script type="text/javascript">
	$(document).ready(function() {
		serach();
		$('#addDialog').window('close'); // close a window
		selectTable();
	});

	// 点击增加
	function add() {
		getTbale();
		$('#dd').dialog('open');
	}
	
	function isExsitTable()
	{
		var tableName=$("#tableName").val();
		var url='<%=request.getContextPath()%>/admin/codetable/isExsitTable?tableName='+tableName;
		 $.ajax({  
		        type: "POST",  
		        contentType : 'application/json;charset=utf-8', //设置请求头信息
		        url:url,  
		        async: false,  
		        dataType: "json",  
		        success: function (data) {  
		        	if(data!=null&&data!=undefined&&data!="undefined")
					{
						var res=data.result;
						var msg=data.msg;
						if(res)
						{
							nextWindow(tableName);
						}else
						{
							$.messager.alert("操作提示", msg);  
						}
						
					}
		  
		        }  
		    });  
	}
	//下一步，进入表的列数据信息
	function nextWindow(tableName) {
		if(tableName==null&&tableName==undefined)
		{
			return;
		}
		var title='添加业务表配置';
		var url = '<%=request.getContextPath()%>/admin/codetable/editTable?name='+tableName;
		var height=300;
		var width=600;
		$('#dd').dialog('close'); // 
		ShowiframeWindow(url,title,width,height);
	}
	//查询所有物理表数据
	function getTbale()
	{
		var url="<%=request.getContextPath()%>/admin/codetable/getTable";
		$('#codetable').combobox({
			url : url,
			valueField : 'name',
			textField : 'comments',
			onChange: function (n,o) {
				$("#tableName").val(n);
			}
		});
	}
	//选择数据库表的对话框
	function selectTable()
	{
		$('#dd').dialog({
		    title: '选择数据库表',
		    width: 400,
		    height: 200,
		    closed: true,
		    cache: false,
		    modal: true
		});
		
	}
	function deleted(target){
		var index=getRowIndex(target);
		$('#dg').datagrid('selectRow', index);
		var row = $('#dg').datagrid('getSelected');
		var url='<%=request.getContextPath()%>/admin/codetable/deleted?id='+row.id;
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
	//编辑
	function editdata(target)
	{
	    var index=getRowIndex(target);
		$('#dg').datagrid('selectRow', index);
		var row = $('#dg').datagrid('getSelected');
		if(row!=null)
		{
		  var tableName=row.name;
		  nextWindow(tableName);
		}
		
	}
	//列表数据查询
	function serach() {
		var dataPara = getFormJson($("#searchForm"));
		var url = '<%=request.getContextPath()%>/admin/codetable/list';
			$('#dg').datagrid({
				url : url,
				type : "post",
				datatype : "json",
				queryParams : dataPara,
				loadMsg : "数据加载中，请稍后...",
				fitColumns : true,
				pageSize : 10,
				pagination : true,
				striped : true,
				collapsible : true,
				remoteSort : true,
				singleSelect : true,
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
				onDblClickRow: function (index, rowData) //单击事件
				{
					if(rowData!=null)
					{
						var tableName=rowData.name;
						nextWindow(tableName);
					}
					
				},
				columns : [ [ {
					field : 'name',
					title : '名称',
					width : 100,
					align : 'left'
				}, {
					field : 'comments',
					title : '描述',
					width : 100,
					align : 'left'
				}, {
					field : 'className',
					title : '实体名称',
					width : 100,
					align : 'left'
				}, {
					field : 'createtimestr',
					title : '创建时间',
					width : 100,
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
			    } ] ]
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
	</script>
</body>
</html>
