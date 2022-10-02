package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Post;
import model.PostComments;
import model.Posts;
import model.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class AddComment extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);

        // fetch account and postID from URL
        String url = request.getPathInfo().substring(1);
        String[] urlFragment = url.split("/");
        String account = urlFragment[0];
        int postID = Integer.parseInt(urlFragment[1]);

        @SuppressWarnings("unchecked")
        HashMap<String, ArrayList<Post>> postMap = (HashMap<String, ArrayList<Post>>) getServletContext()
                .getAttribute("PostMap");

        // fetch the post corresponds to account and postID
        Post post = Posts.fetchSpecificPost(account, postID, postMap);

        User user = (User) request.getSession().getAttribute("User");
        String content = request.getParameter("Comment");

        PostComments.addComment(user.getAccount(), content, post);

        response.sendRedirect(request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
                + request.getContextPath() + "/" + "Post/" + account + "/" + postID);
        return;

    }

}
