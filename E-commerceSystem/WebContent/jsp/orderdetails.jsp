<%@ page language="java" contentType="text/html; charset=GB18030"
	pageEncoding="GB18030"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<%@ taglib prefix="s" uri="/struts-tags"%>
<title></title>
</head>
<body>
	<table border="1">
		<thead>
			<tr>
				<th>Title</th>
				<th>Quantity</th>
				<th>Price</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="order">
				<tr>
					<td><s:property value="title" /></td>
					<td><s:property value="totalnum" /></td>
					<td>$<s:property value="totalprice" /></td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
</body>
</html>