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
<title></title>
<%
	String username = request.getUserPrincipal().getName();
%>
<%
	request.setAttribute("username", username);
%>
<sx:head />
</head>
<body>

	<div id="wrap">

		<div id="header"></div>

		<div id="catalogue">

			<h1>Catalogue</h1>

			<table border="1">
				<thead>
					<tr>

						<th>Item Title</th>
						<th>Tags</th>
						<th>Image</th>
						<th>Price</th>
						<th>Action</th>

					</tr>
				</thead>
				<tbody>
					<s:iterator value="products">
						<tr>
							<td width="250px"><s:property value="title" /></td>
							<td width="450px"><s:property value="tag" /></td>
							<td><img src="<s:property value='imageurl'/>"
								alt="image not available"></td>
							<td>$<s:property value="price" /></td>
							<td width="100px"><s:url var="addCartUrl"
									action="addToCartDojo" namespace="/search">
									<s:param name="id">
										<s:property value="id" />
									</s:param>
								</s:url> <sx:a afterNotifyTopics="loadcart" href="%{addCartUrl}">Add to Cart</sx:a>

								<s:url var="removeCartUrl" action="removefromCartDojo"
									namespace="/search">
									<s:param name="id">
										<s:property value="id" />
									</s:param>
								</s:url> <sx:a afterNotifyTopics="loadcart" href="%{removeCartUrl}">Remove</sx:a>
							</td>
						</tr>
					</s:iterator>
			</table>

		</div>


		<s:url var="loadCartUrl" action="loadCart" namespace="/search" />
		<sx:div id="cart" href="%{loadCartUrl}" listenTopics="loadcart">

		</sx:div>


	</div>
</body>
</html>