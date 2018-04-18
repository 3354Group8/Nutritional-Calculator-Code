package edu.utd.controller;
import edu.utd.model.DatabaseManager;
import edu.utd.model.UserManager;

public class GoalController
{
	private DatabaseManager dbMan;
	private UserManager uMan;
	
	public GoalController(DatabaseManager in_dbMan, UserManager in_uMan)
	{
		dbMan = in_dbMan;
		uMan = in_uMan;
	}
	
	public boolean hasGoal()
	{
		if(uMan.getGoal() != null)
			return true;
		return false;
	}

	public boolean manageGoal(int duration, int goal)
	{
		uMan.setGoal(duration, goal);
		dbMan.setGoal(uMan.getUser().getUsername(), duration, goal);
		return true;
	}
	
	public int[] getGoal()
	{
		int[] ret = new int[2];
		ret[0] = uMan.getGoal().getDuration();
		ret[1] = uMan.getGoal().getCalorieGoal();
		return ret;
	}
}