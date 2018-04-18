package view;

import java.text.*;
import java.util.*;
import edu.utd.controller.*;

public class UserView 
{
	private FoodController foodCon;
	private GoalController goalCon;
	private Scanner in;
	
	UserView(
		FoodController in_foodCon, 
		GoalController in_goalCon)
	{
		foodCon = in_foodCon;
		goalCon = in_goalCon;
		in = new Scanner(System.in);
	}
	
	State run()
	{
		String userViewText1 = 
				  "============================================== \n"
				+ "= Nutritional Calculator                     = \n"
				+ "============================================== \n"
				+ "   Item   |  #  |    Date    | Total Calories \n";
		String userViewText2 = 
				  "================================= \n"
				+ "= A - Add Item                  = \n"
				+ "= B - Add Entry                 = \n"
				+ "= C - Delete Entry              = \n"
				+ "= D - " + (goalCon.hasGoal() ? "Edit Goal" : "Add Goal ") + "                 = \n"
				+ "= E - Logout                    = \n"
				+ "= F - Exit                      = \n"
				+ "================================= \n"
				+ "  ? - ";
			System.out.print(userViewText1);
			
			ArrayList<String> foodents = foodCon.getEntries(true);

	    	String[] todayparts = new SimpleDateFormat("yyyy/MM/dd").format(new Date()).split("/");
	    	int todaycount = Integer.parseInt(todayparts[0])*365 + Integer.parseInt(todayparts[1])*31 + Integer.parseInt(todayparts[2]);
	    	
			int calories = 0;
			for(String s : foodents)
			{
				System.out.println(s);
		    	String[] tokens = s.split("/");
		    	String[] dateparts = {
		    			tokens[0].substring(tokens[0].length()-4, tokens[0].length()),
		    			tokens[1],
		    			tokens[2].substring(0, 2)};
		    	
		    	int datecount = Integer.parseInt(dateparts[0])*365 + Integer.parseInt(dateparts[1])*31 + Integer.parseInt(dateparts[2]);
		    	
		    	if(todaycount - goalCon.getGoal()[0] <= datecount)
		    	{
		    		String[] mtokens = tokens[2].split(" ");
		    		calories += Integer.parseInt(mtokens[mtokens.length-1]);
		    	}
			}
			System.out.println("==============================================");
			System.out.println("Current Goal: " + goalCon.getGoal()[1] + " Calories in " + goalCon.getGoal()[0] + " days.");
			System.out.println("Calories within past " + goalCon.getGoal()[0] + " days: " + calories);
			System.out.println("==============================================");
			System.out.print(userViewText2);
			
			String input = in.nextLine();
			
			switch(input)
			{
			case "A":	return addItem();
			case "B":	return addEntry();
			case "C":	return deleteEntry();
			case "D":	return manageGoal();
			case "E":	return logout();
			case "F":	return State.EXIT;
			default: 	System.out.println("\n Invalid Input"); 
						return State.USER;
			}
	}
	
	private State addItem()
	{
		String input, name;
		int calories = -1;
		do {
			String text = ""
					+ "================================= \n"
					+ "= Name of item:                 = \n"
					+ "= Leave blank to return to menu = \n"
					+ "================================= \n"
					+ "  ? - ";
			System.out.print(text);
			
			name = in.nextLine();
			if(name.length() == 0)
				return State.USER;
			
			if(containsPunctuation(name))
				System.out.println("No whitespace/punctuation allowed in entry");
		} while(containsPunctuation(name));

		do {
			String text = ""
					+ "================================= \n"
					+ "= How many calories:            = \n"
					+ "= Leave blank to return to menu = \n"
					+ "================================= \n"
					+ "  ? - ";
			System.out.print(text);
			
			input = in.nextLine();
			try
			{
				if(input.length() == 0)
					return State.USER;
				
				calories = Integer.parseInt(input);
				if(calories < 0) 
				{
					calories = -1;
					throw new Exception();
				}
				
			} catch(Exception e) {
				System.out.println("Only positive integers accepted.");
			}
		} while(calories == -1);
		
		if(!foodCon.addItem(name, calories))
			System.out.println("Item with that name already exists");
		return State.USER;
	}
	
