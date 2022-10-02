package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;

public class Posts {

	private static int querySize = 100;
	private static int divider = 10;

	/**
	 * This function will fetch all posts posted by this account.
	 * 
	 * @param account User account
	 * @param map     Map of account -> post from this account
	 * @return Post from this account
	 */
	public static ArrayList<Post> fetchPosts(String account, HashMap<String, ArrayList<Post>> map) {
		if (map == null)
			return null;
		return map.get(account);
	}

	public static Post fetchSpecificPost(String account, int id, HashMap<String, ArrayList<Post>> map) {
		ArrayList<Post> posts = fetchPosts(account, map);

		if (posts == null)
			return null;

		if (id <= posts.size()) {
			Post ret = posts.get(id - 1);

			return ret;
		}

		return null;

	}

	/**
	 * 
	 * @param account User account
	 * @param query   Number of posts
	 * @param map     Map of account -> post from this account
	 * @return Query numbers of posts (if enough)
	 */
	public static ArrayList<Post> fetchSpecificNumPosts(String account, int query,
			HashMap<String, ArrayList<Post>> map) {
		ArrayList<Post> posts = fetchPosts(account, map);
		int size = posts.size();
		ArrayList<Post> ret = new ArrayList<Post>();

		for (int i = size - 1; i >= 0; i--)
			ret.add(posts.get(i));

		return ret;
	}

	/**
	 * 
	 * @param account User account
	 * @param postMap Account -> Posts map
	 * @param userMap Account -> User map
	 * @return Query size of posts that sort by posting time from User's following
	 *         accounts
	 */
	public static ArrayList<Post> fetchTimeLinePosts(String account, HashMap<String, ArrayList<Post>> postMap,
			HashMap<String, User> userMap) {
		ArrayList<Post> ret = fetchSpecificNumPosts(account, querySize, postMap);
		ArrayList<Post> query;

		User user = userMap.get(account);
		ArrayList<String> following = user.getFollowing();

		for (int i = 0; i < following.size(); i++) {
			query = fetchSpecificNumPosts(following.get(i), querySize, postMap);
			for (int j = 0; j < query.size(); j++)
				ret.add(query.get(j));
			Collections.sort(ret);
			while (ret.size() > querySize)
				ret.remove(querySize);
		}

		return ret;
	}

	/**
	 * 
	 * @param postMap Account -> Posts map
	 * @param userMap Account -> Users map
	 * @return Query size of posts sorted by posting time from every users
	 */
	public static ArrayList<Post> fetchGlobalPosts(HashMap<String, ArrayList<Post>> postMap,
			HashMap<String, User> userMap) {
		ArrayList<Post> ret = new ArrayList<Post>();
		ArrayList<Post> query;
		int retSize;

		for (String account : postMap.keySet()) {
			query = fetchSpecificNumPosts(account, querySize / divider, postMap);
			retSize = ret.size();
			for (int j = 0; j < query.size(); j++)
				ret.add(query.get(j));
			if (retSize != ret.size()) {
				Collections.sort(ret);
				retSize = ret.size();
			}
			while (retSize > querySize) {
				ret.remove(querySize);
				retSize--;
			}
		}

		return ret;
	}

	/**
	 * 
	 * @param account User account
	 * @param post    New post
	 * @param postMap Account -> Posts map
	 */
	public static void addPost(String account, Post post, HashMap<String, ArrayList<Post>> postMap) {
		ArrayList<Post> accountPosts = postMap.get(account);

		post.setPostID(accountPosts.size() + 1);
		post.setTime(new Date());
		post.setComments(new ArrayList<PostComment>());
		accountPosts.add(post);
	}

}
