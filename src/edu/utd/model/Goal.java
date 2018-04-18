package edu.utd.model;

public class Goal {
	private int duration;
	private int caloriegoal;

	public Goal(int duration, int caloriegoal)
	{
		this.duration = duration;
		this.caloriegoal = caloriegoal;
	}

	public int getDuration() 
	{
		return duration;
	}
	public int getCalorieGoal() 
	{
		return caloriegoal;
	}
}
