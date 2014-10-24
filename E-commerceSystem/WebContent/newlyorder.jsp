<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="../css/style_new.css"
	media="screen" />

<title>Newly created orders</title>
<sx:head />
</head>
<body>

	<h3>newly created orders</h3>
	<div>

		<%
			String username = request.getUserPrincipal().getName();
		%>
		Current user:
		<%=username%>
		<%
			request.setAttribute("username", username);
		%>



		<s:url var="showcustomer" action="loadorder" namespace="/search">
			<s:param name="username">
				<s:property value="%{#request.username}" />
			</s:param>
		</s:url>
		<sx:a afterNotifyTopics="loadorder" href="%{showcustomer}" />
		<s:url var="loadorderurl" action="showcustomer" namespace="/search" />
		<sx:div href="%{loadorderurl}" listenTopics="loadorder">
		</sx:div>

	</div>


</body>
</html>