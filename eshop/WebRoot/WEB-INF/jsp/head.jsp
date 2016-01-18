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
	<style type="text/css">
	.tableBorder2{width:97%;border: 1px #DEDEDE solid; background-color: #EFEFEF;}
</style>
<script type="text/javascript">
	function search() {
		var name = document.getElementById("name").value;
		location.href = "product_findPageProductsByName?pname=" + name + "&currentPage=1";
	}
</script>
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
		<div>
			<img src="${pageContext.request.contextPath }/image/year.png" alt="" style="width: 1200px; height: 110px; position: relative; left: -90px;">
		</div>
		<div style="width:618px;height:50px;margin:20px 30px 20px 160px;">
			<table cellspacing=1 cellpadding=3 align=center class=tableBorder2 style="background-color: white;border: none;">
				<tr>
					<td height=30 valign=middle align=left >
				      <input style="line-height:30px; font-size:14px;border-color: red;border-style: solid;border-width: 1px;" type="text" id="name" size="70" height="30px" placeholder="请输入您要查询的商品名称"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				    </td>
				    <td height=30 valign=middle align=left style="text-align: left;">
				      <input type="button" value="搜索" style="line-height:30px;font-size:18px;height:30px" onclick="search()"/>
				    </td>
				</tr>
			</table>
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
