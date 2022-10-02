package controller;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Post;
import model.User;
import model.Users;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class SignUp extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect(request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
				+ request.getContextPath() + "/SignUp.jsp");
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		String name = request.getParameter("name");
		String account = request.getParameter("account");
		String password = request.getParameter("password");

		// fetch UserMap
		ServletContext ctx = getServletContext();
		@SuppressWarnings("unchecked")
		HashMap<String, User> userMap = (HashMap<String, User>) ctx.getAttribute("UserMap");
		@SuppressWarnings("unchecked")
		HashMap<String, ArrayList<Post>> postMap = (HashMap<String, ArrayList<Post>>) ctx.getAttribute("PostMap");

		if (userMap == null) {
			userMap = new HashMap<String, User>();
			ctx.setAttribute("UserMap", userMap);
		}

		// check account existed
		boolean accountExisted = Users.checkAccountExisted(account, userMap);
		if (accountExisted) {
			request.setAttribute("Error", "Account Existed!");
			request.getRequestDispatcher("/SignUp.jsp").forward(request, response);
			return;
		}

		Users.addUser(account, password, name, userMap, postMap);

		// back to login page
		response.sendRedirect(request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
				+ request.getContextPath() + "/");
		return;

	}

}
