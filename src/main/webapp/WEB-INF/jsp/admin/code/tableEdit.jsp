<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/jsp/admin/common/head.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>业务表配置</title>
    <style>
        .table {
            margin-bottom: 8px;
            border-collapse: separate;
            border-spacing: 20px;
        }

        .table th, .table td {
            vertical-align: middle;
        }

        .table td i {
            margin: 0 2px;
        }

        .table-nowrap td {
            width: 200px;
            max-width: 175px;
            overflow: hidden;
            white-space: nowrap;
            text-overflow: ellipsis;
        }
    </style>
</head>
<body>
<center>
    <form id="addForm" name="addForm" method="post">
        <input name="id" value="${tb.id}" maxlength="200" type="hidden"/>
        <table id="contentTable"
               class="table table-striped table-bordered table-condensed">
            <tr>
                <td>表名</td>
                <td><input name="name" id="tableName" value="${tb.name}" maxlength="200"
                           readonly="readonly" style="background-color:#DDDDDD"/></td>

                <td>说明:</td>
                <td><input name="comments" value="${tb.comments}"
                           maxlength="200"/></td>
            </tr>
            <tr>
                <td>类名</td>
                <td><input name="className" value="${tb.className}"
                           maxlength="200"/></td>

                <td>备注:</td>
                <td><textarea name="remark" value="${tb.remark}" maxlength="200"></textarea>
                </td>
            </tr>
            <tr>
                <td></td>
                <td><input type="button" value="保存并下一步" onclick="save()"></td>
                <td><input type="button" value="取消" onclick="closeDialogAndreload()"></td>
                <td></td>
            </tr>
        </table>
    </form>

</center>
<script type="text/javascript">
    //将表单序列化成json格式的数据(但不适用于含有控件的表单，例如复选框、多选的select)
    (function ($) {
        $.fn.serializeJson = function () {
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
            for (var item in jsonData1) {
                var tmp = $.isArray(jsonData1[item]) ? jsonData1[item].length : 1;
                vCount = (tmp > vCount) ? tmp : vCount;
            }

            if (vCount > 1) {
                var jsonData2 = new Array();
                for (var i = 0; i < vCount; i++) {
                    var jsonObj = {};
                    for (var item in jsonData1) {
                        jsonObj[item] = jsonData1[item][i];
                    }
                    jsonData2.push(jsonObj);
                }
                return JSON.stringify(jsonData2);
            } else {
                return "[" + JSON.stringify(jsonData1) + "]";
            }
        };
    })(jQuery);

    function save() {
        var parms = $("#addForm").serializeJson();
        $.ajax({
            type: "POST",
            contentType: 'application/json;charset=utf-8', //设置请求头信息
            url: "<%=request.getContextPath()%>/admin/codetable/save",
            async: false,
            data: parms,
            dataType: "json",
            success: function (data) {
                if (data != null && data != undefined && data != "undefined") {
                    var res = data.result;
                    var msg = data.msg;
                    if (res) {
                        nextWindow();
                    } else {
                        $.messager.alert("操作提示", msg);
                    }

                }

            }
        });
    }

    //下一步，进入表的列数据信息
    function nextWindow() {
        var tableName = $("#tableName").val();
        var url = '<%=request.getContextPath()%>/admin/codetable/ediTtableColumn?tableName=' + tableName;
        var title = '添加业务表配置';
        var height = 400;
        var width = 900;
        //updateShowiframeWindow(url,title,width,height);
        window.location.href = url;
    }

    function checkForm() {

        return true;
    }
</script>
</body>
</html>
