package edu.utd.model;

public class FoodEntry 
{
	private FoodItem foodItem;
	private int quantity;
	
	public FoodEntry(FoodItem in_foodItem, int in_quantity)
	{
		foodItem = in_foodItem;
		quantity = in_quantity;
	}
}
