<%@ page language="java" contentType="text/html; charset=GB18030"
	pageEncoding="GB18030"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<meta http-equiv="refresh"
	content="2;url=http://localhost:8085/E-commerceSystem/index.jsp">

<title>logout</title>
</head>
<body>
	<h2>Logout successful, returning to login page........</h2>
	<%
		session.invalidate();
	%>
</body>
</html>