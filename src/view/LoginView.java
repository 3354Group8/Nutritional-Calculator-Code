package view;

import java.util.Scanner;

import edu.utd.controller.UserController;

public class LoginView
{
	private UserController userCon;
	private Scanner in;
	
	LoginView(UserController in_userCon)
	{
		userCon = in_userCon;
		in = new Scanner(System.in);
	}
	
	State run()
	{
		String loginViewText = 
				  "================================= \n"
				+ "= Nutritional Calculator        = \n"
				+ "================================= \n"
				+ "= Enter any option letter below = \n"
				+ "= A - Login                     = \n"
				+ "= B - Signup                    = \n"
				+ "= C - Exit                      = \n"
				+ "================================= \n"
				+ "  ? - ";
			System.out.print(loginViewText);
			String input = in.nextLine();
			
			switch(input)
			{
			case "A": 	return login();
			case "B":	return signup();
			case "C":	return State.EXIT;
			default: 	System.out.println("\n Invalid Input"); 
						return State.LOGIN;
			}
	}
	
	private State login()
	{	
		String username, password;
		do {
			String loginText = ""
					+ "================================= \n"
					+ "= Username:                     = \n"
					+ "= Leave blank to return to menu = \n"
					+ "================================= \n"
					+ "  ? - ";
			System.out.print(loginText);
			
			username = in.nextLine();
			
			if(containWhitespace(username))
				System.out.println("No whitespace/punctuation allowed in entry");
		} while(containWhitespace(username));
		
		if(username.length() == 0)
			return State.LOGIN;
		
		do {
			String loginText = ""
					+ "================================= \n"
					+ "= Password:                     = \n"
					+ "= Leave blank to return to menu = \n"
					+ "================================= \n"
					+ "  ? - ";
			System.out.print(loginText);
			
			password = in.nextLine();

			if(containWhitespace(password))
				System.out.println("No whitespace/punctuation allowed in entry");
		} while(containWhitespace(password));
		
		if(password.length() == 0)
			return State.LOGIN;
		
		if(userCon.loginUser(username, password) == -1)
		{
			System.out.println("No users match those credentials");
			return State.LOGIN;
		}
		return State.USER;
	}
	
	private State signup()
	{
		String username, password;
		do {
			String loginText = ""
					+ "================================= \n"
					+ "= New username:                 = \n"
					+ "= Leave blank to return to menu = \n"
					+ "================================= \n"
					+ "  ? - ";
			System.out.print(loginText);
			
			username = in.nextLine();
			
			if(containWhitespace(username))
				System.out.println("No whitespace/punctuation allowed in entry");
		} while(containWhitespace(username));
		
		if(username.length() == 0)
			return State.LOGIN;
		
		do {
			String loginText = ""
					+ "================================= \n"
					+ "= New password:                 = \n"
					+ "= Leave blank to return to menu = \n"
					+ "================================= \n"
					+ "  ? - ";
			System.out.print(loginText);
		
			password = in.nextLine();

			if(containWhitespace(password))
				System.out.println("No whitespace/punctuation allowed in entry");
		} while(containWhitespace(password));
		
		if(password.length() == 0)
			return State.LOGIN;
		
		do {
			System.out.println("=================================");
			System.out.println("  Username: " + username);
			System.out.println("  Password: " + password);
			System.out.println("  Make an account with these credentials (Y/N)?");
			System.out.println("=================================");
			System.out.print(  "  ? - ");
			
			String choice = in.nextLine();
			switch(choice)
			{
			case "Y":	if(!userCon.signupUser(username, password))
							System.out.println("That username already exists");
						else
							System.out.println("Account created successfully");
						return State.LOGIN;
			case "N":	return State.LOGIN;
			default:	System.out.println("Please enter Y for Yes or N for No.");
			}
		} while(true);
	}
	
	private boolean containWhitespace(String str)
	{
		char [] punctuation = {'.' , ',' , ';' , ':', '?' , '!' , '"' , '\'' , ')' , '('};
	    if (str != null && str.length() > 0)
	    	for (int i = 0; i < str.length(); i++) 
	    	{
	    		if (Character.isWhitespace(str.charAt(i))) 
	    			return true;
	    		for (int j = 0; j < punctuation.length; j++)
	    			if(str.charAt(i) == punctuation[j])
	    				return true;
	    	}
	    return false;
	}
}
