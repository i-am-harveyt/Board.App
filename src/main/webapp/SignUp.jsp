<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sign Up</title>
<style>
.nav {
	position: fixed;
	top: 0;
	left: 0;
	height: 4rem;
	width: 100%;
	display: flex;
	padding-left: 1rem;
	align-items: center;
	background-color: rgba(75, 75, 75);
}

.nav h2 a {
	text-decoration: none;
	color: rgb(0, 195, 195);
}

.block {
margin-top: 5rem;
	margin-left: auto;
	margin-right: auto;
	width: 20rem;
	border-radius: 2rem;
	background-color: rgb(75, 75, 75);
	text-align: center;
	padding: 2rem;
	color: rgb(0, 200, 200);
}

.block input {
	border-radius: 2rem;
	border: solid;
	border-color: rgb(0, 200, 200);
	background: none;
	padding: 0.5rem;
	color: rgb(0, 200, 200);
}
</style>
</head>
<body style="background-color: rgb(50, 50, 50);">


	<div class="nav">
		<h2>
			<a href=${pageContext.request.contextPath}/TimeLine.jsp>Board.com</a>
		</h2>
	</div>

	<div class="block">
		<h3>Create Your Own Account!</h3>

		<%
		request.setCharacterEncoding("UTF-8");
		String error = (String) request.getAttribute("Error");
		if (error != null) {
		%>
		<strong style="color: red; margin-bottom: 1rem"><%=error%></strong>
		<%
		}
		%>

		<form action=${pageContext.request.contextPath}/SignUp method="post">

			<table>
				<tr>
					<th style="float: left; margin-top: 0.5rem;">Name:</th>
					<th><input name="name" type="text" required></th>
				</tr>
				<tr>
					<th style="float: left; margin-top: 0.5rem;">Account:</th>
					<th><input name="account" type="text" required></th>
				</tr>
				<tr>
					<th style="float: left; margin-top: 0.5rem;">Password:</th>
					<th><input name="password" type="password" required></th>
				</tr>
			</table>

			<p>
				<input type="submit" value="Sign up!">
			</p>

		</form>

	</div>

</body>
</html>