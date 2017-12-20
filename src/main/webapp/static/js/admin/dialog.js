
//关闭对话框
function closeDialog() {
	$("#reset").click();
	parent.$('#addDialog').window('close');
}

//关闭对话框
function closeDialogAndreload() {
	$("#reset").click();
	parent.$('#addDialog').window('close');
	parent.$("#dg").datagrid('reload');
}


//弹出页面
function  ShowiframeWindow(url,title,width,height) {
	$('#openWindIframe')[0].src=url;//设置打开的页面地址
	$("#addDialog").window({title:title});//设置打开的页面标题
	$("#addDialog").window({width:width});//设置打开的页面宽度
	$("#addDialog").window({height:height});//设置打开的页面高度
	$("#addDialog").dialog('open');
}

//修改弹出页面
function  updateShowiframeWindow(url,title,width,height) {
	parent.window({title:title});//设置打开的页面标题
	parent.window({width:width});//设置打开的页面宽度
	parent.window({height:height});//设置打开的页面高度
}
//将form中的值转换为键值对
function getFormJson(frm){
 var o={};
 var a=$(frm).serializeArray();
 $.each(a,function(){
  if(o[this.name]!==undefined){
   if(!o[this.name].push){
    o[this.name]=[o[this.name]];
   }
   o[this.name].push(this.value || '');
  }else{
   o[this.name]=this.value || '';
  }
 });
 return o;
}

//将表单序列化成json格式的数据(但不适用于含有控件的表单，例如复选框、多选的select)
(function($){
    $.fn.serializeJson = function(){
        var jsonData1 = {};
        var serializeArray = this.serializeArray();
        // 先转换成{"id": ["12","14"], "name": ["aaa","bbb"], "pwd":["pwd1","pwd2"]}这种形式
        $(serializeArray).each(function () {
            if (jsonData1[this.name]) {
                if ($.isArray(jsonData1[this.name])) {
                    jsonData1[this.name].push(this.value);
                } else {
                    jsonData1[this.name] = [jsonData1[this.name], this.value];
                }
            } else {
                jsonData1[this.name] = this.value;
            }
        });
        // 再转成[{"id": "12", "name": "aaa", "pwd":"pwd1"},{"id": "14", "name": "bb", "pwd":"pwd2"}]的形式
        var vCount = 0;
        // 计算json内部的数组最大长度
        for(var item in jsonData1){
            var tmp = $.isArray(jsonData1[item]) ? jsonData1[item].length : 1;
            vCount = (tmp > vCount) ? tmp : vCount;
        }

        if(vCount > 1) {
            var jsonData2 = new Array();
            for(var i = 0; i < vCount; i++){
                var jsonObj = {};
                for(var item in jsonData1) {
                    jsonObj[item] = jsonData1[item][i];
                }
                jsonData2.push(jsonObj);
            }
            return JSON.stringify(jsonData2);
        }else{
            return "[" + JSON.stringify(jsonData1) + "]";
        }
    };
})(jQuery);