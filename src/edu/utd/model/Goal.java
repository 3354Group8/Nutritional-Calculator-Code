package edu.utd.model;

import java.sql.Date;

public class Goal {

	private String title;
	private String goalId;
	private Date goalBeginDate;
	private Date goalFinishDate;
	private int goalBeginWeight;
	
	private int days;
	private int goalFinishWeight;
	private int goalDailyCalorie;
	

	public Goal(int in_goalFinishWeight, int in_days)
	{
		goalFinishWeight = in_goalFinishWeight;
		days = in_days;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}	
	public String getGoalId() {
		return goalId;
	}
	public void setGoalId(String goalId) {
		this.goalId = goalId;
	}
	public Date getGoalBeginDate() {
		return goalBeginDate;
	}
	public void setGoalBeginDate(Date goalBeginDate) {
		this.goalBeginDate = goalBeginDate;
	}
	public Date getGoalFinishDate() {
		return goalFinishDate;
	}
	public void setGoalFinishDate(Date goalFinishDate) {
		this.goalFinishDate = goalFinishDate;
	}
	public int getGoalBeginWeight() {
		return goalBeginWeight;
	}
	public void setGoalBeginWeight(int goalBeginWeight) {
		this.goalBeginWeight = goalBeginWeight;
	}
	public int getGoalFinishWeight() {
		return goalFinishWeight;
	}
	public void setGoalFinishWeight(int goalFinishWeight) {
		this.goalFinishWeight = goalFinishWeight;
	}
	public int getGoalDailyCalorie() {
		return goalDailyCalorie;
	}
	public void setGoalDailyCalorie(int goalDailyCalorie) {
		this.goalDailyCalorie = goalDailyCalorie;
	}
}
