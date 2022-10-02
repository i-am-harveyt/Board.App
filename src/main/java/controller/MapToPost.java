package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Post;
import model.Posts;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class MapToPost extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		// get account and ID from URL
		String url = request.getPathInfo().substring(1);
		String[] urlFragment = url.split("/");
		String account = urlFragment[0];
		int postID = Integer.parseInt(urlFragment[1]);

		// fetch data
		@SuppressWarnings("unchecked")
		HashMap<String, ArrayList<Post>> postMap = (HashMap<String, ArrayList<Post>>) getServletContext()
				.getAttribute("PostMap");
		Post post = Posts.fetchSpecificPost(account, postID, postMap);
		if (post == null) {
			response.sendRedirect(request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
					+ request.getContextPath() + "/404.jsp");
			return;
		}

		request.setAttribute("Post", post);

		request.getRequestDispatcher("/PostPage.jsp").forward(request, response);
		return;

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
