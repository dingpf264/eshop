<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<table>
  <tr>
    <td width="20%">商品</td>
    <td width="25%">图片</td>
    <td width="25%">数量</td>
    <td width="20%">小计</td>
  </tr>
  <s:set value="#request.orderitems" id="items"></s:set>
  	<tr><td colspan="4" height="20px"></td></tr>
  <s:iterator value="#items" id="item">
	  <tr>
	    <td width="20%">
	    	<s:property value="#item.product.pname"/>
	    </td>
	    <td width="25%"><img src="${pageContext.request.contextPath }/<s:property value="#item.product.image"/>" width="40px" height="30px"/></td>
	    <td width="25%"><s:property value="#item.count"/></td>
	    <td width="20%"><s:property value="#item.subtotal"/></td>
	  </tr>
  </s:iterator>
</table>

