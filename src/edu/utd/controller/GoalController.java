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
		return true;
	}

	public boolean editGoal(int duration, int goal)
	{
		uMan.setGoal(duration, goal);
		return true;
	}
}