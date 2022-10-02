<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.ArrayList, java.util.Date"
	import="model.Post, model.PostComment, model.PostComments, model.User"
	import="java.text.SimpleDateFormat"%>

<%
// fetch user and post
Post post = (Post) request.getAttribute("Post");
User user = (User) session.getAttribute("User");
if (user == null) {
	response.sendRedirect(request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
	+ request.getContextPath() + "/");
	return;
}
if (post == null) {
	request.getRequestDispatcher("").forward(request, response);
	return;
}

// fetch comment
ArrayList<PostComment> comments = post.getComments();

// href for navigating to addComment servlet
String href = "/B08305013-D/AddComment/" + post.getPoster() + "/" + post.getPostID();
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><%=post.getTitle()%></title>
<style>
.post-area {
	margin-top: 5rem;
	border-radius: 2rem;
	padding-top: 1rem;
	padding-bottom: 1rem;
	padding-left: 3rem;
	padding-right: 3rem;
	background-color: aliceblue;
}

.post-area a {
	text-decoration: none;
	color: black;
}

.comment-area {
	margin-top: 1rem;
	background-color: aliceblue;
	align-items: center;
	border-radius: 2rem;
	padding-top: 1rem;
	padding-bottom: 1rem;
	padding-left: 3rem;
	padding-right: 3rem;
}

.comment-area textarea {
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

.comment {
	border-style: solid;
	border-radius: 2rem;
	padding: 1rem;
}

.comment a {
	text-decoration: none;
	color: black;
}
</style>
</head>
<body style="background-color: rgb(50, 50, 50)">
	<jsp:include page="Header.jsp" />

	<div class="post-area">
		<div>
			<p>
				<a href=<%="/B08305013-D/Account/" + post.getPoster()%>> <%=post.getPoster()%>
				</a>
			<h3>
				<%=post.getTitle()%></h3>
			<p>
				<%=post.getContent().replaceAll("(\r\n|\n)", "<br>")%>
			</p>
			<%
			Date postTime = post.getTime();
			SimpleDateFormat postFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			%>
			<p>
				<%=postFormat.format(postTime)%></p>
		</div>
	</div>
	<div class="comment-area">
		<form action=<%=href%> method="post">
			Add Comment: <br />
			<p>
				<textarea name="Comment"></textarea>
			</p>
			<input class="submit-button" type="submit" value="Submit!" />
		</form>
		<p>Comment:</p>

		<%
		for (PostComment comment : comments) {
		%>
		<div class="comment">
			<p>
				<a href=<%="/B08305013-D/Account/" + comment.getCommenter()%>> <%=comment.getCommenter()%>
				</a>
			</p>
			<p>
				<%=comment.getContent().replaceAll("(\r\n|\n)", "<br>")%>
			</p>
			<%
			Date commentTime = comment.getTime();
			SimpleDateFormat commentFormat = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
			%>
			<p>
				<%=commentFormat.format(commentTime)%></p>
		</div>
		<br />
		<%
		}
		%>
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