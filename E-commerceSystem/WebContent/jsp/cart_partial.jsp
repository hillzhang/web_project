<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>shopping cart table</title>
</head>
<body>
	<h2>Items in cart :</h2>
	<table>
		<s:iterator value="cartData">
			<tr>
				<td><s:property value="title" /></td>
				<td><s:property value="quantity" /></td>
			</tr>
		</s:iterator>
		<tfoot>
			<tr>
				<th>Product total:</th>
				<th><s:property value="totalPrice" /></th>

			</tr>
		</tfoot>
	</table>

	<s:url var="showform" action="checkout" namespace="/search" />
	<sx:div href="%{showform}" listenTopics="loadformurl">

	</sx:div>

	<%
		String form = (String) session.getAttribute("form");
	%>
	<%
		if (form == null) {
	%>
	<div id="divID" style="display: block">
		<s:form action="loadform" namespace="/search" method="post">
			<sx:submit value="checkout" afterNotifyTopics="loadformurl"
				onclick='document.getElementById("divID").style.display="none"' />
		</s:form>
	</div>
	<%
		}
	%>



</body>
</html>