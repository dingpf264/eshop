<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0048)http://localhost:8080/mango/product/list/1.jhtml -->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>欢乐购商城</title>
<link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/product.css" rel="stylesheet" type="text/css" />

</head>
<body>
	<%@ include file="head.jsp" %>
	<div class="container productList">
		<div class="span6">
			<div class="hotProductCategory">
				<s:set value="#session.categories" id="categories"></s:set>
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
			<form id="productForm"
				action="" method="get">
				<input type="hidden" id="brandId" name="brandId" value=""> <input
					type="hidden" id="promotionId" name="promotionId" value="">
				<input type="hidden" id="orderType" name="orderType" value="">
				<input type="hidden" id="pageNumber" name="pageNumber" value="1">
				<input type="hidden" id="pageSize" name="pageSize" value="20">

				<div id="result" class="result table clearfix">
					<ul>
						<s:set value="#request.pageProducts.list" id="ps"></s:set>
						<s:if test="#ps != null">
							<s:iterator value="#ps" id="p">
								<li>
									<a href="product_findDetail.action?pid=<s:property value='#p.pid'/>" target="_blank"> <img
										src="${pageContext.request.contextPath}/<s:property value="%{#p.image}"/>"
										width="170" height="170" style="display: inline-block;"> <span
										style='color:green'><s:property value="%{#p.pname}"/></span> <span class="price">
											商城价： ￥<s:property value="%{#p.shopPrice}"/>/份 </span>
									</a>
								</li>
							</s:iterator>
						</s:if>
						<s:else>
							<li>
								<h3 align="center">该分类的商品正在上架中.....请耐心等待\(^o^)/~</h3>
							</li>
						</s:else>
					</ul>
				</div>
				<div class="pagination">
					<s:set value="#request.pageProducts" id="pageProducts"></s:set>
					<s:if test="cid != null">
						<span>第 <s:property value="%{#pageProducts.currentPage}"/>/<s:property value="%{#pageProducts.totalPage}"/> 页</span>
						<s:if test="#pageProducts.currentPage != 1">
							<a href="${ pageContext.request.contextPath }/product_findPageProductsByCategory.action?cid=<s:property value="cid"/>&currentPage=1" class="firstPage">&nbsp;</a>
							<a href="${ pageContext.request.contextPath }/product_findPageProductsByCategory.action?cid=<s:property value="cid"/>&currentPage=<s:property value="#pageProducts.currentPage-1"/>" class="previousPage">&nbsp;</a>
						</s:if>
						
						<s:iterator var="i" begin="1" end="%{#pageProducts.totalPage}">
							<s:if test="#pageProducts.currentPage != #i">
								<a href="${ pageContext.request.contextPath }/product_findPageProductsByCategory.action?cid=<s:property value="cid"/>&currentPage=<s:property value="#i"/>"><s:property value="#i"/></a>
							</s:if>
							<s:else>
								<span class="currentPage"><s:property value="#i"/></span>
							</s:else>
						</s:iterator>
						
						<s:if test="#pageProducts.currentPage != #pageProducts.totalPage">	
							<a class="nextPage" href="${ pageContext.request.contextPath }/product_findPageProductsByCategory.action?cid=<s:property value="cid"/>&currentPage=<s:property value="%{#pageProducts.currentPage}+1"/>">&nbsp;</a>
							<a class="lastPage" href="${ pageContext.request.contextPath }/product_findPageProductsByCategory.action?cid=<s:property value="cid"/>&currentPage=<s:property value="%{#pageProducts.totalPage}"/>">&nbsp;</a>
						</s:if>
					</s:if>	
				</div>
			</form>
		</div>
	</div>
	<%@ include file="foot.jsp" %>
</body>
</html>
