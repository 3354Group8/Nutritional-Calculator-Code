package view;
import java.util.Scanner;

import edu.utd.controller.*;

public class UserView 
{
	private UserController userCon;
	private FoodController foodCon;
	private GoalController goalCon;
	private Scanner in;
	
	UserView(UserController in_userCon, 
		FoodController in_foodCon, 
		GoalController in_goalCon)
	{
		userCon = in_userCon;
		foodCon = in_foodCon;
		goalCon = in_goalCon;
		in = new Scanner(System.in);
	}
	
	State run()
	{
		String userViewText = 
				  "================================= \n"
				+ "= Nutritional Calculator        = \n"
				+ "================================= \n"
				+ "= A - Add Entry                 = \n"
				+ "= B - Delete Entry              = \n"
				+ "= C - " + (goalCon.hasGoal() ? "Edit Goal" : "Add Goal ") + "                 = \n"
				+ "= D - Save Current              = \n"
				+ "= E - Load Old                  = \n"
				+ "= F - Logout                    = \n"
				+ "= G - Exit                      = \n"
				+ "================================= \n"
				+ "  ? - ";
			System.out.print(userViewText);
			String input = in.nextLine();
			
			switch(input)
			{
			case "A":	return addEntry();
			case "B":	return deleteEntry();
			case "C":	return editGoal();
			case "D":	return save();
			case "E":	return load();
			case "F":	return logout();
			case "G":	return State.EXIT;
			default: 	System.out.println("\n Invalid Input"); 
						return State.USER;
			}
	}

	private State view()
	{
		userCon.view();
		return State.USER;
	}
	
	private State addEntry()
	{
		userCon.addEntry();
		System.out.println("Entries have been added");
		return State.USER;
	}
	
	private State deleteEntry()
	{
		userCon.deleteEntry();
		System.out.println("Entries have been deleted");
		return State.USER;
	}

	private State editGoal()
	{
		String input1, input2;
		int int1, int2;
		do {
			int1 = -1;
			String loginText = ""
					+ "================================= \n"
					+ "= Duration in days:             = \n"
					+ "= Leave blank to return to menu = \n"
					+ "================================= \n"
					+ "  ? - ";
			System.out.print(loginText);
			
			input1 = in.nextLine();
			try
			{
				int1 = Integer.parseInt(input1);
			} catch(Exception e) {}
		} while(int1 != -1);

		if(input1.length() == 0)
			return State.USER;
		do {
			int2 = -1;
			String loginText = ""
					+ "================================= \n"
					+ "= Calorie Count:             = \n"
					+ "= Leave blank to return to menu = \n"
					+ "================================= \n"
					+ "  ? - ";
			System.out.print(loginText);
			
			input2 = in.nextLine();
			try
			{
				int2 = Integer.parseInt(input1);
			} catch(Exception e) {}
		} while(int2 != -1);
		
		if(input2.length() == 0)
			return State.USER;
		goalCon.editGoal(int1, int2);
		return State.USER;
	}
		
	private State save()
	{
		userCon.saveUser();
		return State.USER;
	}
	
	private State load()
	{
		userCon.loadUser();
		return State.USER;
	}
	
	private State logout()
	{
		return State.LOGIN;
	}
}
