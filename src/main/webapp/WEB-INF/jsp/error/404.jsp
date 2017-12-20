<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>404</title>
<meta http-equiv="Cache-Control" content="no-transform " />
<meta name="viewport"
	content="width=device-width,initial-scale=1.0,user-scalable=no" />
<meta name="keywords"
	content="小蜜蜂，小蜜网，小蜜蜂资讯，电影票房，小蜜蜂电影，美女图片，搞笑视频，好文推荐，明星动态，今日头条">
<meta name="description" content="404" keywords="小蜜蜂资讯，电影票房，小蜜蜂电影，美女图片，" />
<link rel="stylesheet" id="sytle-css"
	href="<%=request.getContextPath()%>/static/css/style.css"
	type="text/css" media="all">
<link rel="stylesheet" id="home-css"
	href="<%=request.getContextPath()%>/static/css/home.css"
	type="text/css" media="all">
<link rel="stylesheet" id="ishayou-pc-css"
	href="<%=request.getContextPath()%>/static/css/cooxin-pc.css"
	type="text/css" media="all">
<link rel="stylesheet" id="ishayou-phone-css"
	href="<%=request.getContextPath()%>/static/css/cooxin-phone.css"
	type="text/css" media="all">
<link rel="shortcut icon" type="/x-icon"
	href="<%=request.getContextPath()%>/favicon.ico" media="screen" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/common/jquery1.72.js"></script>
</head>
<style>
.backhome {
	float: left;
	margin-left: 10px;
	display: block;
	height: 35px;
	line-height: 36px;
	color: #fff;
	font-size: 17px;
	text-align: center;
}
</style>
<body>
	<%@ include file="/WEB-INF/jsp/common/menu.jsp"%>
	<div id="main">
		<div id="container">
			<article class="content"> <header class="contenttitle">
			<h2 class="mscctitle">
				<a href="#"> 找不到此页面 </a>
			</h2>
			<br>
			<div style="width:100%;height:1px;border-bottom:1px dashed #000;"></div>
			<br>

			</header>
			<div class="content-text" style="word-wrap:break-word;">
			 <img src="<%=request.getContextPath()%>/image/list/404-1.png">
			</div>
			<!--content_text-->
			<div class="post-like">
				<a href="<%=request.getContextPath()%>/news/1.html" 
					class="favorite" style="width:200px"><span class="backhome"> 返回首页 </span> </a>
			</div>

			</article>
			<!--content-->
			<!--相关文章-->
			<div class="xianguan">
				<div class="xianguantitle">热点推荐</div>
               <ul class="pic">
				<c:forEach items="${relList}" var="item">
					<li><a href="<%=request.getContextPath()%>/news/detail/${item.id}.html" target="_blank">
							<img src="<%=request.getContextPath()%>${item.coverurl}" />
					</a><a
								href="<%=request.getContextPath()%>/news/detail/${item.id}.html" title="${item.title}"
								target="_blank"> <c:if test="${fn:length(item.title)>32}"> ${fn:substring(item.title, 0, 32)}... </c:if><c:if test="${fn:length(item.title)<=32}"> ${item.title}</c:if>  </a>
						<address class="xianaddress">
							<time> ${item.createtimestr} </time>
							- 阅  ${item.readcount} &nbsp;&nbsp;<span class="zan">推荐</span>
						</address>
						<p>${item.description}</p>
							
						</li>
				</c:forEach>
				</ul>
			</div>
			<!--相关文章-->
		</div>
		<!--Container End-->
		<aside id="sitebar">
	     <div class="sitebar_list" style="position: static;">
				<h4 class="sitebar_title">猜猜你喜欢</h4>
				<ul class="sitebar_list_ul">
					<c:forEach items="${hotList}" var="item">
					<li><a
								href="<%=request.getContextPath()%>/news/detail/${item.id}.html" title="${item.title}"
								target="_blank"> <c:if test="${fn:length(item.title)>18}"> ${fn:substring(item.title, 0, 18)}... </c:if><c:if test="${fn:length(item.title)<=18}"> ${item.title}</c:if>  </a></li>
					</c:forEach>
				</ul>
			</div>
		<script type="text/javascript">
			$(function() {
				var elm = $('.sitebar_list');
				var startPos = $(elm).offset().top;
				$.event.add(window, "scroll", function() {
					var p = $(window).scrollTop();
					$(elm).css('position',
							((p) > startPos) ? 'fixed' : 'static');
					$(elm).css('top', ((p) > startPos) ? '20px' : '');
				});
			});
		</script> </aside>
		<div class="clear"></div>
	</div>

<!--网站底部-->
	<%@ include file="/WEB-INF/jsp/common/bottom.jsp"%>
	<!--网站底部-->
</body>
</html>
