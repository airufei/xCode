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
                <td title="数据库字段名">列名</td>
                <td title="默认读取数据库字段备注">说明</td>
                <td title="数据库中设置的字段类型及长度">物理类型</td>
                <td title="实体对象的属性字段类型">Java类型</td>
                <td
                        title="实体对象的属性字段（对象名.属性名|属性名2|属性名3，例如：用户user.id|name|loginName，属性名2和属性名3为Join时关联查询的字段）">Java属性
                    <i class="icon-question-sign"></i>
                </td>
                <td title="是否是数据库主键">主键</td>
                <td title="字段是否可为空值，不可为空字段自动进行空值验证">可空</td>
                <td title="选中后该字段被加入到insert语句里">插入</td>
                <td title="选中后该字段被加入到update语句里">编辑</td>
                <td title="选中后该字段被加入到查询列表里">列表</td>
                <td title="选中后该字段被加入到查询条件里">查询</td>
                <td title="编辑页显示字段">编辑页</td>
                <td title="必须插入值的字段">必须插入</td>
                <td title="必须编辑值的字段">必须编辑</td>
                <td title="排序字段">排序</td>
                <td title="该字段为查询字段时的查询匹配放松">匹配方式</td>
                <td title="字段在表单中显示的类型">表单类型</td>
                <td title="显示表单类型设置为“下拉框、复选框、点选框”时，需设置字典的类型">字典类型</td>
                <td>排序</td>
            </tr>
            <tbody>

            <c:forEach items="${colList}" var="columns" varStatus="vs">
                <tr
                    ${columns.flag eq '1'?' class="error" title="已删除的列，保存之后消失！"':''}>
                    <td nowrap>
                        <input type="hidden" name="id" value="${columns.id}"/>
                        <input type="hidden" name="flag" value="${columns.flag}"/>
                        <input type="hidden" name="tableId" value="${columns.tableId}"/>
                        <input type="hidden" name="tableName" value="${columns.tableName}"/>
                        <input name="name" value="${columns.name}"/>${columns.name}
                    </td>
                    <td><input type="text" name="comments"
                               value="${columns.comments}" maxlength="200" class="required"
                               style="width:100px;"/></td>
                    <td nowrap><input type="hidden" name="jdbcType"
                                      value="${columns.jdbcType}"/>${columns.jdbcType}</td>
                    <td><select name="javaType" class="required input-mini"
                                style="width:85px;*width:75px">
                        <c:forEach items="${config.javaTypeList}" var="dict">
                            <option value="${dict.dictKey}"
                                ${dict.dictKey==columns.javaType?'selected':''}
                                    title="${dict.dictValue}">${dict.dictValue}</option>
                        </c:forEach>
                    </select></td>
                    <td><input type="text" name="javaField"
                               value="${columns.javaField}" maxlength="200"
                               class="required input-small"/></td>
                    <td><select name="isPk" class="required input-mini"
                                style="width:75px">
                        <option value="0" ${columns.isPk=='0' ?'selected':''} title="否">否</option>
                        <option value="1" ${columns.isPk=='1' ?'selected':''} title="是">是</option>
                    </select></td>
                    <td><select name="isNull" class="required input-mini"
                                style="width:75px">
                        <option value="1" ${columns.isNull=='1' ?'selected':''}
                                title="是">是
                        </option>
                        <option value="0" ${columns.isNull=='0' ?'selected':''}
                                title="否">否
                        </option>
                    </select></td>
                    <td><select name="isInsert" class="required input-mini"
                                style="width:75px">
                        <option value="1" ${columns.isInsert=='1' ?'selected':''}
                                title="是">是
                        </option>
                        <option value="0" ${columns.isInsert=='0' ?'selected':''}
                                title="否">否
                        </option>
                    </select></td>
                    <td><select name="isEdit" class="required input-mini"
                                style="width:75px">
                        <option value="1" ${columns.isEdit=='1' ?'selected':''}
                                title="是">是
                        </option>
                        <option value="0" ${columns.isEdit=='0' ?'selected':''}
                                title="否">否
                        </option>
                    </select></td>
                    <td><select name="isList" class="required input-mini"
                                style="width:75px">
                        <option value="1" ${columns.isList=='0' ?'selected':''}
                                title="是">是
                        </option>
                        <option value="0" ${columns.isList=='1' ?'selected':''}
                                title="否">否
                        </option>
                    </select></td>
                    <td><select name="isQuery" class="required input-mini"
                                style="width:75px">
                        <option value="0" ${columns.isQuery=='0' ?'selected':''}
                                title="否">否
                        </option>
                        <option value="1" ${columns.isQuery=='1' ?'selected':''}
                                title="是">是
                        </option>
                    </select></td>
                    <td><select name="isEditPage" class="required input-mini"
                                style="width:75px">
                        <option value="1" ${columns.isEditPage=='1' ?'selected':''}
                                title="是">是
                        </option>
                        <option value="0" ${columns.isEditPage=='0' ?'selected':''}
                                title="否">否
                        </option>

                    </select></td>
                    <td><select name="isInsertRequiredField" class="required input-mini"
                                style="width:75px">
                        <option value="0" ${columns.isInsertRequiredField=='0' ?'selected':''}
                                title="否">否
                        </option>
                        <option value="1" ${columns.isInsertRequiredField=='1' ?'selected':''}
                                title="是">是
                        </option>

                    </select></td>
                    <td><select name="isUpdateRequiredField" class="required input-mini"
                                style="width:75px">
                        <option value="0" ${columns.isUpdateRequiredField=='0' ?'selected':''}
                                title="否">否
                        </option>
                        <option value="1" ${columns.isUpdateRequiredField=='1' ?'selected':''}
                                title="是">是
                        </option>

                    </select></td>
                    <td><select name="isSort" class="required input-mini"
                                style="width:75px">
                        <option value="0" ${columns.isSort=='0' ?'selected':''}
                                title="否">否
                        </option>
                        <option value="1" ${columns.isSort=='1' ?'selected':''}
                                title="是">是
                        </option>

                    </select></td>
                    <td><select name="queryType" class="required input-mini">
                        <c:forEach items="${config.queryTypeList}" var="dict">
                            <option value="${dict.dictKey}" title="${dict.dictValue}">${dict.dictValue}</option>
                        </c:forEach>
                    </select></td>
                    <td><select name="showType" class="required"
                                style="width:100px;">
                        <c:forEach items="${config.showTypeList}" var="dict">
                            <option value="${dict.dictKey}"
                                ${dict.dictKey==columns.showType?'selected':''}
                                    title="${dict.dictValue}">${dict.dictValue}</option>
                        </c:forEach>
                    </select></td>
                    <td><input type="text" name="dictType"
                               value="${columns.dictType}" maxlength="200" class="input-mini"/>
                    </td>
                    <td><input type="text" name="sort" value="${columns.sort}"
                               maxlength="200" class="required input-min digits"/></td>
                </tr>
            </c:forEach>
            </tbody>
            <tr>

                <td colspan="15"></td>

            </tr>
            <tr>
                <td colspan="2"></td>
                <td><input type="button" value="保存" onclick="save()"></td>
                <td><input type="button" value="取消" onclick="CloseWind()"></td>
                <td colspan="12"></td>
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
            url: "<%=request.getContextPath()%>/admin/codetable/saveColumn",
            async: false,
            data: parms,
            dataType: "json",
            success: function (data) {
                if (data != null && data != undefined
                    && data != "undefined") {
                    var res = data.result;
                    if (res) {
                        closeDialogAndreload();
                    }
                    var msg = data.msg;
                    $.messager.alert("操作提示", msg);
                }

            }
        });
    }
</script>
</body>
</html>
