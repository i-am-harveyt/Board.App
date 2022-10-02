<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="model.User"%>

<%
User user = (User) session.getAttribute("User");
if (user != null) {
	response.sendRedirect(request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
	+ request.getContextPath() + "/TimeLine.jsp");
	return;
}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>Login</title>
</head>
<body style="background-color: rgb(50, 50, 50)">

	<div
		style="margin-top: auto; margin-bottom: auto; margin-left: auto; margin-right: auto; width: 20rem; border-radius: 2rem; background-color: rgb(75, 75, 75); text-align: center; padding: 2rem;">

		<h3 style="color: rgba(0, 200, 200)">Welcome to Board.com!</h3>

		<%
		request.setCharacterEncoding("UTF-8");
		String error = (String) request.getAttribute("Error");
		if (error != null) {
		%>
		<strong style="color: red"><%=error%></strong>
		<div class="gap" style="margin-top: 1rem"></div>
		<%
		}
		%>

		<form id="login-form" action=${pageContext.request.contextPath}/Login
			method="post">
			<table style="margin-left: auto; margin-right: auto;">
				<tr>
					<th
						style="margin-top: 0.5rem; float: left; color: rgb(0, 200, 200);">Account:</th>
					<th><input type="text" name="account"
						style="border-radius: 2rem; border: solid; border-color: rgb(0, 200, 200); background: none; padding: 0.5rem; color: aliceblue;"
						required></th>
				</tr>
				<tr>
					<th
						style="margin-top: 0.5rem; float: left; color: rgb(0, 200, 200)">Password:</th>
					<th><input type="password" name="password"
						style="border-radius: 2rem; border: solid; border-color: rgb(0, 200, 200); background: none; padding: 0.5rem; color: aliceblue;"
						required></th>
				</tr>
			</table>
			<p>
				<a href=${pageContext.request.contextPath}/SignUp.jsp
					style="text-decoration: none; color: aliceblue;">Have no
					account? Sign Up!</a>
			</p>
			<p>
				<button form="login-form" type="submit"
					style="background: none; border: solid; border-color: rgb(0, 200, 200); border-radius: 2rem; padding: 0.5rem; color: rgb(0, 200, 200); cursor: pointer;">
					Login</button>
			</p>
		</form>
		<form id="init-form"
			action=${pageContext.request.contextPath}/InitTestCase method="post">
			<button form="init-form" type="submit"
				style="background: none; border: solid; border-color: rgb(0, 200, 200); border-radius: 2rem; padding: 0.5rem; color: rgb(0, 200, 200); cursor: pointer;">
				Init TestCase</button>
		</form>
	</div>
</body>
</html>
