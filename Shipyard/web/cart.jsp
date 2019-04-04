<%@ page import="edu.wctc.my.kgable.model.ShipModel" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: graxi
  Date: 2/27/2019
  Time: 8:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>Dominion Shipyards - Cart</title>
	<link rel="stylesheet" href="css/shipyard.css">
</head>
<body>
<div id="wrapper">
	<header>
		<h1>Dominion Shipyards</h1>
	</header>
	<nav>
		<a href="index.jsp">Home</a>
		<span class="spacer"></span>
		<a href="catalog.go">Catalog</a>
		<span class="spacer"></span>
		<a href="cart.go">Cart</a>
	</nav>
	<main>
		<form action="bill.go">
			<table>
				<tr>
					<th>Name</th>
					<th>Price</th>
					<th>Remove</th>
				</tr>
				<%
					ArrayList<ShipModel> cart = (ArrayList<ShipModel>)request.getAttribute("cart");

					ShipModel shipModel;
					int total = 0;

					for (int i = 0; i < cart.size(); i++)
					{
						shipModel = cart.get(i);

						out.print("<tr><td>" + shipModel.getName() + "</td><td>" + shipModel.getBasePrice() + " Credits</td><td><a href='delete.go?model=" + shipModel.getModelID() + "'><button type='button'>Remove</button></a></td></tr>");

						total += shipModel.getBasePrice();
					}

					out.print("<tr><th>Total</th><th>" + total + " Credits</th><th></th>");
				%>
			</table>
			<input type="submit" value="Purchase">
		</form>
	</main>
	<footer>
		<a href="mailto:kgable@my.wctc.edu">kgable@my.wctc.edu</a>
	</footer>
</div>
</body>
</html>