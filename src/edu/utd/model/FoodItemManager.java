package edu.utd.model;
import java.util.ArrayList;

public class FoodItemManager {

	private ArrayList<FoodItem> fooditems;
	
	public FoodItemManager(DatabaseManager dbMan)
	{
		fooditems = dbMan.loadItems();
	}
	
	public FoodItem find(String str)
	{
		for(FoodItem item : fooditems)
		{
			if(str.equals(item.getName()));
				return item;
		}
		return null;
	}
}
