package edu.utd.model;
import java.io.*;
import java.util.*;

public class DatabaseManager 
{
	public DatabaseManager() {}
	
	public ArrayList<FoodItem> loadItems()
	{
		try {
		    File inFile = new File("FoodItems");
	
		    Scanner sc = new Scanner (inFile);
		    
		    String line = sc.nextLine();
		    String[] itemTokens = line.split(" ");
		    
		    ArrayList<FoodItem> foodItems = new ArrayList<FoodItem>();
		    int j = 1;
		    for(int i = 0; i < itemTokens.length; i+=2)
		    {
		    	FoodItem item = new FoodItem(itemTokens[i], Integer.parseInt(itemTokens[j]));
		    	foodItems.add(item);
		    	//System.out.println(itemTokens[i] + " " + itemTokens[j]);
		    	j+=2;
		    }
		    sc.close();
		    
		    return foodItems;
		} catch(IOException e) {
			return null;
		}
		
	}

	public int loadUser(String username, String password, UserManager uMan, FoodItemManager fMan)
	{
		try {
		    File inFile = new File(username);
	
		    Scanner sc = new Scanner (inFile);
		    if(!password.equals(sc.nextLine()))
		    	return -1;
		    
		    uMan.setUser(new User(username, password));
		    String line = sc.nextLine();
		    if(line.length() > 2)
		    {
		    String[] goalTokens = line.split(" ");
		    uMan.setGoal(new Goal(Integer.parseInt(goalTokens[0]), Integer.parseInt(goalTokens[1])));
		    }
		    if(sc.hasNextLine())
		    {
		    line = sc.nextLine();
		    String[] entryTokens = line.split(" ");
		    ArrayList<FoodEntry> foodEntries = new ArrayList<FoodEntry>();
		    int i; int j = 1;
		    for(i = 0; i < entryTokens.length; i+=2)
		    {
		    	FoodItem item = fMan.find(entryTokens[i]);
		    	FoodEntry entry = new FoodEntry(item, Integer.parseInt(entryTokens[j]));
		    	foodEntries.add(entry);
		    	j+=2;
		    }
		    
		    uMan.setEntries(foodEntries);
		    }
		    sc.close();
		    
		    return 1;
		} catch(IOException e) {
			return -1;
		}
	}

	public boolean createUser(String username, String password)
	{
		try {
	 	     File file = new File(username);
	 	     
	 	     boolean fvar = file.createNewFile();
	 	     if(!fvar)
	 	    	 return fvar;
	 	     
	 	     FileWriter fWriter = new FileWriter(file);
	 	     PrintWriter pWriter = new PrintWriter(fWriter);
	 	     pWriter.println(password + "\n\n");
	 	     pWriter.close();
	 	     
	 	     return fvar;
	     	} catch (IOException e) {
	     		System.out.println("Exception Occurred:");
	 	        e.printStackTrace();
	     	}
		return false;
	}
	
	
}
