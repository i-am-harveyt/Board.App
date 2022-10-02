package model;

import java.util.ArrayList;
import java.util.Date;

public class Post implements Comparable<Post> {
	private int postID;
	private String poster;
	private String title;
	private String content;
	private ArrayList<PostComment> comments;
	private Date time;

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public ArrayList<PostComment> getComments() {
		return comments;
	}

	public void setComments(ArrayList<PostComment> comments) {
		this.comments = comments;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	@Override
	public int compareTo(Post post) {
		return getTime().compareTo(post.getTime());
	}

	public int getPostID() {
		return postID;
	}

	public void setPostID(int postID) {
		this.postID = postID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
