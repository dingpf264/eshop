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
		
		function doDelete(){
			var checkProducts = document.getElementsByName("product");
			var m = 0;
			var n = false;
			var p = new Array();
			for(var i = 0; i < checkProducts.length; i++){
				if(checkProducts[i].checked){
					n =true;
					p[m++] = checkProducts[i].value;
				}
			}
			if(!n){
				alert("亲，请至少选择一个商品O(∩_∩)O~");
			}
			location.href="${pageContext.request.contextPath}/adminProduct_batchDelete.action?p=" + p;
		}
		
		function addProduct(){
			window.location.href = "${pageContext.request.contextPath}/adminProduct_addPage.action";
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
					<td class="ta_01" align="center" bgColor="#afd1f3"><strong>商品
							列 表</strong></TD>
				</tr>
				<tr>
					<td class="ta_01" align="left">
					&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button" id="delete" name="delete" value="删除"
						 onclick="doDelete()">
							批量删除</button>
							&nbsp;&nbsp;
						<button type="button" id="add" name="add" value="添加"
							class="button_add" onclick="addProduct()">
							&#28155;&#21152;</button>
					</td>
				</tr>
				<tr>
					<td class="ta_01" align="center" bgColor="#f5fafe">
						<table cellspacing="0" cellpadding="1" rules="all"
							bordercolor="gray" border="1" id="DataGrid1"
							style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
							<tr
								style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">
								<td style="text-align:center;"><label><input class="check-all check"
									type="checkbox" />&nbsp;全选</label></td>
								<td align="center" width="8%">序号</td>
								<td align="center" width="12%">商品名称</td>
								<td align="center" width="12%">市场价</td>
								<td align="center" width="12%">商城价</td>
								<td align="center" width="12%">图片</td>
								<td align="center" width="8%">是否热门</td>
								<td width="8%" align="center">编辑</td>
								<td width="8%" align="center">删除</td>
							</tr>
							<s:iterator var="p" value="pList.list" status="status">
								<tr onmouseover="this.style.backgroundColor = 'white'"
									onmouseout="this.style.backgroundColor = '#F5FAFE';">
									<td class="checkbox" style="text-align:center;"><input class="check-one check"
									type="checkbox" name="product" value="<s:property value='#p.pid'/>"/></td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="8%"><s:property value="#status.count" /></td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="12%"><s:property value="#p.pname" /></td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="12%"><s:property value="#p.marketPrice" /></td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="12%"><s:property value="#p.shopPrice" /></td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="12%">
										<a target="_blank" href="product_findDetail.action?pid=<s:property value='#p.pid'/>" title="点击预览商品简介">
										<img 
											src="${pageContext.request.contextPath}/<s:property value="#p.image" />"
											border="0" style="CURSOR: hand" width="60px" height="50px">	
										</a>
									</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="12%">
										<s:if test="#p.isHot == 1">
											是
										</s:if>
										<s:else>否</s:else>
									</td>
									<td align="center" style="HEIGHT: 22px"><a
										href="${pageContext.request.contextPath}/adminProduct_edit.action?pid=<s:property value="#p.pid"/>">
											<img
											src="${pageContext.request.contextPath}/images/i_edit.gif"
											border="0" style="CURSOR: hand">
									</a></td>

									<td align="center" style="HEIGHT: 22px"><a
										href="${pageContext.request.contextPath}/adminProduct_delete.action?pid=<s:property value="#p.pid"/>">
											<img
											src="${pageContext.request.contextPath}/images/i_del.gif"
											width="16" height="16" border="0" style="CURSOR: hand">
									</a></td>
								</tr>
							</s:iterator>
							<tr>
								<td colspan="4" style="text-align: center;">
									<div class="pagination">
										<s:set value="pList" id="pList"></s:set>
										<s:if test="#pList != null">
											<span>第 <s:property value="%{#pList.currentPage}" />/<s:property
													value="%{#pList.totalPage}" /> 页
											</span>
											<s:if test="#pList.currentPage != 1">
												<a
													href="${ pageContext.request.contextPath }/adminProduct_findAllByPage.action?currentPage=1"
													class="firstPage">&nbsp;</a>
												<a
													href="${ pageContext.request.contextPath }/adminProduct_findAllByPage.action?currentPage=<s:property value="#pList.currentPage-1"/>"
													class="previousPage">&nbsp;</a>
											</s:if>
											<s:iterator var="i" begin="1" end="%{#pList.totalPage}">
												<s:if test="#pList.currentPage != #i">
													<a
														href="${ pageContext.request.contextPath }/adminProduct_findAllByPage.action?currentPage=<s:property value="#i"/>"><s:property
															value="#i" /></a>
												</s:if>
												<s:else>
													<span class="currentPage"><s:property value="#i" /></span>
												</s:else>
											</s:iterator>

											<s:if test="#pList.currentPage != #pList.totalPage">
												<a class="nextPage"
													href="${ pageContext.request.contextPath }/adminProduct_findAllByPage.action?currentPage=<s:property value="%{#pList.currentPage}+1"/>">&nbsp;</a>
												<a class="lastPage"
													href="${ pageContext.request.contextPath }/adminProduct_findAllByPage.action?currentPage=<s:property value="%{#pList.totalPage}"/>">&nbsp;</a>
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

