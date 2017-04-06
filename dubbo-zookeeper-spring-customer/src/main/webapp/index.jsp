<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Dubbo-ZooKeeper-Spring-Customer</title>
<style type="text/css">
span{
   color:red;
}
table{
background-color: #eeefff;
}
td{
 width: 200px;
}
</style>
</head>
<body>

<hr/>
<span>Welcome to Dubbo-ZooKeeper-Spring-Customer</span>
<hr/>
	<span >${error}</span>
	<form action="<%=path%>/login.html" method="post">
	   <table width="600px" height="200px">
		<tr><td>用户名：</td><td><input type="text" name="username" /></td></tr>
		<tr><td>密码：</td><td><input type="password" name="password" /></td></tr>
		<tr><td colspan="2" align="center"><input value="登录查询商品" type="submit"  /></td><td></td></tr>
		</table>
	</form>
</body>
</html>