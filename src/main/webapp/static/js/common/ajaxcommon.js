//是否手机设备
function isMobile()
{
    var isMobile = false;
    var userAgentInfo = navigator.userAgent.toLowerCase();
    var Agents = ["android", "iphone", "symbianos", "windows phone", "ipad", "ipod"];
    for (var v = 0; v < Agents.length; v++)
    {
        if (userAgentInfo.indexOf(Agents[v]) >= 0)
        {
            isMobile = true;
            break;
        }
    }
    return isMobile;
}
//获取列表页面的推荐数据
function getHotList(webRoot, typeid)
{
    if (isMobile())
    {
        return false;
    }
    var url = "";
    if (webRoot == null || webRoot == "")
    {
        url = "/common/getHotList?typeid=" + typeid;
    }
    else
    {
        url = webRoot + "/common/getHotList?typeid=" + typeid;
    }
    $.ajax(
    {
        type: "POST",
        contentType: 'application/json;charset=utf-8', //设置请求头信息
        url: url,
        dataType: 'JSON', //加入这个选项即可
        success: function(data)
        {
            if (data != null && data != undefined && data != "undefined")
            {
                var res = data.result;
                if (res)
                {
                    var list = data.list;
                    if (list != null)
                    {
                        var str = "";
                        for (var i = 0; i < list.length; i++)
                        {
                            var item = list[i];
                            var title = item.title;
                            if (title != null && title.length > 18)
                            {
                                title = title.substr(0, 18) + "...";
                            }
                            if (typeid == null || typeid == "" || typeid == undefined)
                            {
                                typeid = "news";
                            }
                            if (typeid == "culture")
                            {
                                typeid = "dushu";
                            }
                            str = str + '<li><a href=' + webRoot + '"/detail/' + item.id + '.html" title="' + item.title + '" target="_blank">' + title + '</a></li>';
                        }
                        $(".sitebar_list_ul").append(str);
                    }
                }
            }
        }
    });
}
//获取列表页面的推荐数据
function getHotAndRelList(webRoot, keywords, typeid, id)
{
    if (isMobile())
    {
        return false;
    }
    $.ajax(
    {
        type: "POST",
        contentType: 'application/json;charset=utf-8', //设置请求头信息
        url: webRoot + "/common/getHotAndRelList?typeid=" + typeid + "&keywords=" + keywords + "&id=" + id,
        async: false,
        dataType: "json",
        success: function(data)
        {
            if (data != null && data != undefined && data != "undefined")
            {
                var res = data.result;
                if (res)
                {
                    var hoList = data.hoList;
                    if (hoList != null)
                    {
                        var hotList = hoList.hostList;
                        var relList = hoList.relList;
                        var str = "";
                        for (var i = 0; i < hotList.length; i++)
                        {
                            var item = hotList[i];
                            var title = item.title;
                            if (title != null && title.length > 18)
                            {
                                title = title.substr(0, 18) + "...";
                            }
                            if (typeid == null || typeid == "" || typeid == undefined)
                            {
                                typeid = "news";
                            }
                            if (typeid == "culture")
                            {
                                typeid = "dushu";
                            }
                            str = str + '<li><a href=' + webRoot + '"/detail/' + item.id + '.html" title="' + item.title + '" target="_blank">' + title + '</a></li>';
                        }
                        $(".sitebar_list_ul").append(str);
                        var str2 = "";
                        for (var i = 0; i < relList.length; i++)
                        {
                            var item = relList[i];
                            var title = item.title;
                            if (title != null && title.length > 30)
                            {
                                title = title.substr(0, 20) + "...";
                            }
                            if (typeid == null || typeid == "" || typeid == undefined)
                            {
                                typeid = "news";
                            }
                            if (typeid == "culture")
                            {
                                typeid = "dushu";
                            }
                            str2 = str2 + '<li><a href=' + webRoot + '"/detail/' + item.id + '.html" target="_blank"><img src="' + item.coverurl + '" /></a><a href=' + webRoot + '"/detail/' + item.id + '.html" title="' + item.title + '" target="_blank">' + title + '</a></li>';
                        }
                        $(".pic").append(str2);
                    }
                }
            }
        }
    });
}
var isImgCode = false;
//提交评论
function subcomment()
{
    var rootUrl = $("#rootUrl").val();
    var url = rootUrl + '/coment/save';
    var comment = $("#message").val();
    var imgCode = $("#imgCode").val();
    var contentid = $("#contentid").val();
    var typeid = "news_coment";
    if (isImgCode && (imgCode == "" || imgCode == null || imgCode == "undefined"))
    {
        alert("请输入验证码");
        return false;
    }
    if (comment == "" || comment == null || comment == "undefined")
    {
        alert("请输入内容");
        return false;
    }
    if (contentid == "" || contentid == null || contentid == "undefined")
    {
        return false;
    }
    if (comment.length > 200)
    {
        alert("最大输入200字符，请重新输入");
        return false;
    }
    var parms = {
        "imgCode": imgCode,
        "coments": comment,
        "contentid": contentid,
        "typeid": typeid
    };
    parms = JSON.stringify(parms); //可以将json对象转换成json对符串
    $.ajax(
    {
        type: "POST",
        contentType: 'application/json;charset=utf-8', //设置请求头信息
        url: url,
        data: parms,
        dataType: "json",
        success: function(data)
        {
            if (data != null && data != undefined && data != "undefined")
            {
                var result = data.result;
                var msg = data.msg;
                isImgCode = data.isShow;
                if (result)
                {
                    $("#message").val("");
                    $("#imgCode").val("");
                    var page = 1;
                    getcommentList(page);
                }
                if (isImgCode)
                {
                    var target = document.getElementById("img_code");
                    var target2 = document.getElementById("img_code_input");
                    target2.style.display = "block";
                    target.style.display = "block";
                    chageCode();
                }
                else
                {
                    var target = document.getElementById("img_code");
                    var target2 = document.getElementById("img_code_input");
                    target2.style.display = "none";
                    target.style.display = "none";
                }
                alert(msg);
            }
        }
    });
}
//获取文章列表评论信息
function getcommentList(page)
{
    var rootUrl = $("#rootUrl").val();
    var url = rootUrl + '/coment/list';
    var contentid = $("#contentid").val();
    if (contentid == "" || contentid == null || contentid == "undefined")
    {
        return false;
    }
    var typeid = "news_coment";
    var page = 1;
    url=url+"?contentid="+contentid+"&typeid="+typeid+"&page="+page;
   /* var commparms = {
        "contentid": contentid,
        "typeid": typeid,
        "page": page
    };
    parms = JSON.stringify(commparms); //可以将json对象转换成json对符串
*/  //获取文章列表评论信息
    $.ajax(
    	    {
    	        type: "POST",
    	        contentType: 'application/json;charset=utf-8', //设置请求头信息
    	        url: url,
    	        dataType: "json",
    	        success: function(data)
    	        {
    	            if (data != null && data != undefined && data != "undefined")
    	            {
    	                var list = data.list;
    	                if (list == null)
    	                {
    	                    return false;
    	                }
    	                var target = $("#cmment_table");
    	                if (target != null && target != undefined && target != "undefined")
    	                {
    	                    target.remove();
    	                }
    	                var str = '<table id="cmment_table" style="border-collapse:separate; border-spacing:0px 20px;">';
    	                for (var i = 0; i < list.length; i++)
    	                {
    	                    var comment = list[i].coments;
    	                    var createtimestr = list[i].createtimestr;
    	                    str = str + '<tr><td style="width:10%">游客：</td><td style="width:90%">' + comment + '</td></tr>';
    	                    str = str + '<tr><td style="width:10%"></td><td style="width:90%">' + createtimestr + '</td></tr>';
    	                    str = str + '<tr><td colspan="2"><div style="width:100%;height:1px;border-bottom:1px dashed #000;"></div></td></tr>';
    	                }
    	                str = str + "<table>";
    	                var $boxes = $(str);
    	                $('#cmment_list').append($boxes);
    	            }
    	        }
    	    });
}


//获取验证码
function chageCode()
{
    var rootUrl = $("#rootUrl").val();
    $('#codeImage').attr('src', rootUrl + '/admin/baseUser/authCode?abc=' + Math.random()); //链接后添加Math.random，确保每次产生新的验证码，避免缓存问题。
}