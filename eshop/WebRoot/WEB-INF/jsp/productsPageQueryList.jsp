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
<title>欢乐购搜索_<s:property value="pname"/></title>
<link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/product.css" rel="stylesheet" type="text/css" />

</head>
<body>
	<%@ include file="head.jsp" %>
	<span style="margin-left:400px;">总共为您找到<s:property value="pList.totalCount"/>件相关宝贝</span>
	<div class="container productList" style="margin-top:15px;">
		<div class="span18 last" style="width:950px;">
			<form id="productForm"
				action="" method="get" style="width:950px;">
				<div id="result" class="result table clearfix" style="width:950px;">
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
				<div class="pagination" style="margin-right:400px;">
					<s:set value="pList" id="pageProducts"></s:set>
						<s:if test="#pageProducts != null">
							<span>第 <s:property value="%{#pageProducts.currentPage}"/>/<s:property value="%{#pageProducts.totalPage}"/> 页</span>
							<s:if test="#pageProducts.currentPage != 1">
								<a href="${ pageContext.request.contextPath }/product_findPageProductsByName.action?pname=<s:property value="pname"/>&currentPage=1" class="firstPage">&nbsp;</a>
								<a href="${ pageContext.request.contextPath }/product_findPageProductsByName.action?pname=<s:property value="pname"/>&currentPage=<s:property value="#pageProducts.currentPage-1"/>" class="previousPage">&nbsp;</a>
							</s:if>
							<s:iterator var="i" begin="1" end="#pageProducts.totalPage">
								<s:if test="#pageProducts.currentPage != #i">
									<a href="${ pageContext.request.contextPath }/product_findPageProductsByName.action?pname=<s:property value="pname"/>&currentPage=<s:property value="#i"/>"><s:property value="#i"/></a>
								</s:if>
								<s:else>
									<span class="currentPage"><s:property value="#i"/></span>
								</s:else>
							</s:iterator>
							
							<s:if test="#pageProducts.currentPage != #pageProducts.totalPage">	
								<a class="nextPage" href="${ pageContext.request.contextPath }/product_findPageProductsByName.action?pname=<s:property value="pname"/>&currentPage=<s:property value="%{#pageProducts.currentPage}+1"/>">&nbsp;</a>
								<a class="lastPage" href="${ pageContext.request.contextPath }/product_findPageProductsByName.action?pname=<s:property value="pname"/>&currentPage=<s:property value="%{#pageProducts.totalPage}"/>">&nbsp;</a>
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
	<%@ include file="foot.jsp" %>
</body>
</html>
