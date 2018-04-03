package edu.utd.model;
import java.util.ArrayList;

public class UserManager 
{
	private User user;
	private ArrayList<FoodEntry> foodEntries;
	private Goal goal;
	
	public UserManager()
	{
		foodEntries = new ArrayList<FoodEntry>();
	}

	public void setUser(User in_user)
	{ 
		user = in_user; 
	}
	
	public User getUser()
	{ 
		return user;
	}

	public void setGoal(Goal ingoal)
	{
		goal = ingoal;
	}
	
	public void setGoal(int duration, int cgoal)
	{
		goal = new Goal(duration, cgoal);
	}
	
	
	public Goal getGoal()
	{ 
		return goal;
	}

	public void setEntries(ArrayList<FoodEntry> in_foodEntries)
	{ 
		foodEntries = in_foodEntries; 
	}
	
	public ArrayList<FoodEntry> getEntries()
	{ 
		return foodEntries;
	}
}
