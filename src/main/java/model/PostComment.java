package model;

import java.util.Date;

public class PostComment {
	private String commenter;
	private String postAccount;
	private int postID;
	private String content;
	private Date time;

	public String getCommenter() {
		return commenter;
	}
	
	public void setCommenter(String commenter) {
		this.commenter = commenter;
	}

	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}

	public String getPostAccount() {
		return postAccount;
	}

	public void setPostAccount(String postAccount) {
		this.postAccount = postAccount;
	}

	public int getPostID() {
		return postID;
	}

	public void setPostID(int postID) {
		this.postID = postID;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

}
