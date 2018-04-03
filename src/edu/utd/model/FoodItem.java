package edu.utd.model;

public class FoodItem 
{
	private String name;
	private int calories;
	
	public FoodItem(String str, int num1)
	{
		name=str;
		calories=num1;
	}
	
	public String getName() 
	{
		return name;
	}
	
	public void setName(String str) 
	{
		name=str;
	}
	
	public int getCalories() 
	{
		return calories;
	}
	
	public void setCalories(int num) 
	{
		calories=num;
	}
	
}
