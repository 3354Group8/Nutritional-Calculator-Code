package edu.utd.model;

import java.sql.Date;

public class User {
	
	private String username;
	private String password;
	private Date dob;
	private int height;
	private int gender;
	
	public User(String in_username, String in_password)
	{
		username = in_username;
		password = in_password;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return username;
	}
	public void setPassword(String username) {
		this.username = username;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}

}
