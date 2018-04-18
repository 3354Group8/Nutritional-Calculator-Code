package edu.utd.controller;
import edu.utd.model.DatabaseManager;
import edu.utd.model.FoodItemManager;
import edu.utd.model.UserManager;

public class UserController 
{
	private DatabaseManager dbMan;
	private UserManager uMan;
	private FoodItemManager fMan;
	
	public UserController(DatabaseManager in_dbMan, UserManager in_uMan, FoodItemManager in_fMan)
	{
		dbMan = in_dbMan;
		uMan = in_uMan;
		fMan = in_fMan;
	}
	
	public int loginUser(String username, String password)
	{
		return dbMan.loadUser(username, password, uMan, fMan);
	}
	
	public boolean signupUser(String username, String password)
	{
		return dbMan.createUser(username, password);
	}
}