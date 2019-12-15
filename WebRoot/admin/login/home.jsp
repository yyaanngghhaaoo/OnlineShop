<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>科技点亮未来</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath }/client/css/main.css" type="text/css" />
	<!--<script type="text/javascript">
	function exitSys() {
		var flag = window.confirm("确认退出系统吗?");
		if (flag) {
			window.top.open('', '_parent', '');
			window.top.close();
		}
	}
    </script> -->
</head>
<body class="main">
	<jsp:include page="top.jsp" />
	<div id="divpagecontent">
		<ul>
		   <li><img src="${pageContext.request.contextPath}/admin/images/registerSuccess.jpg" width="200" height="150" /></li>
           <li><a class="active" href="#">后台中心</a></li>         
           <li><a href="${pageContext.request.contextPath}/listProduct" target="mainFrame">商品管理</a></li>
           <li><a href="${pageContext.request.contextPath}/admin/products/download.jsp" target="mainFrame">销售榜单</a></li>
           <li><a href="${pageContext.request.contextPath}/findOrders" target="mainFrame">订单管理</a></li>
           <!--<li><a href="${pageContext.request.contextPath}/manager/ListNoticeServlet" target="mainFrame">公告管理</a></li>-->
           <li><a href="${pageContext.request.contextPath}/client/index.jsp">退出系统</a></li>
        </ul>
	</div>
	<div class="divcontent_2">
	     <img src="${pageContext.request.contextPath}/client/images/registerSuccess.jpg" width="840" height="300" />
	     <table><tr height="30px"></tr></table>
	     <p><b style="font-size:23px">欢迎来到OnlineShop,这里是你的用户中心，让我们一起用科技点亮未来吧！</b></p>
	</div>
</body>
</html>
