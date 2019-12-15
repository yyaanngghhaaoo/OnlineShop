<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>电子书城</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/client/css/main.css" type="text/css" />
	<script type="text/javascript">
	//删除订单
	function o_del() {   
	    var msg = "您确定要删除该订单吗？";   
	    if (confirm(msg)==true){   
	    return true;   
	    }else{   
	    return false;   
	    }   
	} 
	</script>
</head>
<body class="main">
	<!-- 使用了自定义标签 -->
	<jsp:include page="after_login_menu_search.jsp" />
	<div id="divpagecontent">
		<ul>
		   <li><img src="client/images/registerSuccess.jpg" width="200" height="150" /></li>
           <li><a class="active" href="#">个人中心</a></li>
           
           <!-- <li><a href="${pageContext.request.contextPath }/client/modifyuserinfo.jsp">信息修改</a></li>-->
           <li><a href="${pageContext.request.contextPath}/findOrderByUser">订单查询</a></li>
           <li><a href="${pageContext.request.contextPath}/logout" onclick="javascript:return confirm_logout()">退出登录</a></li>
       </ul>
	</div>
	<div class="divcontent_2">
		<table width="100%" border="0" cellspacing="0">
			<tr>			
				<td>					
					<table cellspacing="0" class="infocontent">
						<tr>
							<td style="padding:20px"><p><b>我的订单</b></p>
								<p>
									共有<font style="color:#FF0000" >${orders.size()}</font>订单
								</p>
								<table width="100%" border="0" cellspacing="0" class="tableopen">
									<tr>
										<td bgcolor="#A3E6DF" class="tableopentd01">订单号</td>
										<td bgcolor="#A3D7E6" class="tableopentd01">收件人</td>
										<td bgcolor="#A3CCE6" class="tableopentd01">订单时间</td>
										<td bgcolor="#A3B6E6" class="tableopentd01">状态</td>
										<td bgcolor="#A3E2E6" class="tableopentd01">操作</td>
									</tr>
									<c:forEach items="${orders}" var="order">
										<tr>
											<td class="tableopentd02">${order.id}</td>
											<td class="tableopentd02">${order.receiverName }</td>
											<td class="tableopentd02">${order.ordertime}</td>
											<td class="tableopentd02">${order.paystate==0?"未支付":"已支付"}</td>
											<td class="tableopentd03">
												<a href="${pageContext.request.contextPath}/findOrderById?id=${order.id}">查看</a>&nbsp;&nbsp;
												<c:if test="${order.paystate==0 }">
													<a href="${pageContext.request.contextPath}/delOrderById?id=${order.id}"  onclick="javascript:return o_del()">刪除</a>
												</c:if> 
												<c:if test="${order.paystate!=0 }">
													<a href="${pageContext.request.contextPath}/delOrderById?id=${order.id}&type=client" onclick="javascript:return o_del()">刪除</a>
												</c:if>
											</td>
										</tr>
									</c:forEach>
								</table>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>