	private State addEntry()
	{
		System.out.println("=================================");
		ArrayList<String> strList = foodCon.getItems();
		for(String s : strList)
			System.out.println(s);
		
		String input, name, date;
		int quantity = -1;
		do {
			String text = ""
					+ "================================= \n"
					+ "= Add which item:               = \n"	
					+ "= Leave blank to return to menu = \n"
					+ "================================= \n"
					+ "  ? - ";
			System.out.print(text);
			
			name = in.nextLine();
			if(name.length() == 0)
				return State.USER;
			
			if(!foodCon.exists(name))
				System.out.println("Item with that name not found");
		} while(!foodCon.exists(name));
		
		do {
			String text = ""
					+ "================================= \n"
					+ "= Enter quantity:               = \n"
					+ "= Leave blank to return to menu = \n"
					+ "================================= \n"
					+ "  ? - ";
			System.out.print(text);
			
			input = in.nextLine();
			try
			{
				if(input.length() == 0)
					return State.USER;
				
				quantity = Integer.parseInt(input);
				if(quantity < 0 || quantity > 100) 
				{
					quantity = -1;
					throw new Exception();
				}
				
			} catch(Exception e) {
				System.out.println("Only positive integers < 100 accepted.");
			}
		} while(quantity == -1);
	
		Date today = new Date();
		SimpleDateFormat ymdFormat = new SimpleDateFormat("yyyy/MM/dd");
		String strToday = ymdFormat.format(today);
		do {
			String text = ""
					+ "================================= \n"
					+ "= Enter date YYYY/MM/DD:        = \n"	
					+ "= Leave blank for " + strToday + "    =\n"
					+ "================================= \n"
					+ "  ? - ";
			System.out.print(text);
			
			date = in.nextLine();
			if(date.length() == 0)
				date = strToday;
			
			if(!validDate(date))
				System.out.println("Not a date of form YYYY/MM/DD");
		} while(!validDate(date));
		
		foodCon.addEntry(name, quantity, date);
		return State.USER;
	}
	
	private State deleteEntry()
	{
		System.out.println("=================================");
		String[] entries = foodCon.getEntries(false).toArray(new String[foodCon.getEntries(false).size()]);
		for(int i = 0; i < entries.length; i++)
		{
			entries[i] = (i+1) + "- " + entries[i];
			System.out.println(entries[i]);
		}
		
		String input;
		int index = -1;
		do {
			String text = ""
					+ "================================= \n"
					+ "= Delete which entry #:         = \n"
					+ "= Leave blank to return to menu = \n"
					+ "================================= \n"
					+ "  ? - ";
			System.out.print(text);
			
			input = in.nextLine();
			try
			{
				if(input.length() == 0)
					return State.USER;
				
				index = Integer.parseInt(input);
				if(index < 1 || index > entries.length) 
				{
					index = -1;
					throw new Exception();
				}
				
			} catch(Exception e) {
				System.out.println("Please enter the index # of the entry you wish to delete.");
			}
		} while(index == -1);
		
		foodCon.deleteEntry(entries[index-1].substring(3));
		
		return State.USER;
	}

	private State manageGoal()
	{
		String input;
		int duration = -1, caloriegoal = -1;
		do {
			String text = ""
					+ "================================= \n"
					+ "= Duration in days:             = \n"
					+ "= Leave blank to return to menu = \n"
					+ "================================= \n"
					+ "  ? - ";
			System.out.print(text);
			
			input = in.nextLine();
			try
			{
				if(input.length() == 0)
					return State.USER;
				
				duration = Integer.parseInt(input);
				if(duration < 0) 
				{
					duration = -1;
					throw new Exception();
				}
				
			} catch(Exception e) {
				System.out.println("Only positive integers accepted.");
			}
		} while(duration == -1);

		do {
			String text = ""
					+ "================================= \n"
					+ "= Calorie Count:                = \n"
					+ "= Leave blank to return to menu = \n"
					+ "================================= \n"
					+ "  ? - ";
			System.out.print(text);
			
			input = in.nextLine();
			try
			{
				if(input.length() == 0)
					return State.USER;
				
				caloriegoal = Integer.parseInt(input);
				if(caloriegoal < 0) 
				{
					caloriegoal = -1;
					throw new Exception();
				}
				
			} catch(Exception e) {
				System.out.println("Only positive integers accepted.");
			}
		} while(caloriegoal == -1);
		
		goalCon.manageGoal(duration, caloriegoal);
		return State.USER;
	}
	
	private State logout()
	{
		return State.LOGIN;
	}
	
	private boolean validDate(String str)
	{   
    	String[] tokens = str.split("/");
    	if(tokens.length != 3)
    		return false;
    	
    	int[] limits = {2018, 12, 31};
    	for(int i = 0; i < 3; i++)
    		try {
				int j = Integer.parseInt(tokens[i]);
				if(j < 0 || j > limits[i]) 
					throw new Exception();
    		} catch(Exception e) {
    			return false;
    		}
    	
    	return true;
	}
	
	private boolean containsPunctuation(String str)
	{
		char [] punctuation = {'.' , ',' , ';' , ':', '?' , '!' , '"' , '\'' , ')' , '('};
	    if (str != null && str.length() > 0)
	    	for (int i = 0; i < str.length(); i++) 
	    	{
	    		for (i = 0; i < punctuation.length; i++)
	    			if(str.charAt(i) == punctuation[i])
	    				return true;
	    	}
	    return false;
	}
}
