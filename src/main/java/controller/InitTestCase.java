package controller;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Post;
import model.User;
import model.TestcaseInit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class InitTestCase extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		// fetch data and init
		ServletContext ctx = getServletContext();
		@SuppressWarnings("unchecked")
		HashMap<String, User> userMap = (HashMap<String, User>) ctx.getAttribute("UserMap");
		@SuppressWarnings("unchecked")
		HashMap<String, ArrayList<Post>> postMap = (HashMap<String, ArrayList<Post>>) ctx.getAttribute("PostMap");
		if (userMap == null) {
			userMap = new HashMap<String, User>();
			ctx.setAttribute("UserMap", userMap);
		}
		if (postMap == null) {
			postMap = new HashMap<String, ArrayList<Post>>();
			ctx.setAttribute("PostMap", postMap);
		}

		TestcaseInit init = new TestcaseInit(userMap, postMap);

		response.sendRedirect(request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
				+ request.getContextPath() + "/");
		return;

	}

}
