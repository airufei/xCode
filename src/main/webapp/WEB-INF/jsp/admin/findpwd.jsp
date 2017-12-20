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
<link
	href="<%=request.getContextPath()%>/static/login/uniform.default.min.css"
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
							<a href="index.html"><img
								src="<%=request.getContextPath()%>/static/login/logo-alt.png"
								height="40" alt="logo name" /></a>
						</div>
					</div>
				</div>
			</div>
			<!--/NAV-BAR -->
		</header>
		<!--/HEADER -->
		<!-- LOGIN -->
		<section id="findpwd" class="visible">
			<div class="container">
				<div class="row">
					<div class="col-md-4 col-md-offset-4">
						<div class="login-box-plain">
							<h2 class="bigintro">重置密码</h2>
							<div class="divide-40"></div>
							<form role="form" id="loginCooxin">
				             <input  type="hidden" name="email"
										class="form-control" id="exampleInputEmail1" maxlength="30">
								<div class="form-group">
									<label for="exampleInputPassword1">密码</label> <i
										class="fa fa-lock"></i> <input type="password" name="password"
										class="form-control" id="exampleInputPassword1" maxlength="16">
								</div>
								<div class="form-group">
									<label for="exampleInputPassword2">确认密码</label> <i
										class="fa fa-check-square-o"></i> <input type="password"
										class="form-control" id="exampleInputPassword2" maxlength="16">
								</div>
								<div class="form-group">
									<label for="exampleInputPassword2" style="color:red" id="findMsg"></label> 
								</div>
					
								<div class="form-actions">
									<button type="button" onclick="updatePwd()" id="isNextPwd" class="btn btn-danger">提交</button>
								</div>
							</form>
							<!-- SOCIAL REGISTER -->
							<div class="divide-20"></div>
							<!-- /SOCIAL REGISTER -->
							<div class="login-helpers">
								<a href="javascript:void(0)" onclick="toLogin()">返回登陆</a>
								<br>
							</div>
						</div>
					</div>
				</div>
			</div>
		</section>
		<!--/LOGIN -->
	</section>
	<!--/PAGE -->
	<!-- JAVASCRIPTS -->
	<!-- Placed at the end of the document so the pages load faster -->
	<!-- JQUERY -->
	<script
		src="<%=request.getContextPath()%>/static/js/common/jquery-2.0.3.min.js"></script>

	<script src="<%=request.getContextPath()%>/static/login/script.js"></script>

	<script type="text/javascript">
	
	function toLogin() {
		window.location.href = "<%=request.getContextPath()%>/admin/login";
	}
	//获取验证码
	function chageCode() {
		$('#codeImage').attr('src', '<%=request.getContextPath()%>/admin/baseUser/authCode1?abc=' + Math.random()); //链接后添加Math.random，确保每次产生新的验证码，避免缓存问题。
	}
	//注册
	function updatePwd() {

		$("#findMsg").html("");
		var password1 = $("#exampleInputPassword1").val();
		var password2 = $("#exampleInputPassword2").val();
		var email = $("#email").val();
		var isNext = valateparms(email, password1);
		if (password2 != password1) {
			var msg = "两次输入密码不一致";
			$("#findMsg").html(msg);
			return false; ;
		}
		if (isNext) {
		      $("#isNextPwd").attr("disabled", true);
			url = "<%=request.getContextPath()%>/admin/baseUser/updatePwd";
			var parms = {"email":email,"password1":password1};
			ajaxPost(url, parms);
		}
	}
	//参数验证
	function valateparms(email, password,randMun) {
		var msg = "";
		var ret = true;
		if (password == null || password == '' || password == undefined) {
			msg = "密码不能为";
			$("#findMsg").html(msg);
			return false;
		}
		if (randMun == null || randMun == '' || randMun == undefined) {
			msg = "密码不能为";
			$("#findMsg").html(msg);
			return false;
		}
		if (password != null && (password.length<6 || password.length >16)) {
			msg = "密码长度不正确（6-16位）";
			$("#findMsg").html(msg);
			return false;
		}
		if (!isNumAndStr(password)) {
			msg = "密码格式错误(建议：字母+数字和下划线且6-16位)";
			$("#findMsg").html(msg);
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
		$.ajax({
			type: "POST",
			contentType: 'application/json;charset=utf-8', //设置请求头信息
			url: url,
			async: false,
			data: parms,
			dataType: "json",
			success: function (data) {
			    $("#isNextPwd").attr("disabled", false);
				if (data != null && data != undefined
					 && data != "undefined") {
					var res = data.result;
					var msg = data.msg;
					$("#findMsg").html(msg);
					if (res) {
						window.location.href = "<%=request.getContextPath()%>/admin/login";
					}
					
				}

			}
		});
	}

	</script>
	<!-- /JAVASCRIPTS -->
</body>
</html>