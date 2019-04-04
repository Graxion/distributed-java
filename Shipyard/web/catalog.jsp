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
	<title>Dominion Shipyards - Catalog</title>
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
		<h3>Catalog</h3>
		<form action="processcart.go">
			<table>
				<tr>
					<th>Name</th>
					<th>Class</th>
					<th>Price</th>
					<th>Add to Cart</th>
				</tr>
				<%
					ArrayList<ShipModel> catalog = (ArrayList<ShipModel>) request.getAttribute("catalog");

					ShipModel shipModel;

					for (int i = 0; i < catalog.size(); i++)
					{
						shipModel = catalog.get(i);

						out.print("<tr><td>" + shipModel.getName() + "</td><td>" + shipModel.getShipClass() + "</td><td>" + shipModel.getBasePrice() + " Credits</td><td><input type='checkbox' name='cartItem' value=" + shipModel.getModelID() + "></td></tr>");
					}
				%>
			</table>
			<input type="submit" value="Add to Cart">
		</form>
	</main>
	<footer>
		<a href="mailto:kgable@my.wctc.edu">kgable@my.wctc.edu</a>
	</footer>
</div>
</body>
</html>