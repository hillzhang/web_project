<%@ page language="java" contentType="text/html; charset=GB18030"
	pageEncoding="GB18030"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<title>Address form</title>

</head>
<body>
	<%
		String username = request.getUserPrincipal().getName();
	%>
	<%
		request.setAttribute("username", username);
	%>

	<s:url var="error" action="addressinvalid" namespace="/search" />
	<sx:div href="%{error}" listenTopics="error" preload="false">

	</sx:div>
	<div style="float: left">
		<s:form action="compute" namespace="/search">
			<s:hidden name="username" value="%{#request.username}" />
			<s:textfield name="address" label="Address" />
			<sx:submit afterNotifyTopics="success" errorNotifyTopics="error" />
		</s:form>
	</div>




	<div style="float: left">
		<s:form action="discard" namespace="/search">
			<s:submit value="discard"
				onclick="javascript:window.open('http://localhost:8085/E-commerceSystem/newlyorder.jsp')" />
		</s:form>
	</div>
	<s:url var="success" action="showorder" namespace="/search" />
	<sx:div href="%{success}" listenTopics="success" preload="false">

	</sx:div>
</body>
</html>