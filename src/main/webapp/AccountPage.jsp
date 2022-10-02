<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.ArrayList, java.util.HashMap, java.util.Date"
	import = "model.Post, model.Posts, model.User, model.Users"
	import = "java.text.SimpleDateFormat"
	%>

<%
// fetch user data, if no user, redirect to login page
User user = (User) session.getAttribute("User");
if (user == null) {
	response.sendRedirect(request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
	+ request.getContextPath() + "/");
	return;
}
// get user account
String userAccount = user.getAccount();

// get account that this page is gonna present
String account = (String) request.getAttribute("Account");
// if no account, which means there's wrong input
if (account == null) {
	response.sendRedirect(request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
	+ request.getContextPath() + "/");
	return;
}
// renew attribute, but it might
request.setAttribute("Account", account);

// check this page is user's page or not
boolean isUser = userAccount.equals(account);

// fetch data and check if user is following this account
@SuppressWarnings("unchecked")
ArrayList<Post> posts = Posts.fetchPosts(account,
		(HashMap<String, ArrayList<Post>>) application.getAttribute("PostMap"));
@SuppressWarnings("unchecked")
boolean isFollowing = Users.isFollowing(userAccount, account,
		(HashMap<String, User>) application.getAttribute("UserMap"));

// for the content in button
String value = "Follow";
if (!isUser && isFollowing)
	value = "Unfollow";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><%=account%></title>
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

.bar {
	margin-top: 3rem;
	display: flex;
	align-items: center;
}

.bar h3 {
	margin-left: 1rem;
}

#follow-form {
	margin-left: auto;
	margin-right: 1rem;
}
</style>
</head>
<body style="background-color: rgb(50, 50, 50);">

	<jsp:include page="Header.jsp" />

	<div style="margin-top: 5rem"></div>
	<div class="bar">
		<h3 style="color: aliceblue;">
			<%=account%>
		</h3>
		<%
		if (!isUser) {
		%>
		<form id="follow-form" action="/B08305013-D/FollowingManipulation"
			method="post">
			<input type="radio" name="Account" value=<%=account%> checked
				style="display: none" /> <input type="radio" name="FollowQuery"
				value=<%=value%> checked style="display: none" />
			<button class="follow-button" form="follow-form" type="submit"
				style="background: none; border: solid; border-color: rgb(0, 200, 200); border-radius: 2rem; padding: 0.5rem; color: rgb(0, 200, 200); cursor: pointer;">
				<%=value%></button>
		</form>
		<%
		}
		%>
	</div>

	<div class="gap" style="margin-top: 2rem;"></div>

	<%
	if (posts != null) {
		Post post;
		for (int i = posts.size() - 1; i >= 0; i--) {
			post = posts.get(i);
	%>
	<div class="post">
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
	}
	%>


</body>
</html>