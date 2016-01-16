<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>My JSP 'head.jsp' starting page</title>
<link href="${pageContext.request.contextPath}/css/slider.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/common.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/index.css"
	rel="stylesheet" type="text/css" />
</head>

<body>
	<div class="container header">
		<div class="span5">
			<div class="logo">
				<a href="./网上商城/index.htm"> <img
					src="${pageContext.request.contextPath}/image/r___________renleipic_01/logo.gif"
					alt="eshop" />
				</a>
			</div>
		</div>
		<div class="span9">
			<img src="${pageContext.request.contextPath}/image/header.jpg"
					width="320" height="50" alt="正品保障" title="正品保障" />
		</div>
		<div class="span10 last">
			<div class="topNav clearfix">
				<s:if test="#session.user != null">
					<ul>
						<li id="headerLogin" class="headerLogin"
							style="display: list-item;"><s:property value="#session.user.username"/>|</li>
						<li id="headerRegister" class="headerRegister"
							style="display: list-item;"><a href="user_logOut.action">注销</a>|</li>
						<li id="headerRegister" class="headerRegister"
							style="display: list-item;"><a href="${pageContext.request.contextPath }/order_findOrders.action?userid=<s:property value="#session.user.userid"/>&currentPage=1" target="_blank">我的订单</a>|</li>
						<li><a>会员中心</a> |</li>
						<li><a>购物指南</a> |</li>
						<li><a>关于我们</a></li>
					</ul>
				</s:if>
				<s:else>
					<ul>
						<li id="headerLogin" class="headerLogin"
							style="display: list-item;"><a href="user_loginPage.action">登录</a>|</li>
						<li id="headerRegister" class="headerRegister"
							style="display: list-item;"><a href="user_registerPage.action">注册</a>|</li>
						<li id="headerUsername" class="headerUsername"></li>
						<li id="headerLogout" class="headerLogout"><a>[退出]</a>|</li>
						<li><a>会员中心</a> |</li>
						<li><a>购物指南</a> |</li>
						<li><a>关于我们</a></li>
					</ul>
				</s:else>
			</div>
			<div class="cart">
				<a href="${pageContext.request.contextPath}/cart_showCart.action">我的购物车</a>
			</div>
			<div class="phone">
				客服热线: <strong>96008/53277764</strong>
			</div>
		</div>
		<div class="span24">
			<ul class="mainNav">
				<li><a href="${pageContext.request.contextPath}/index.action">首页</a> |</li>
				<s:set value="#session.categories" id="categories"></s:set>
				<s:iterator value="#categories" id="c">
					<li>
						<a href="product_findPageProductsByCategory?cid=<s:property value="#c.cid"/>&currentPage=1" target="_blank"><s:property value="#c.cname"/></a> |
					</li>
				</s:iterator>
			</ul>
		</div>
	</div>
</body>
</html>
