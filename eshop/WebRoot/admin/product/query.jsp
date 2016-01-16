<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
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
		<input type="hidden" name="currentPage" value="1"/>
		<input type="submit" value="搜索" style="line-height:30px;font-size:18px;height:30px"/>
</form>
</div>
</body>
</html>
