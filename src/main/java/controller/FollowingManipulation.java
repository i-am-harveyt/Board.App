package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;
import model.Users;

import java.io.IOException;
import java.util.HashMap;

public class FollowingManipulation extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);

		request.setCharacterEncoding("UTF-8");

		// fetch user and target account
		User user = (User) request.getSession().getAttribute("User");
		if (user == null) {
			response.sendRedirect(request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
					+ request.getContextPath() + "/");
			return;
		}
		String userAccount = user.getAccount();
		String targetAccount = request.getParameter("Account");
		if (targetAccount == null) {
			response.sendRedirect(request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
					+ request.getContextPath() + "/");
			return;
		}

		// follow or unfollow
		String query = request.getParameter("FollowQuery");

		@SuppressWarnings("unchecked")
		HashMap<String, User> userMap = (HashMap<String, User>) request.getServletContext().getAttribute("UserMap");
		if (query.equals("Follow"))
			Users.toFollow(userAccount, targetAccount, userMap);
		else
			Users.unFollow(userAccount, targetAccount, userMap);

		response.sendRedirect(request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
				+ request.getContextPath() + "/Account/" + targetAccount);
		return;
	}

}
