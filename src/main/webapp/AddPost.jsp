<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="model.User"%>

<%
User user = (User) session.getAttribute("User");
if (user == null) {
	response.sendRedirect(request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
	+ request.getContextPath() + "/");
	return;
}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add New Post</title>
<style>
.block {
	margin-top: 5rem;
	margin-left: auto;
	margin-right: auto;
	background-color: aliceblue;
	border-radius: 2rem;
	padding-top: 1rem;
	padding-bottom: 1rem;
	padding-left: 3rem;
	padding-right: 3rem;
	padding-top: 1rem;
}

.title-input {
	width: 20rem;
	border-style: solid;
	padding-left: 1rem;
	padding-right: 1rem;
}

.content-input {
	border-style: solid;
	width: 20rem;
	resize: none;
	padding-left: 1rem;
	padding-right: 1rem;
	overflow: hidden;
}

.submit-button {
	background: none;
	border: solid;
	padding: 0.5rem;
	cursor: pointer;
	margin-left: auto;
	margin-right: auto;
	border: solid;
}
</style>
</head>
<body style="background-color: rgb(50, 50, 50)">
	<jsp:include page="Header.jsp" />
	<div class="block">
		<form action=${pageContext.request.contextPath}/AddPost method="post">
			<p>Title:</p>
			<input class="title-input" name="Title" type="text" required /> <br>
			<p>Content:</p>
			<p>
				<textarea class="content-input" name="Content" required></textarea>
			</p>
			<input class="submit-button" type="submit" value="Post!" />
		</form>
	</div>

	<script>
		const tx = document.getElementsByTagName("textarea");
		for (let i = 0; i < tx.length; i++) {
			tx[i].setAttribute("style", "height:" + (tx[i].scrollHeight)
					+ "px;overflow-y:hidden;");
			tx[i].addEventListener("input", OnInput, false);
		}

		function OnInput() {
			this.style.height = "auto";
			this.style.height = (this.scrollHeight) + "px";
		}
	</script>
</body>
</html>