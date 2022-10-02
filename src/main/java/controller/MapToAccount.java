package controller;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Post;
import model.Posts;
import model.User;
import model.Users;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class MapToAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		// get account from URL
		String url = request.getPathInfo().substring(1);
		String[] urlFragment = url.split("/");
		String account = urlFragment[0];

		// fetch data
		ServletContext ctx = request.getServletContext();
		@SuppressWarnings("unchecked")
		HashMap<String, User> userMap = (HashMap<String, User>) ctx.getAttribute("UserMap");
		boolean accountExist = Users.checkAccountExisted(account, userMap);
		if (!accountExist) {
			response.sendRedirect(request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
					+ request.getContextPath() + "/404.jsp");
			return;
		}
		@SuppressWarnings("unchecked")
		HashMap<String, ArrayList<Post>> postMap = (HashMap<String, ArrayList<Post>>) getServletContext()
				.getAttribute("PostMap");
		ArrayList<Post> posts = Posts.fetchPosts(account, postMap);

		request.setAttribute("Posts", posts);
		request.setAttribute("Account", account);

		request.getRequestDispatcher("/AccountPage.jsp").forward(request, response);
		return;

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
