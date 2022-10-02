<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="model.User, model.Post, model.Posts"
	import = "java.util.ArrayList, java.util.Date, java.util.HashMap"
	import = "java.text.SimpleDateFormat"
	%>

<%
User user = (User) session.getAttribute("User");
if (user == null) {
	response.sendRedirect(request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
	+ request.getContextPath() + "/");
	return;
}

// fetch posts for global page.
@SuppressWarnings("unchecked")
ArrayList<Post> globalPosts = Posts.fetchGlobalPosts(
		(HashMap<String, ArrayList<Post>>) application.getAttribute("PostMap"),
		(HashMap<String, User>) application.getAttribute("UserMap"));
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TimeLine</title>
<style>
.post {
	background-color: aliceblue;
	border-radius: 2rem;
	padding-top: 1rem;
	padding-bottom: 1rem;
	padding-left: 3rem;
	padding-right: 3rem;
}

.post a {
	text-decoration: none;
	color: black;
}
</style>
</head>
<body style="background-color: rgb(50, 50, 50);">

	<jsp:include page="Header.jsp" />

	<div style="margin-top: 5rem"></div>
	<%
	Post post;
	for (int i = globalPosts.size() - 1; i >= 0; i--) {
		post = globalPosts.get(i);
	%>
	<div class="post">
		<p>
			<a href=<%="/B08305013-D/Account/" + post.getPoster()%>> <%=post.getPoster()%>
			</a>
		</p>
		<h3>
			<a href=<%="/B08305013-D/Post/" + post.getPoster() + "/" + post.getPostID()%>>
				<%=post.getTitle()%>
			</a>
		</h3>
		<%
		Date time = post.getTime();
		SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
		%>
		<p>
			<%=ft.format(time)%></p>
	</div>
	<br>
	<%
}
%>


</body>
</html>