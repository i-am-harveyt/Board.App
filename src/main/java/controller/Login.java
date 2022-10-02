package controller;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;

import java.io.IOException;
import java.util.HashMap;

public class Login extends HttpServlet {
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

		String account = request.getParameter("account");
		String password = request.getParameter("password");

		// fetch user map
		ServletContext ctx = getServletContext();
		@SuppressWarnings("unchecked")
		HashMap<String, User> userMap = (HashMap<String, User>) ctx.getAttribute("UserMap");

		// There is no UserMap
		if (userMap == null) {
			request.setAttribute("Error", "Account Not Found!");
			request.getRequestDispatcher("").forward(request, response);
		}

		// account exist
		if (userMap.containsKey(account)) {
			// password mismatch
			if (!userMap.get(account).getPassword().equals(password)) {
				request.setAttribute("Error", "Password Mismatch!");
				request.getRequestDispatcher("").forward(request, response);
			}
			
			// fetch data and set session
			User user = userMap.get(account);
			request.getSession().setAttribute("User", user);

			request.getRequestDispatcher("TimeLine.jsp").forward(request, response);
			return;
		}
		// account not exist
		else {
			request.setAttribute("Error", "Account Not Found!");
			request.getRequestDispatcher("").forward(request, response);
			return;
		}
	}

}
