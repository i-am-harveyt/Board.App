<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isErrorPage="true" import="java.io.PrintWriter"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Error!</title>
<style>
.block {
	margin-top: 5rem;
	background-color: aliceblue;
	border-radius: 2rem;
	padding-left: 3rem;
	padding-right: 3rem;
	padding-top: 1rem;
	padding-bottom: 1rem;
}

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
</style>
</head>
<body style="background-color: rgb(50, 50, 50);">

	<div class="nav">
		<h2>
			<a href=${pageContext.request.contextPath}/TimeLine.jsp>Board.com</a>
		</h2>
	</div>

	<div class="block">
		<h3>500-Internal Server Error</h3>
	</div>

</body>
</html>