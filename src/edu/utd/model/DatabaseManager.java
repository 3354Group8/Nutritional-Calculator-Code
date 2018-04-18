package edu.utd.model;
import java.io.*;
import java.util.*;

public class DatabaseManager 
{
	public DatabaseManager() {}
	
	public boolean addItem(String name, int calories)
	{
		try {
			BufferedReader file = new BufferedReader(new FileReader("FoodItems"));
			StringBuffer inputBuffer = new StringBuffer();
			String line;
			
	        while ((line = file.readLine()) != null) {
	        	inputBuffer.append(line);
	        	inputBuffer.append('\n');
	        }
	        inputBuffer.append(name + " " + calories);
	        file.close();

	        FileOutputStream fileOut = new FileOutputStream("FoodItems");
	        fileOut.write(inputBuffer.toString().getBytes());
	        fileOut.close();
			
			return true;
		} catch(Exception e) {
			return false;
		}
	}
	
	public boolean addEntry(String username, FoodEntry foodentry)
	{
		try {
			BufferedReader file = new BufferedReader(new FileReader(username));
			StringBuffer inputBuffer = new StringBuffer();
			String line;
			
	        while ((line = file.readLine()) != null) {
	        	inputBuffer.append(line);
	        	inputBuffer.append('\n');
	        }
	        inputBuffer.append(foodentry.getFooditem().getName() + " " + foodentry.getQuantity() + " " + foodentry.getDate());
	        file.close();

	        FileOutputStream fileOut = new FileOutputStream(username);
	        fileOut.write(inputBuffer.toString().getBytes());
	        fileOut.close();
			
			return true;
		} catch(Exception e) {
			return false;
		}
	}
	
	public boolean deleteEntry(String username, FoodEntry foodentry)
	{
		try {
			BufferedReader file = new BufferedReader(new FileReader(username));
			StringBuffer inputBuffer = new StringBuffer();
			String line;
			boolean deleted = false;
			
	        while ((line = file.readLine()) != null) 
	        {
		    	String[] tokens = line.split(" ");
	        	if(deleted == false &&
	        		tokens[0].equals(foodentry.getFooditem().getName()) &&
	        		tokens[1].equals(foodentry.getQuantity() + "") &&
	        		tokens[2].equals(foodentry.getDate()))
	        	{
	        		deleted = true;
	        		continue;
	        	}
	        	
	        	inputBuffer.append(line);
	        	inputBuffer.append('\n');
	        }
	        file.close();

	        FileOutputStream fileOut = new FileOutputStream(username);
	        fileOut.write(inputBuffer.toString().getBytes());
	        fileOut.close();
			
			return true;
		} catch(Exception e) {
			return false;
		}
	}
	
	public ArrayList<FoodItem> loadItems()
	{
		try {
		    File inFile = new File("FoodItems");
	
		    Scanner sc = new Scanner (inFile);
		    ArrayList<FoodItem> foodItems = new ArrayList<FoodItem>();
		    
		    while(sc.hasNextLine())
		    {
		    	String line = sc.nextLine();
			    String[] itemTokens = line.split(" ");
			    
			    int j = 1;
			    for(int i = 0; i < itemTokens.length; i+=2)
			    {
			    	FoodItem item = new FoodItem(itemTokens[i], Integer.parseInt(itemTokens[j]));
			    	foodItems.add(item);
			    	//System.out.println(itemTokens[i] + " " + itemTokens[j]);
			    	j+=2;
			    }
		    }
		    sc.close();
		    
		    return foodItems;
		} catch(IOException e) {
			return null;
		}
	}

	public boolean setGoal(String username, int duration, int caloriegoal)
	{
		try {
			BufferedReader file = new BufferedReader(new FileReader(username));
			StringBuffer inputBuffer = new StringBuffer();
			String line;
			int linecount = 0;
			
	        while ((line = file.readLine()) != null || linecount < 2) {
	        	if(linecount == 1)
	        	{
	        		inputBuffer.append(Integer.toString(duration) + " " + Integer.toString(caloriegoal) + "\n");
	        	} else {
	        		inputBuffer.append(line);
	        		inputBuffer.append('\n');
	        	}
	            linecount++;
	        }
	        file.close();

	        FileOutputStream fileOut = new FileOutputStream(username);
	        fileOut.write(inputBuffer.toString().getBytes());
	        fileOut.close();
			
			return true;
		} catch(Exception e) {
			return false;
		}
	}
	
	public int loadUser(String username, String password, UserManager uMan, FoodItemManager fMan)
	{
		try {
		    File inFile = new File(username);
		    Scanner sc = new Scanner (inFile);
		    
		    if(!password.equals(sc.nextLine()))
		    {
		    	sc.close();
		    	return -1;
		    }
		    
		    uMan.setUser(new User(username, password));
		    
		    String line = sc.nextLine();
		    if(line.length() > 2)
		    {
		    	String[] goalTokens = line.split(" ");
		    	uMan.setGoal(new Goal(Integer.parseInt(goalTokens[0]), Integer.parseInt(goalTokens[1])));
		    }

	    	ArrayList<FoodEntry> foodEntries = new ArrayList<FoodEntry>();
		    while(sc.hasNextLine())
		    {
		    	line = sc.nextLine();
		    	String[] entryTokens = line.split(" ");

		    	FoodItem item = fMan.find(entryTokens[0]);
		    	FoodEntry entry = new FoodEntry(item, Integer.parseInt(entryTokens[1]), entryTokens[2]);
		    	foodEntries.add(entry);
		    }
	    	uMan.setEntries(foodEntries);
	    	
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
	 	     pWriter.println(password + "\n");
	 	     pWriter.close();
	 	     
	 	     return fvar;
	     	} catch (IOException e) {
	     		System.out.println("Exception Occurred:");
	 	        e.printStackTrace();
	     	}
		return false;
	}
	
	
}
