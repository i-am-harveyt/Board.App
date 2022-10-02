package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Users {
	/**
	 * Check account existed or not
	 * 
	 * @param account
	 * @param map
	 * @return true/false
	 */
	public static boolean checkAccountExisted(String account, HashMap<String, User> map) {
		if (map == null)
			return false;
		
		if (map.containsKey(account))
			return true;
		
		return false;
	}
	
	/**
	 * Check password matched or not
	 * 
	 * @param account
	 * @param password
	 * @param map
	 * @return true/false
	 */
	public boolean checkPasswordMatched (String account, String password, HashMap<String, User> map) {
		if (map == null)
			return false;

		User user = map.get(account);
		
		if (password.equals(user.getPassword()))
			return true;

		return false;
	}
	
	/**
	 * Add User
	 * 
	 * @param account
	 * @param password
	 * @param name
	 * @param userMap
	 * @param postMap
	 */
	public static void addUser(String account, String password, String name, HashMap<String, User> userMap, HashMap<String, ArrayList<Post>> postMap) {
		User user = new User();
		user.setAccount(account);
		user.setPassword(password);
		user.setName(name);
		user.setSignUpDate(new Date());
		user.setFollowing(new ArrayList<String>());
		userMap.put(account, user);
		
		ArrayList<Post> posts = new ArrayList<Post>();
		postMap.put(account, posts);
	}
	
	/**
	 * Check if account is following target
	 * 
	 * @param account
	 * @param target
	 * @param map
	 * @return true/false
	 */
	public static boolean isFollowing (String account, String target, HashMap<String, User> map) {
		if (map == null)
			return false;
		User user = map.get(account);
		return user.getFollowing().contains(target);
	}
	
	public static void toFollow (String account, String followAccount, HashMap<String, User> map) {
		if (map == null)
			return;
		map.get(account).getFollowing().add(followAccount);
	}
	
	public static void unFollow (String account, String target, HashMap<String, User> map) {
		if (map == null)
			return;
		map.get(account).getFollowing().remove(target);
	}
	

}
