<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/admin/common/head.jsp"%>
<html>
<head>
	<title>日志管理</title>
</head>
<body>
	<table width="99%">
		<tr>
			<td>
				<div style="height: auto; background: #fafafa; padding: 0px;"
					class="easyui-panel" title="日志管理" iconCls="icon-search"
					collapsible="true" minimizable="false" maximizable="false"
					closable="false">
					<form id="searchForm" name="searchForm" method="post" action="">
						<div>
							<table width="99%" cellspacing="7">
								<tr>
									<td>
										<form id="searchForm">
											<table style="width:98%">
											 
													<tr>
													<td style="text-align:right;"> 日志内容：</td>
													<td style="width:35%;text-align:left;">
													<input  class="easyui-textbox" style="width:80%;height:32px" name="content" maxlength="200" />
													</td>
													<td style="text-align:right;"> 日志类型：</td>
													<td style="width:35%;text-align:left;">
													<input  class="easyui-textbox" style="width:80%;height:32px" name="logtype" maxlength="200" />
													</td>
													 <td style="width:16%;text-align:center;">
													<input id="btnSubmit" class="btn btn-primary"
														type="button" onclick="serach()" value="查询" />
													</td>
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
				<table id="dg" name="dg" title="日志管理" iconCls="icon-list"
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
		var url = '<%=request.getContextPath()%>/admin/log/list';
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
			columns : [ [ 
			 {
				field : 'content',
				title : '日志内容',
                width:100,
				align : 'left'
			},
			 {
				field : 'rqurl',
				title : '请求路径',
                width:150,
				align : 'left'
			},
			 {
				field : 'logtype',
				title : '日志类型',
                width:100,
				align : 'left'
			},
			 {
				field : 'remark',
				title : '备注',
                width:100,
				align : 'left'
			}
			,
			 {
				field : 'createtimestr',
				title : '时间',
                width:100,
				align : 'left'
			},
			{
			field: 'id',
			title: '操作',
			width: 100,
			align: 'center',
			formatter: function(value, row, index) {
				if (row!=null) 
				{
					var c = '<a href="javascript:void(0)" onclick="deleted(this)">删除</a>';
					return  c;
				}
			  }
		    }
		  ]
		 ]
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
	 function deleted(target)
	 {
	      var index=getRowIndex(target);
		  $('#dg').datagrid('selectRow', index);
		  var row = $('#dg').datagrid('getSelected');
		  var id=row.id;
	      var url='<%=request.getContextPath()%>/admin/log/delete?id='+id;
	      $.messager.confirm("操作提示", "您确定要执行操作吗？", function (data) {  
            if (data) {  
                postdata(url);  
            }  
        });  
	 }

     function postdata(url)
     {
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