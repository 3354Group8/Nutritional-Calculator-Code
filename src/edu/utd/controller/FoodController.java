package edu.utd.controller;

import java.util.*;
import edu.utd.model.DatabaseManager;
import edu.utd.model.UserManager;
import edu.utd.model.FoodItemManager;
import edu.utd.model.FoodItem;
import edu.utd.model.FoodEntry;

public class FoodController
{
	private DatabaseManager dbMan;
	private UserManager uMan;
	private FoodItemManager fMan;
	
	public FoodController(DatabaseManager dbMan, UserManager uMan, FoodItemManager fMan)
	{
		this.dbMan = dbMan;
		this.uMan = uMan;
		this.fMan = fMan;
	}
	
	public void addEntry(String name, int quantity, String date)
	{
		FoodEntry foodentry = new FoodEntry(fMan.find(name), quantity, date);
		uMan.addEntry(foodentry);
		dbMan.addEntry(uMan.getUser().getUsername(), foodentry);
	}
	
	public void deleteEntry(String str)
	{
    	String[] tokens = str.split(" | ");
    	
    	FoodItem item = fMan.find(tokens[0]);
    	int quantity = Integer.parseInt(tokens[2]);
    	String date = tokens[4];
    	
    	uMan.deleteEntry(new FoodEntry(item, quantity, date));
    	dbMan.deleteEntry(uMan.getUser().getUsername(), new FoodEntry(item, quantity, date));
	}
	
	public boolean exists(String name)
	{
		if(fMan.find(name) != null)
			return true;
		return false;
	}
	
	public boolean addItem(String name, int calories)
	{
		if(fMan.addItem(name,calories))
		{
			dbMan.addItem(name,calories);
			return true;
		}
		return false;
	}
	
	public ArrayList<String> getEntries(boolean pretty)
	{
		ArrayList<FoodEntry> feList = uMan.getEntries();
		ArrayList<String> strList = new ArrayList<String>();
		for(FoodEntry fe : feList)
		{
			String str = new String();
			if(pretty)
			{
				if(fe.getFooditem().getName().length() > 10)
					str = String.format("%1$-8s..|", fe.getFooditem().getName());
				else 
					str = String.format("%1$-10s|", fe.getFooditem().getName());
			} else {
				str = String.format("%s |", fe.getFooditem().getName());
			}
			
			if(pretty && fe.getQuantity() > 99)
				str = str + " >99 |";
			else
				str = str + String.format(" %1$03d |", fe.getQuantity());
			
			str = str + " " + fe.getDate() + " | " + (fe.getFooditem().getCalories() * fe.getQuantity());
			strList.add(str);
		}
		
		return strList;
	}
	
	public ArrayList<String> getItems()
	{
		ArrayList<FoodItem> fiList = fMan.getItems();
		ArrayList<String> strList = new ArrayList<String>();
		for(FoodItem fi : fiList)
		{
			String str = new String();
			str = fi.getName() + ": " + fi.getCalories() + " Calories";
			strList.add(str);
		}
		
		return strList;
	}
}
