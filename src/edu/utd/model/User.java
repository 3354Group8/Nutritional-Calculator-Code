package edu.utd.model;

public class User {
	
	private String username;
	private String password;
	
	public User(String in_username, String in_password)
	{
		username = in_username;
		password = in_password;
	}

	public String getUsername() 
	{
		return username;
	}
	public void setUsername(String username) 
	{
		this.username = username;
	}
	public String getPassword() 
	{
		return password;
	}
	public void setPassword(String password) 
	{
		this.password = password;
	}
}
