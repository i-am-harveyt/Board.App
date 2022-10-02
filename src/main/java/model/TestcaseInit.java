package model;

import java.util.Date;
import java.util.HashMap;
import java.util.ArrayList;

public class TestcaseInit {
	// users
	private String[] testAccount = { "User", "Harvey", "Tony", "Tom", "David" };
	private String[] testPassword = { "pwd1", "pwd2", "pwd3", "pwd4", "pwd5" };
	private String[] testName = { "User", "Harvey", "Tony", "Tom", "David" };

	// posts
	private String[] testPosters = { "User", "User", "Tony", "Tony", "Tony" };
	private String[] testTitle = { "Title1", "Title2", "Title3", "Title4", "Title5" };
	private String[] testContent = { "Hello1", "Hello2", "Hello3", "Hello4", "Hello5" };

	// comments
	private String[] testCommenters = { "Harvey", "Tony", "Harvey", "Tom", "David" };
	private String[] testComments = { "Comment1", "Comment2", "Comment3", "Comment4", "Comment5" };
	private String[] testPostAccount = { "User", "User", "Tony", "Tony", "Tony" };
	private int[] testPostID = { 1, 1, 1, 1, 1 };

	public TestcaseInit(HashMap<String, User> userMap, HashMap<String, ArrayList<Post>> postMap) {
		// add test users
		for (int i = 0; i < testAccount.length; i++)
			if (!userMap.containsKey(testAccount[i]))
				Users.addUser(testAccount[i], testPassword[i], testName[i], userMap, postMap);

		// User follows Tony
		Users.toFollow("User", "Tony", userMap);

		// add test posts
		for (int i = 0; i < testPosters.length; i++) {
			Post post = new Post();
			post.setPoster(testPosters[i]);
			post.setTitle(testTitle[i]);
			post.setContent(testContent[i]);
			post.setComments(null);
			post.setTime(new Date());
			Posts.addPost(testPosters[i], post, postMap);

		}
		
		// add test comments
		for (int i = 0 ; i < testCommenters.length ; i++) {
			Post post = Posts.fetchSpecificPost(testPostAccount[i], testPostID[i], postMap);
			PostComments.addComment(testCommenters[i], testComments[i], post);
		}
	}

}
