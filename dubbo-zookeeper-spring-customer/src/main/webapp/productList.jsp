<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Products List</title>
<style type="text/css">
span{
   color:red;
}
td{
width: 280px;
}
</style>
</head>
<body>
    <hr />
	<span>登录成功</span>
	<hr />
	商品列表：
	<table style="border-bottom-color: green;" border="1">
		<tr>
		   <th>ID</th>
		   <th>NAME</th>
		   <th>ADDRESS</th>
		</tr>
		<c:forEach items="${products}" var="product">
			<tr>
				<td>${product.id}</td>
				<td>${product.name}</td>
				<td>${product.address}</td>
			</tr>		
		</c:forEach>
		
	</table>
</body>
</html>