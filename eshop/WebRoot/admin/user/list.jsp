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
			function addUser(){
				window.location.href = "${pageContext.request.contextPath}/admin/user/add.jsp";
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
					<td class="ta_01" align="center" bgColor="#afd1f3"><strong>用户
							列 表</strong></TD>
				</tr>
				<tr>
					<td class="ta_01" align="right">
						<button type="button" id="add" name="add" value="添加"
							class="button_add" onclick="addUser()">
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
								<td align="center" width="8%">序号</td>
								<td align="center" width="8%">用户名</td>
								<td align="center" width="8%">性别</td>
								<td align="center" width="8%">真实姓名</td>
								<td align="center" width="10%">出生日期</td>
								<td align="center" width="12%">邮箱</td>
								<td align="center" width="10%">地址</td>
								<td align="center" width="8%">联系方式</td>
								<td align="center" width="8%">状态</td>
								<td width="10%" align="center">编辑</td>
								<td width="10%" align="center">删除</td>
							</tr>
							<s:iterator var="u" value="uList.list" status="status">
								<tr onmouseover="this.style.backgroundColor = 'white'"
									onmouseout="this.style.backgroundColor = '#F5FAFE';">
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="8%"><s:property value="#status.count" /></td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="8%"><s:property value="#u.username" /></td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="8%"><s:property value="#u.userdetail.gender" /></td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="8%"><s:property value="#u.userdetail.truename" /></td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="8%">
										<s:date name="#u.userdetail.birthday" format="yyyy年MM月dd日 "/>
									</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="8%"><s:property value="#u.userdetail.email" /></td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="10%">
										<s:property value="#u.userdetail.address" />
									</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="10%">
										<s:property value="#u.userdetail.phone" />
									</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="8%">
										<s:if test="#u.userdetail.state == 0">
											未激活
										</s:if>
										<s:elseif test="#u.userdetail.state == 1">
											已激活
										</s:elseif>
									</td>
									<td align="center" style="HEIGHT: 22px" width="12%"><a
										href="${pageContext.request.contextPath}/adminUser_edit.action?userid=<s:property value="#u.userid"/>">
											<img
											src="${pageContext.request.contextPath}/images/i_edit.gif"
											border="0" style="CURSOR: hand">
									</a></td>
									<td align="center" style="HEIGHT: 22px" width="12%"><a
										href="${pageContext.request.contextPath}/adminUser_delete.action?userid=<s:property value="#u.userid"/>">
											<img
											src="${pageContext.request.contextPath}/images/i_del.gif"
											width="16" height="16" border="0" style="CURSOR: hand">
									</a></td>
								</tr>
							</s:iterator>
							<tr>
								<td colspan="4" style="text-align: center;">
									<div class="pagination">
										<s:set value="uList" id="uList"></s:set>
										<s:if test="#uList != null">
											<span>第 <s:property value="%{#uList.currentPage}" />/<s:property
													value="%{#uList.totalPage}" /> 页
											</span>
											<s:if test="#uList.currentPage != 1">
												<a
													href="${ pageContext.request.contextPath }/adminUser_findAllByPage.action?currentPage=1"
													class="firstPage">&nbsp;</a>
												<a
													href="${ pageContext.request.contextPath }/adminUser_findAllByPage.action?currentPage=<s:property value="#uList.currentPage-1"/>"
													class="previousPage">&nbsp;</a>
											</s:if>
											<s:iterator var="i" begin="1" end="%{#uList.totalPage}">
												<s:if test="#uList.currentPage != #i">
													<a
														href="${ pageContext.request.contextPath }/adminUser_findAllByPage.action?currentPage=<s:property value="#i"/>"><s:property
															value="#i" /></a>
												</s:if>
												<s:else>
													<span class="currentPage"><s:property value="#i" /></span>
												</s:else>
											</s:iterator>

											<s:if test="#uList.currentPage != #uList.totalPage">
												<a class="nextPage"
													href="${ pageContext.request.contextPath }/adminUser_findAllByPage.action?currentPage=<s:property value="%{#uList.currentPage}+1"/>">&nbsp;</a>
												<a class="lastPage"
													href="${ pageContext.request.contextPath }/adminUser_findAllByPage.action?currentPage=<s:property value="%{#uList.totalPage}"/>">&nbsp;</a>
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

