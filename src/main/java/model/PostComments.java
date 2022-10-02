package model;

import java.util.Date;

public class PostComments {
	public static void addComment(String commenter, String content, Post post) {
		PostComment comment = new PostComment();
		comment.setCommenter(commenter);
		comment.setPostAccount(post.getPoster());
		comment.setPostID(post.getPostID());
		comment.setContent(content);
		comment.setTime(new Date());

		post.getComments().add(0, comment);
	}

}
