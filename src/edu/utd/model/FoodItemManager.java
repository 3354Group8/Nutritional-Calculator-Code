package edu.utd.model;
import java.util.ArrayList;

public class FoodItemManager {

	private ArrayList<FoodItem> fooditems;
	
	public FoodItemManager(DatabaseManager dbMan)
	{
		fooditems = dbMan.loadItems();
	}
	
	public boolean addItem(String name, int calories)
	{
		for(FoodItem f : fooditems)
			if(f.getName().equals(name))
				return false;

		fooditems.add(new FoodItem(name,calories));
		return true;
	}
	
	public FoodItem find(String str)
	{
		for(FoodItem item : fooditems)
			if(item.getName().equals(str))
				return item;
		
		return null;
	}
	
	public ArrayList<FoodItem> getItems()
	{ 
		return fooditems;
	}
	
}
