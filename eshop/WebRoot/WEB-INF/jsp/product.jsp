<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<title>欢乐购商城</title>
<link href="${pageContext.request.contextPath}/css/common.css"
	rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/css/product.css"
	rel="stylesheet" type="text/css">

</head>
<body>

	<%@ include file="head.jsp"%>
	</div>
	<div class="container productContent">
		<div class="span6">
			<div class="hotProductCategory">
				<s:iterator value="#categories" id="c">
					<dl>
						<dt>
							<a href="${ pageContext.request.contextPath }/product_findPageProductsByCategory.action?cid=<s:property value="#c.cid"/>&currentPage=1"><s:property value="#c.cname"/></a>
						</dt>
							<s:set value="%{#c.categoryseconds}" id="categoryseconds"></s:set>
							<s:iterator value="#categoryseconds" id="cs">
								<dd>
									<a href="${ pageContext.request.contextPath }/product_findPageProductsByCategorysecond.action?csid=<s:property value="#cs.csid"/>&currentPage=1"><s:property value="#cs.csname"/></a>
								</dd>
							</s:iterator>
					</dl>
				</s:iterator>
			</div>


		</div>
		<div class="span18 last">
			<s:set value="#request.product" id="p"></s:set>
			<div class="productImage">
				<a title="" style="outline-style: none; text-decoration: none;"
					id="zoom"
					href="http://image/r___________renleipic_01/bigPic1ea8f1c9-8b8e-4262-8ca9-690912434692.jpg"
					rel="gallery">
					<div class="zoomPad">
						<img style="opacity: 1;" title="" class="medium"
							src="${pageContext.request.contextPath}/<s:property value="#p.image"/>"><div
								style="display: block; top: 0px; left: 162px; width: 0px; height: 0px; position: absolute; border-width: 1px;"
								class="zoomPup"></div>
							<div
								style="position: absolute; z-index: 5001; left: 312px; top: 0px; display: block;"
								class="zoomWindow">
								<div style="width: 368px;" class="zoomWrapper">
									<div style="width: 100%; position: absolute; display: none;"
										class="zoomWrapperTitle"></div>
									<div style="width: 0%; height: 0px;" class="zoomWrapperImage">
										<img
											src="${pageContext.request.contextPath}/<s:property value="#p.image"/> "
											style="position: absolute; border: 0px none; display: block; left: -432px; top: 0px;">
									</div>
								</div>
							</div>
							<div
								style="visibility: hidden; top: 129.5px; left: 106px; position: absolute;"
								class="zoomPreload">Loading zoom</div>
					</div>
				</a>
			</div>
			<div class="name"><s:property value="#p.pname"/></div>
			<div class="sn">
				<div>编号：<s:property value="#p.pid"/></div>
			</div>
			<div class="info">
				<dl>
					<dt>商城价:
					</dt>
					<dd>
						<strong>￥：<s:property value="#p.shopPrice"/>元/份</strong> 参 考 价：
						<del>￥<s:property value="#p.marketPrice"/>元/份</del>
					</dd>
				</dl>
				<dl>
					<dt>促销:</dt>
					<dd>
						<a target="_blank" title="限时抢购 (2014-07-30 ~ 2015-01-01)">限时抢购</a>
					</dd>
				</dl>
				<dl>
					<dt></dt>
					<dd>
						<span> </span>
					</dd>
				</dl>
			</div>
			<form action="${pageContext.request.contextPath }/cart_addToCart.action" method="post">
				<div class="action">
					<dl class="quantity">
						<dt>购买数量:</dt>
						<dd>
							<input type="hidden" name="pid" value="<s:property value="#p.pid"/>"/>
							<input id="count" name="count" value="1" maxlength="4"
								onpaste="return false;" type="text">
								<div>
									<span id="increase" class="increase">&nbsp;</span> <span
										id="decrease" class="decrease">&nbsp;</span>
								</div>
						</dd>
						<dd>件</dd>
					</dl>
					<div class="buy">
						<input id="addCart" class="addCart" value="加入购物车" type="submit">
					</div>
				</div>
			</form>
			<div id="bar" class="bar">
				<ul>
					<li id="introductionTab"><a href="#introduction">商品介绍</a></li>
				</ul>
			</div>

			<div id="introduction" name="introduction" class="introduction">
				<div class="title">
					<strong>商品介绍</strong>
				</div>
				<div>
					<img
						src="${pageContext.request.contextPath}/<s:property value="#p.image"/> ">
					<p>
						<s:property  value="#p.pdesc"/>
					</p>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="foot.jsp"%>
</body>
</html>
