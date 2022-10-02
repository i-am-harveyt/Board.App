package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Post;
import model.Posts;
import model.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class AddPost extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);

		request.setCharacterEncoding("UTF-8");

		User user = (User) request.getSession().getAttribute("User");
		if (user == null) {
			response.sendRedirect(request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
					+ request.getContextPath() + "/");
			return;
		}

		String account = user.getAccount();
		String title = request.getParameter("Title");
		String content = request.getParameter("Content");

		Post post = new Post();
		post.setPoster(account);
		post.setTitle(title);
		post.setContent(content);

		@SuppressWarnings("unchecked")
		HashMap<String, ArrayList<Post>> postMap = (HashMap<String, ArrayList<Post>>) getServletContext()
				.getAttribute("PostMap");
		Posts.addPost(account, post, postMap);
		
		response.sendRedirect(request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
					+ request.getContextPath() + "/TimeLine.jsp");
		return;
	}

}
