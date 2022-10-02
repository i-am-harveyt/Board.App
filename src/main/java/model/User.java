package model;

import java.util.Date;
import java.util.ArrayList;

public class User {
    private String name;
    private String account;
    private String password;
    private Date signUpDate;
    private ArrayList<String> following;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getSignUpDate() {
		return signUpDate;
	}

	public void setSignUpDate(Date signUpDate) {
		this.signUpDate = signUpDate;
	}

	public ArrayList<String> getFollowing() {
		return this.following;
	}

	public void setFollowing(ArrayList<String> following) {
		this.following = following;
	}

}