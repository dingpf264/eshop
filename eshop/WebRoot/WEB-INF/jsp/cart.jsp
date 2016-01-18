<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0043)http://localhost:8080/mango/cart/list.jhtml -->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>购物车</title>

<link href="${pageContext.request.contextPath}/css/common.css"
	rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/css/cart.css"
	rel="stylesheet" type="text/css">
	</style>
	<script type="text/javascript">
		window.onload = function () {
			var checkInputs = document.getElementsByClassName('check');
		    var checkAllInputs = document.getElementsByClassName('check-all');
			for (var i = 0 , len = checkInputs.length; i < len; i++) {
		        checkInputs[i].onclick = function () {
		            if (this.className === 'check-all check') {
		                for (var j = 0; j < checkInputs.length; j++) {
		                    checkInputs[j].checked = this.checked;
		                }
		            }
		            if (this.checked == false) {
		                for (var k = 0; k < checkAllInputs.length; k++) {
		                    checkAllInputs[k].checked = false;
		                }
		            }
		        };
		    }
		};
		
		function getXmlHttpRequest() {
			var xmlhttp;
			if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
				xmlhttp = new XMLHttpRequest();
			} else {// code for IE6, IE5
				xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
			}
			return xmlhttp;
		}
		function doSubmit(){
			var checkProducts = document.getElementsByName("product");
			var m = 0;
			var n = false;
			var p = new Array();
			for(var i = 0; i < checkProducts.length; i++){
				if(checkProducts[i].checked){
					n =true;
				}else{
					p[m++] = checkProducts[i].value;
				}
			}
			if(!n){
				alert("亲，请至少选择一个商品O(∩_∩)O~");
			}
			location.href="${pageContext.request.contextPath}/order_save.action?p=" + p;
		}
	</script>
</head>
<body>
	<%@ include file="head.jsp"%>
	<div class="container cart" style="margin:30px 165px;">
		<s:if test="#session.cart.cartItems.size() != 0">
			<div class="span24">
				<table id="cartTable">
					<thead>
						<tr>
							<th><label><input class="check-all check"
									type="checkbox" />&nbsp;全选</label></th>
							<th>商品</th>
							<th>单价</th>
							<th>数量</th>
							<th>小计</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<s:set value="#session.cart" id="cart"></s:set>
						<s:iterator value="#cart.cartItems" id="item">
							<tr>
								<td class="checkbox"><input class="check-one check"
									type="checkbox" name="product" value="<s:property value='#item.product.pid'/>"/></td>
								<td class="goods"><img
									src="${pageContext.request.contextPath}/<s:property value="#item.product.image"/>"
									alt="" /> <span><a target="_blank"
										href="product_findDetail.action?pid=<s:property value='#item.product.pid'/>"><s:property
												value="#item.product.pname" /></a></span></td>
								<td class="price">￥<s:property
										value="#item.product.shopPrice" /></td>
								<td class="count">
								<%-- <span class="reduce"></span> <input
									class="count-input" type="text"
									value="<s:property value="#item.count"/>" /> <span class="add">+</span> --%>
									<s:property value="#item.count"/>
								</td>
								<td class="subtotal">￥<s:property value="#item.subTotal" /></td>
								<td class="operation"><span class="delete"> <a
										href="${pageContext.request.contextPath}/cart_remove.action?pid=<s:property value="#item.product.pid"/>"
										class="delete">删除</a>
								</span></td>
							</tr>
						</s:iterator>
					</tbody>
				</table>
				<dl id="giftItems" class="hidden" style="display: none;">
				</dl>
				<div class="total">
					<em id="promotion">
					商品金额:
					<strong id="effectivePrice">￥<s:property
							value="#cart.total" />元
					</strong>
					</em>
				</div>
				<div class="bottom">
					<a href="${pageContext.request.contextPath}/cart_clearCart.action"
						id="clear" class="clear">清空购物车</a> <a
						href="javascript:void(0); "
						id="submit" class="submit" onclick="doSubmit()">提交订单</a>
				</div>
			</div>
		</s:if>
		<s:else>
			<div class="span24" style="margin:60px 245px">
				<h3>亲，您的购物车暂时没有商品，快去商城购买吧\(^o^)/~</h3>
			</div>
		</s:else>
	</div>
	<%@ include file="foot.jsp"%>
</body>
</html>