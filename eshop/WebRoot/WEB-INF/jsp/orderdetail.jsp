<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<title>交易详情</title>
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
			<s:iterator value="#request.order" id="order">
				<table>
					<tbody>
						<tr>
							<td colspan="6">
								<font style="font-weight: bold;">收货地址：</font>&nbsp;
								<s:property value="#order.address"/>
							</td>
						</tr>
						<br/><hr/>
						<tr>
							<td colspan="6">
								<font style="font-weight: bold;">订单信息：</font><br/>
								&nbsp;&nbsp;&nbsp;&nbsp;
								订单编号：<s:property value="#order.oid"/>&nbsp;&nbsp;<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;
								创建时间：<s:date name="#order.ordertime" format="yyyy年MM月dd日 HH:mm:ss"/><br/>
								&nbsp;&nbsp;&nbsp;&nbsp;
								<s:if test="#order.state > 1">
									付款时间：<s:date name="#order.paytime" format="yyyy年MM月dd日 HH:mm:ss"/>
									&nbsp;&nbsp;<br/>
									<s:if test="#order.state > 2">
										&nbsp;&nbsp;&nbsp;&nbsp;
										发货时间：<s:date name="#order.deliverytime" format="yyyy年MM月dd日 HH:mm:ss"/>
									</s:if>
								</s:if>
							</td>
						</tr>
						<br/><hr/>
						<tr>
							<th>图片</th>
							<th>宝贝</th>
							<th>状态</th>
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
								<td>
									<s:if test="#order.state == 1">
										未付款
									</s:if>
									<s:elseif test="#order.state == 2">
										等待商家发货
									</s:elseif>
									<s:elseif test="#order.state == 3">
										未确认收货
									</s:elseif>
									<s:elseif test="#order.state == 4">
										交易完成
									</s:elseif>
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
		</div>
	</div>
	<%@ include file="foot.jsp"%>
  </body>
</html>
