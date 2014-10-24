<%@ page language="java" contentType="text/html; charset=GB18030"
	pageEncoding="GB18030"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<title>Order detail</title>

</head>
<body>
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<p>order details</p>
	<table border="1">
		<thead>
			<tr>
				<th>Title</th>
				<th>Quantity</th>
				<th>ItemPrice</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="cartData">
				<tr>
					<td><s:property value="title" />
					</td>
					<td><s:property value="quantity" />
					</td>
					<s:set var="itemprice" value="quantity*price" />
					<td>$<s:property value="#itemprice" />
					</td>
				</tr>
			</s:iterator>

		</tbody>
	</table>
	<p>
		Shipping Fee:$
		<s:property value="shippingfee" />
	</p>

	<p>
		Total price:$
		<s:property value="totalPrice" />
	</p>

	<a href="http://localhost:8085/E-commerceSystem/newlyorder.jsp"
		target="_blank" style="display: _block" id="onelink">newly order</a>
	<%-- <script>
		document.getElementById('onelink').click();
	</script> --%>
</body>
</html>