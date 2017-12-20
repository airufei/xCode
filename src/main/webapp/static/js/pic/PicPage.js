
        var pageIndex = 1;
        function Submit(tagId) {
            var showmessage = document.getElementById('showMessge');
            var showDIV = document.getElementById('showDIV');
            $.ajax(
            {
                type: "post",
                url: "http://www.kuxin.cc/Server.aspx",
                data: { pageIndex: pageIndex, tagId: tagId },   // 把要验证的参数传过去
                datatype: "json",   // 数据类型为JSON格式的验证
                success: function (resJson) {
                    if (resJson == "-2") {
                        showDIV.style.display = "block";
                        showmessage.innerText = "你刷新太快，休息一会儿吧!"
                    }
                    else if (resJson == "-1") {
                        removeAllChild();
                        AddPicTitle();
                        showDIV.style.display = "block";
                        showmessage.innerText = "没有了，看看其他的吧！"

                    } else {
                        var jsonObj = eval("(" + resJson + ")");
                        if (jsonObj.list.length > 0) {
                            InsertPic(jsonObj);
                            pageIndex++;
                        }
                    }

                }
            });
        }
        function InsertPic(jsonObj) {
            var contentDiv = $("#Div2");
            for (var i = 0; i < jsonObj.list.length; i++) {
                var zan = 'javascript:AddIlike(' + jsonObj.list[i].Photoid + ')';
                var id = 'picHCCSSrefId' + '' + jsonObj.list[i].Photoid + '';
                var newDiv = '<div class="box showpic"><div class="picbox"><a href=' + '/Photo/BigPhoto.aspx?Photoid=' + jsonObj.list[i].Photoid + ' target="_blank"><img height=' + jsonObj.list[i].PhotoHigh + ' width="250px" alt=' + jsonObj.list[i].Descreate + ' class="txpic" src=' + jsonObj.list[i].PhotoName + ' style="display: block;"></a></div><p>' + jsonObj.list[i].Descreate + '</p><div class="actions"><div class="righter"><a id=' + id + ' class="button white" href=' + zan + '>赞(' + jsonObj.list[i].ILike + ')</a></div></div></div>';
                //contentDiv.append(newDiv);
                var $boxes = $(newDiv);
                $('#container').append($boxes).masonry('appended', $boxes);
            }
        }

        //添加标签部分
        function AddPicTitle() {
            var newDiv = ' <div class="box showpic"><div class="picbox"> <table width="272" border="0">  <tr> <td width="88" height="26" style="border:1"> <div align="center"><h3>  <a href="http://99796.com/index.html" style="width: 250px" target="_blank">网站首页</a></h3></div>  </td> <td width="85"><div align="center"><a href="javascript:Submit(45)" style="width: 250px" target="_blank">奇葩世界</a></div></td><td width="85"><div align="center"><a href="javascript:Submit(25)" style="width: 250px" target="_blank">胜点旅游</a></div></td></tr> <tr> <td height="24"><div align="center">  <h3>  <a href="../UpLoadPic.aspx" style="width: 250px" target="_blank">图片上传</a></h3></div></td>     <td>     <div align="center"> <a href="javascript:Submit(15)" style="width: 250px" target="_blank">美女</a></div></td><td><div align="center"><a href="javascript:Submit(9)" style="width: 250px" target="_blank">幽默搞笑</a></div></td></tr><tr><td height="26"><div align="center"><h3>  <a href="http://99796.com/duanzi.html" style="width: 250px" target="_blank">经典段子</a></h3></div></td><td><div align="center"><a href="javascript:Submit(25)" style="width: 250px" target="_blank">胜点旅游</a></div></td><td><div align="center"><a href="javascript:Submit(29)" style="width: 250px" target="_blank">天真可爱</a></div></td></tr><tr><td height="25"><div align="center"><h3> <a href="http://99796.com/Login.aspx" style="width: 250px" target="_blank">登陆酷信</a> </h3></div></td><td><div align="center"><a href="javascript:Submit(36)" style="width: 250px" target="_blank">冰雪世界</a></div></td><td><div align="center"><a href="javascript:Submit(26)" style="width: 250px" target="_blank">精彩一刻</a></div></td></tr><tr><td height="25"><div align="center"><h3> <a href="http://99796.com/Photo/BigPhoto.aspx?albumx=-1" style="width: 250px" target="_blank">高清大图</a> </h3></div></td><td><div align="center"><a href="javascript:Submit(30)" style="width: 250px" target="_blank">美丽婚纱</a></div></td><td><div align="center"><a href="javascript:Submit(31)" style="width: 250px" target="_blank">餐厅美食</a></div></td></tr></table></div></div>';
                var $boxes = $(newDiv);
                $('#container').append($boxes).masonry('appended', $boxes);
            }
        

        //删除文章列表div以下的所有节点
        function removeAllChild() {
            var div = $("#container");
            while (div.hasChildNodes()) //当div下还存在子节点时 循环继续
            {
                div.removeChild(div.firstChild);
            }
        }

        function AddPhotoWall(pid) {

            $.ajax({
                type: "GET",
                url: "/AddPhotoWall.aspx?id=" + pid,
                success: function (de) {
                    if (de == "1") {
                        alert("成功");
                    } else if (de == "-1") {

                        alert("已经添加过了不能重复添加");
                    } else {
                        alert("添加失败");
                    }

                }

            });
        }


        function AddIlike(pid) {

            var picId = document.getElementById("picHCCSSrefId" + pid).innerText;
            var start = picId.indexOf("(");
            var end = picId.indexOf(")");
            var count = Number(picId.substring(start + 1, end)) + Number(1);
            $.ajax({

                type: "GET",
                url: "/AddIlike.aspx?id=" + pid,
                success: function (de) {
                    if (de == "1") {
                        document.getElementById("picHCCSSrefId" + pid).innerText = "赞(" + count + ")"
                    } else if (de == "-1") {
                        alert("你已经赞过了！谢谢！");
                    }

                }

            });
        } 
  