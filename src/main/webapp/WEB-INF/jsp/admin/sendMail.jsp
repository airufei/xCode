<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<title>欢迎登陆</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1, user-scalable=no">
<meta name="description" content="">
<meta name="author" content="">
<link href="<%=request.getContextPath()%>/static/login/cloud-admin.css"
	rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/static/login/font-awesome.css"
	rel="stylesheet">
</head>
<body class="login">
	<!-- PAGE -->
	<section id="page">
		<!-- HEADER -->
		<header>
			<!-- NAV-BAR -->
			<div class="container">
				<div class="row">
					<div class="col-md-4 col-md-offset-4">
						<div id="logo">
							<a href="<%=request.getContextPath()%>/zixun/1" target="_blank""><img
								src="<%=request.getContextPath()%>/image/list/xmf_Logo.png"
								height="100px" alt="logo name" /></a>
						</div>
					</div>
				</div>
			</div>
			<!--/NAV-BAR -->
		</header>
		<!--/HEADER -->
		<!-- FORGOT PASSWORD -->
		<section id="sendMail" class="visible">
			<div class="container">
				<div class="row">
					<div class="col-md-4 col-md-offset-4">
						<div class="login-box-plain">
							<h2 class="bigintro">重置密码</h2>
							<div class="divide-40"></div>
							<form role="form" id="smail">
								<div class="form-group">
									<label for="exampleInputEmail1">输入你的账号(邮箱)</label> <i
										class="fa fa-envelope"></i> <input type="email" name="email"
										class="form-control" id="exampleInputEmail1" maxlength="30">
								</div>
								<div class="form-group">
									<img
										src="<%=request.getContextPath()%>/admin/baseUser/authCode"
										id="codeImage" onclick="chageCode()" title="图片看不清？点击重新得到验证码"
										style="cursor:pointer;" />
								</div>
								<div class="form-group">
									<label for="exampleInputPassword2">图形验证码</label> <i
										class="fa fa-check-square-o"></i> <input type="text"
										class="form-control" name="imgCode" id="imgCode" maxlength="10">
								</div>
								<div class="form-group">
									<label for="exampleInputPassword2" style="color:red" id="sendMailMsg"></label> 
								</div>
								<div class="form-actions">
									<button type="button" onclick="sendMail()" id="isNextsendMail" class="btn btn-info">发送邮件</button>
								</div>
							</form>
							<div class="login-helpers">
								<a href="javascript:void(0)" onclick="toLogin()">返回登陆</a>
								<br>
							</div>
						</div>
					</div>
				</div>
			</div>
		</section>
		<!-- FORGOT PASSWORD -->
	</section>

	<script
		src="<%=request.getContextPath()%>/static/js/common/jquery-2.0.3.min.js"></script>

	<script src="<%=request.getContextPath()%>/static/login/script.js"></script>

	<script type="text/javascript">
	
	function toLogin() {
		window.location.href = "<%=request.getContextPath()%>/admin/login";
	}
	//获取验证码
	function chageCode() {
		$('#codeImage').attr('src', '<%=request.getContextPath()%>/admin/baseUser/authCode?abc=' + Math.random()); //链接后添加Math.random，确保每次产生新的验证码，避免缓存问题。
	}
	
	//发送邮件
	function sendMail() {
		$("#sendMailMsg").html("");
		var email = $("#exampleInputEmail1").val();
		var isNext = valateparms(email);
		if (isNext) {
		    $("#sendMailMsg").html("开始发送，请稍后....");
			url = "<%=request.getContextPath()%>/admin/baseUser/sendMails";
			var parms = $("#smail").serializeJson();
			ajaxPost(url, parms);
		}else
		{
		   chageCode();
		}
	}
	//参数验证
	function valateparms(email) {
		var msg = "";
		var ret = true;
		if (email == null || email == '' || email == undefined) {
			msg = "账号(邮箱)不能为";
			$("#sendMailMsg").html(msg);
			return false;
		}

		var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/;
		if (!reg.test(email)) {
			msg = "账号(邮箱)格式不正确";
			$("#sendMailMsg").html(msg);
			return false;
		}
		if (email != null && (email.length<5 || email.length >30)) {
			var msg = "账号(邮箱)长度不正确（5-30位）";
			$("#sendMailMsg").html(msg);
			return false;
		}
		return ret;
	}

	//是否含有中文
	function isChinse(str) {
		var reg = /^[u4E00-u9FA5]+$/;
		if (!reg.test(str)) {
			return false;
		}
		return true;
	}
	//是否 字母+数字
	function isNumAndStr(str) {
		var patrn = /^((\w*\d\w*[a-z]\w*)|(\w*[a-z]\w*\d\w*))$/i;
		if (str.match(patrn)) {
			return true;
		}
		return false;
	}
	//发送ajax
	function ajaxPost(url, parms) {
	$("#isNextsendMail").attr("disabled", "disabled");
		$.ajax({
			type: "POST",
			contentType: 'application/json;charset=utf-8', //设置请求头信息
			url: url,
			async: false,
			data: parms,
			dataType: "json",
			success: function (data) {
			    chageCode();
			    $("#isNextsendMail").removeAttr("disabled");
				if (data != null && data != undefined
					 && data != "undefined") {
					var res = data.result;
					var msg = data.msg;
					if (res) {
					     $("#exampleInputEmail1").val("");
					     $("#sendMailMsg").html("");
						 alert(msg);
					}else
					{
                    	$("#sendMailMsg").html(msg);
					}
				}

			}
		});
	}
	
	
	//将表单序列化成json格式的数据(但不适用于含有控件的表单，例如复选框、多选的select)
	(function ($) {
		$.fn.serializeJson = function () {
			var jsonData1 = {};
			var serializeArray = this.serializeArray();
			// 先转换成{"id": ["12","14"], "name": ["aaa","bbb"], "pwd":["pwd1","pwd2"]}这种形式
			$(serializeArray).each(
				function () {
				if (jsonData1[this.name]) {
					if ($.isArray(jsonData1[this.name])) {
						jsonData1[this.name].push(this.value);
					} else {
						jsonData1[this.name] = [
							jsonData1[this.name], this.value];
					}
				} else {
					jsonData1[this.name] = this.value;
				}
			});
			// 再转成[{"id": "12", "name": "aaa", "pwd":"pwd1"},{"id": "14", "name": "bb", "pwd":"pwd2"}]的形式
			var vCount = 0;
			// 计算json内部的数组最大长度
			for (var item in jsonData1) {
				var tmp = $.isArray(jsonData1[item]) ? jsonData1[item].length
					 : 1;
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
	
	</script>
	<!-- /JAVASCRIPTS -->
</body>
</html>