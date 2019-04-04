<%--
  Created by IntelliJ IDEA.
  User: graxi
  Date: 2/27/2019
  Time: 7:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
	<head>
		<title>Dominion Shipyards - Home</title>
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
				<form action="search.go">
					<input type="text" name="search">
					<label><input type="radio" name="column" value="name">Name</label>
					<label><input type="radio" name="column" value="class">Class</label>
					<input type="submit" value="Search">
				</form>
			</main>
			<footer>
				<a href="mailto:kgable@my.wctc.edu">kgable@my.wctc.edu</a>
			</footer>
		</div>
	</body>
</html>
