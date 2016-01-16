<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<HTML>
<HEAD>
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
<script type="text/javascript">
	function showOrderDetail(oid){
		var but = $("but" + oid);
		var div1 = $("div"+oid);
		if(but.value == "订单详情"){
			var xmlhttp = getXmlHttpRequest();
			xmlhttp.onreadystatechange = function() {
				if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
					div1.innerHTML = xmlhttp.responseText;
				}
			};
			//发送请求
			xmlhttp.open("GET", "adminOrder_showDetail.action?oid="
					+ oid + "&time=" + new Date().getTime(), true);
			xmlhttp.send();
			but.value = "关闭";
		}else{
			div1.innerHTML = "";
			but.value = "订单详情";
		}
		
	}
	function getXmlHttpRequest() {
		var xmlhttp;
		if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
			xmlhttp = new XMLHttpRequest();
		} else {// code for IE6, IE5
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		return xmlhttp;
	}
	function $(id){
		return document.getElementById(id);
	}
</script>
</HEAD>
<body>
	<br>
	<form id="Form1" name="Form1"
		action="${pageContext.request.contextPath}/user/list.jsp"
		method="post">
		<table cellSpacing="1" cellPadding="0" width="100%" align="center"
			bgColor="#f5fafe" border="0">
			<TBODY>
				<tr>
					<td class="ta_01" align="center" bgColor="#afd1f3"><strong>订单
							列 表</strong></TD>
				</tr>
				<tr>
					<td class="ta_01" align="center" bgColor="#f5fafe">
						<table cellspacing="0" cellpadding="1" rules="all"
							bordercolor="gray" border="1" id="DataGrid1"
							style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
							<tr
								style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">
								<td align="center" width="8%">序号</td>
								<td align="center" width="12%">订单编号</td>
								<td align="center" width="12%">下单时间</td>
								<td align="center" width="12%">订单状态</td>
								<td align="center" width="12%">收件人</td>
								<td align="center" width="14%">联系电话</td>
								<td align="center" width="8%">地址</td>
								<td align="center" width="8%">订单详情</td>
							</tr>
							<s:iterator var="o" value="oList.list" status="status">
								<tr onmouseover="this.style.backgroundColor = 'white'"
									onmouseout="this.style.backgroundColor = '#F5FAFE';">
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="8%"><s:property value="#status.count" /></td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="12%"><s:property value="#o.oid" /></td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="12%"><s:date name="#o.ordertime" format="yyyy年MM月dd日 HH:mm:ss"/></td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="12%">
										<s:if test="#o.state == 1">
											未付款
										</s:if>
										<s:elseif test="#o.state == 2">
											<a href="adminOrder_updateState.action?oid=<s:property value="#o.oid"/>" style="text-decoration: none;"><font color="red">发货</font></a>
										</s:elseif>
										<s:elseif test="#o.state == 3">
											确认收货
										</s:elseif>
										<s:elseif test="#o.state == 4">
											交易完成
										</s:elseif>
									</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="12%"><s:property value="#o.name" /></td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="12%"><s:property value="#o.phone" /></td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="12%"><s:property value="#o.address" /></td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="12%">
										<input type="button" id="but<s:property value="#o.oid"/>" value="订单详情" onclick="showOrderDetail(<s:property value="#o.oid"/>)">
										<div id="div<s:property value="#o.oid"/>"></div>
									</td>
								</tr>
							</s:iterator>
							<tr>
								<td colspan="4" style="text-align: center;">
									<div class="pagination">
										<s:set value="oList" id="oList"></s:set>
										<s:if test="#oList != null">
											<span>第 <s:property value="%{#oList.currentPage}" />/<s:property
													value="%{#oList.totalPage}" /> 页
											</span>
											<s:if test="#oList.currentPage != 1">
												<a
													href="${ pageContext.request.contextPath }/adminOrder_findAllByPage.action?currentPage=1"
													class="firstPage">&nbsp;</a>
												<a
													href="${ pageContext.request.contextPath }/adminOrder_findAllByPage.action?currentPage=<s:property value="#oList.currentPage-1"/>"
													class="previousPage">&nbsp;</a>
											</s:if>
											<s:iterator var="i" begin="1" end="%{#oList.totalPage}">
												<s:if test="#oList.currentPage != #i">
													<a
														href="${ pageContext.request.contextPath }/adminOrder_findAllByPage.action?currentPage=<s:property value="#i"/>"><s:property
															value="#i" /></a>
												</s:if>
												<s:else>
													<span class="currentPage"><s:property value="#i" /></span>
												</s:else>
											</s:iterator>

											<s:if test="#oList.currentPage != #oList.totalPage">
												<a class="nextPage"
													href="${ pageContext.request.contextPath }/adminOrder_findAllByPage.action?currentPage=<s:property value="%{#oList.currentPage}+1"/>">&nbsp;</a>
												<a class="lastPage"
													href="${ pageContext.request.contextPath }/adminOrder_findAllByPage.action?currentPage=<s:property value="%{#oList.totalPage}"/>">&nbsp;</a>
											</s:if>
										</s:if>
									</div>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</TBODY>
		</table>
	</form>
</body>
</HTML>

