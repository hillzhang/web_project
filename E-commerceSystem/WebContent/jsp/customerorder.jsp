<%@ page language="java" contentType="text/html; charset=GB18030"
	pageEncoding="GB18030"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<title>Customer orders</title>
</head>
<body>

	<div>
		<table border="1">
			<thead>
				<tr>
					<th>OrderID</th>
					<th>Username</th>
					<th>TotalItem</th>
					<th>Shippingfee</th>
					<th>TotalPrice</th>
					<th>Address</th>
					<th>State</th>
					<th>Details</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="order">
					<tr>
						<td><s:property value="orderid" /></td>
						<td><s:property value="username" /></td>
						<td><s:property value="totalnum" /></td>
						<td>$<s:property value="shippingfee" /></td>
						<td>$<s:property value="totalprice" /></td>
						<td><s:property value="address" /></td>
						<td><s:property value="state" /></td>
						<td><s:form action="loaddetails" namespace="/search"
								method="post">
								<s:hidden name="orderid" value="%{orderid}" />
								<sx:submit afterNotifyTopics="showdetails" value="details" />
							</s:form></td>
					</tr>
				</s:iterator>
			</tbody>
		</table>


		<s:url var="orderdetails" action="showorderdetails"
			namespace="/search">

		</s:url>
		<sx:div href="%{orderdetails}" listenTopics="showdetails"
			preload="false">
		</sx:div>
	</div>

</body>
</html>