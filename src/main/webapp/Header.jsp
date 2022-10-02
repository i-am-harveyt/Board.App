<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="model.User"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<style>
@charset "UTF-8";

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

.nav .links {
	margin-left: auto;
	margin-right: 1.5rem;
}

.nav .links a {
	margin-right: 0.5rem;
	text-decoration: none;
	color: aliceblue;
}

</style>
</head>

<body>

	<%
	User user = (User) session.getAttribute("User");
	if (user == null) {
		response.sendRedirect(request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
		+ request.getContextPath() + "/");
		return;
	}
	
	// User data, and the href for navigating to user's page
	String name = user.getName(), account = user.getAccount();
	String href = "/B08305013-D/Account/" + account;
	%>

	<div class="nav">
		<h2>
			<a href=${pageContext.request.contextPath}/TimeLine.jsp>Board.com</a>
		</h2>
		<div class="links">
			<a href=${pageContext.request.contextPath}/AddPost.jsp>NewPost</a> <a
				href=${pageContext.request.contextPath}/Global.jsp>Global</a> <a
				href=<%=href%>><%=name%></a> <a
				href=${pageContext.request.contextPath}/LogOut>Log out</a>
		</div>
	</div>

</body>

</html>