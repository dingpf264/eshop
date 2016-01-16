<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Language" content="zh-cn">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/css/Style1.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/common.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/cart.css"
	rel="stylesheet" type="text/css" />
<script language="javascript"
	src="${pageContext.request.contextPath}/js/public.js"></script>
<title>My JSP 'query.jsp' starting page</title>
<link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/product.css" rel="stylesheet" type="text/css" />
</head>

<body>
<div style="margin:20px 200px">
	<form action="adminProduct_findPageProductsByName" method="post">
	<input
		style="line-height:30px; font-size:14px;border-color: red;border-style: solid;border-width: 1px;"
		type="text" name="pname" size="50" height="40px"
		placeholder="请输入您要查询的商品名称" />&nbsp;&nbsp;&nbsp;
		<s:hidden name="currentPage" value="1"></s:hidden>
		<input type="submit" value="搜索" style="line-height:30px;font-size:18px;height:30px"/>
</form>
</div>
	<span style="margin-left:400px;">总共为您找到<s:property value="pList.totalCount"/>条记录</span>
	<div class="container productList" style="margin-top:15px;">
		<div class="span18 last">
			<form id="productForm"
				action="" method="get">
				<div id="result" class="result table clearfix">
					<ul>
						<s:set value="pList.list" id="ps"></s:set>
						<s:if test="#ps != null">
							<s:iterator value="#ps" id="p">
								<li>
									<a href="product_findDetail.action?pid=<s:property value='#p.pid'/>" target="_blank"> <img
										src="${pageContext.request.contextPath}/<s:property value="%{#p.image}"/>"
										width="160" height="170"> <span
										style='color:green'><s:property value="%{#p.pname}"/></span> <span class="price">
											商城价： ￥<s:property value="%{#p.shopPrice}"/>/份 </span>
									</a>
								</li>
							</s:iterator>
						</s:if>
					</ul>
				</div>
				<div class="pagination" style="margin-right:200px;">
					<s:set value="pList" id="pageProducts"></s:set>
						<s:if test="#pageProducts != null">
							<span>第 <s:property value="#pageProducts.currentPage"/>/<s:property value="%{#pageProducts.totalPage}"/> 页</span>
							<s:if test="#pageProducts.currentPage != 1">
								<a href="${ pageContext.request.contextPath }/adminProduct_findPageProductsByName.action?pname=<s:property value="pname"/>&currentPage=1" class="firstPage">&nbsp;</a>
								<a href="${ pageContext.request.contextPath }/adminProduct_findPageProductsByName.action?pname=<s:property value="pname"/>&currentPage=<s:property value="#pageProducts.currentPage-1"/>" class="previousPage">&nbsp;</a>
							</s:if>
							<s:iterator var="i" begin="1" end="#pageProducts.totalPage">
								<s:if test="#pageProducts.currentPage != #i">
									<a href="${ pageContext.request.contextPath }/adminProduct_findPageProductsByName.action?pname=<s:property value="pname"/>&currentPage=<s:property value="#i"/>"><s:property value="#i"/></a>
								</s:if>
								<s:else>
									<span class="currentPage"><s:property value="#i"/></span>
								</s:else>
							</s:iterator>
							
							<s:if test="#pageProducts.currentPage != #pageProducts.totalPage">	
								<a class="nextPage" href="${ pageContext.request.contextPath }/adminProduct_findPageProductsByName.action?pname=<s:property value="pname"/>&currentPage=<s:property value="%{#pageProducts.currentPage}+1"/>">&nbsp;</a>
								<a class="lastPage" href="${ pageContext.request.contextPath }/adminProduct_findPageProductsByName.action?pname=<s:property value="pname"/>&currentPage=<s:property value="%{#pageProducts.totalPage}"/>">&nbsp;</a>
							</s:if>
						</s:if>
						<s:else>
							<center>
								<h3>未查询到相关商品！</h3>
							</center>
						</s:else>
				</div>
			</form>
		</div>
	</div>
</body>
</html>
