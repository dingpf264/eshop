<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<title>我的订单</title>
<link href="${pageContext.request.contextPath}/css/common.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/cart.css"
	rel="stylesheet" type="text/css" />

</head>
<body>
	<%@ include file="head.jsp"%>
	<div class="container cart">
		<div class="span24">
			<div class="step step1">
				<ul>
					<li class="current"></li>
				</ul>
			</div>
			<s:set value="orders.list" id="orders"></s:set>
			<s:iterator value="#orders" id="order">
				<table>
					<tbody>
						<tr>
							<span>
							下单时间：<s:date name="#order.ordertime" format="yyyy年MM月dd日 "/>
							&nbsp;&nbsp;
							订单号：<s:property value="#order.oid" />
							&nbsp;&nbsp;
							<a href="order_findOrderDetailByOid.action?oid=<s:property value="#order.oid"/>">订单详情</a>
							&nbsp;&nbsp;&nbsp;&nbsp;订单状态：
								<s:if test="#order.state == 1">
									<a target="_blank"
										href="${pageContext.request.contextPath }/order_payOrder.action?oid=<s:property value="#order.oid"/>"><font
										color="red">付款</font></a>
								</s:if>
								<s:elseif test="#order.state == 2">
									<font color="orange">商家待发货</font>
								</s:elseif>
								<s:elseif test="#order.state == 3">
									<a href="order_updateState.action?oid=<s:property value="#order.oid"/>"><font color="blue">确认收货</font></a>
								</s:elseif>
								<s:elseif test="#order.state == 4">
									<font color="green">交易完成</font>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<a href="order_deleteOrderByOid.action?oid=<s:property value="#order.oid"/>"><font color="red">删除</font></a>
								</s:elseif>
							</span>
						</tr>
						<br/><br/>
						<tr>
							<th>图片</th>
							<th>商品</th>
							<th>价格</th>
							<th>数量</th>
							<th>小计</th>
						</tr>
						<s:iterator value="#order.orderitems" id="item">
							<tr>
								<td width="60"><input type="hidden" name="id" value="22" />
									<img src="${pageContext.request.contextPath }/<s:property value="#item.product.image"/>" /></td>
								<td><a target="_blank" href="product_findDetail.action?pid=<s:property value='#item.product.pid'/>"><s:property value="#item.product.pname" /></a>
								</td>
								<td><s:property value="#item.product.shopPrice"/></td>
								<td class="quantity" width="60">
									<s:property value="#item.count"/>
								</td>
								<td width="140"><span class="subtotal">￥<s:property value="#item.subtotal"/></span></td>
							</tr>
						</s:iterator>
					</tbody>
				</table>
				<dl id="giftItems" class="hidden" style="display: none;">
				</dl>
				<div class="total">
					<em id="promotion"></em> 商品金额: <strong id="effectivePrice">￥<s:property value="#order.total"/>元</strong>
				</div>
			</s:iterator>
			<div class="pagination">
					<s:set value="orders" id="orders"></s:set>
					<s:if test="#orders != null">
						<span>第 <s:property value="%{#orders.currentPage}"/>/<s:property value="%{#orders.totalPage}"/> 页</span>
						<s:if test="#orders.currentPage != 1">
							<a href="${ pageContext.request.contextPath }/order_findOrders.action?currentPage=1" class="firstPage">&nbsp;</a>
							<a href="${ pageContext.request.contextPath }/order_findOrders.action?currentPage=<s:property value="#orders.currentPage-1"/>" class="previousPage">&nbsp;</a>
						</s:if>
						<s:iterator var="i" begin="1" end="%{#orders.totalPage}">
							<s:if test="#orders.currentPage != #i">
								<a href="${ pageContext.request.contextPath }/order_findOrders.action?currentPage=<s:property value="#i"/>"><s:property value="#i"/></a>
							</s:if>
							<s:else>
								<span class="currentPage"><s:property value="#i"/></span>
							</s:else>
						</s:iterator>
						
						<s:if test="#orders.currentPage != #orders.totalPage">	
							<a class="nextPage" href="${ pageContext.request.contextPath }/order_findOrders.action?currentPage=<s:property value="%{#orders.currentPage}+1"/>">&nbsp;</a>
							<a class="lastPage" href="${ pageContext.request.contextPath }/order_findOrders.action?currentPage=<s:property value="%{#orders.totalPage}"/>">&nbsp;</a>
						</s:if>
					</s:if>	
				</div>
		</div>
	</div>
	<%@ include file="foot.jsp"%>
</body>
</html>
