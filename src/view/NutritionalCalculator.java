package view;

import edu.utd.controller.*;
import edu.utd.model.DatabaseManager;
import edu.utd.model.FoodItemManager;
import edu.utd.model.UserManager;

enum State {
	LOGIN, USER, EXIT
}

public class NutritionalCalculator
{
	private FoodController foodCon;
	private UserController userCon;
	private GoalController goalCon;
	private DatabaseManager dbMan;
	private UserManager uMan;
	private FoodItemManager fMan; 
	
	private State state;
	
	NutritionalCalculator()
	{
		dbMan = new DatabaseManager();
		uMan = new UserManager();
		fMan = new FoodItemManager(dbMan);
		foodCon = new FoodController(dbMan, uMan);
		userCon = new UserController(dbMan, uMan, fMan);
		goalCon = new GoalController(dbMan, uMan);
		state = State.LOGIN;
	}
	
	void run() 
	{
		while(state != State.EXIT) 
		{
			switch(state) {
				case LOGIN: LoginView loginView = new LoginView(userCon);
							state = loginView.run(); 
							break;
				case USER: 	UserView userView = new UserView(userCon, foodCon, goalCon);
							state = userView.run(); 
							break;
				default: 	break;
			}
		}
	}
	
	public static void main(String[] args) 
	{
		NutritionalCalculator system = new NutritionalCalculator();
		system.run();
	}
}