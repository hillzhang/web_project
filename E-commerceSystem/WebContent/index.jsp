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

<title>E-commerce system</title>
<sx:head />
</head>
<body>

	<h3>E-commerce system</h3>
	<div>

		<%
			String username = request.getUserPrincipal().getName();
		%>
		Current user:
		<%=username%>
		<%
			request.setAttribute("username", username);
		%>
		<p>
			<a href="search/logout"> logout</a>
		</p>

		<%
			if (request.isUserInRole("Member")) {
		%>


		<s:url var="showcustomer" action="loadorder" namespace="/search">
			<s:param name="username">
				<s:property value="%{#request.username}" />
			</s:param>
		</s:url>
		<sx:a afterNotifyTopics="loadorder" href="%{showcustomer}">show order</sx:a>


		<s:url var="loadorderurl" action="showcustomer" namespace="/search" />
		<sx:div href="%{loadorderurl}" listenTopics="loadorder"
			preload="false">
		</sx:div>

		<s:form action="loadqueryresult" namespace="/search" method="post">
			<s:textfield name="search" label="search" />
			<sx:submit afterNotifyTopics="Queryresult" value="search" />
			<s:hidden name="username" value="%{#request.username}" />
		</s:form>

		<s:url var="showqueryresult" action="showProductsDojo"
			namespace="/search" />
		<sx:div href="%{showqueryresult}" listenTopics="Queryresult">
		</sx:div>




		<%
			} else {
		%>


		<s:url var="showadmin" action="loadadmin" namespace="/search">
			<s:param name="username">
				<s:property value="%{#request.username}" />
			</s:param>
		</s:url>
		<sx:a afterNotifyTopics="loadcart" href="%{showadmin}">show order</sx:a>


		<s:url var="loadorderurl" action="showadmin" namespace="/search" />
		<sx:div href="%{loadorderurl}" listenTopics="loadcart">
		</sx:div>

		<%
			}
		%>
	</div>


</body>
</html>