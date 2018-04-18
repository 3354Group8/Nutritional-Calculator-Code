package edu.utd.model;

public class FoodEntry 
{
	private FoodItem fooditem;
	private int quantity;
	private String date;
	
	public FoodEntry(FoodItem fooditem, int quantity, String date)
	{
		this.fooditem = fooditem;
		this.quantity = quantity;
		this.date = date;
	}

	public FoodItem getFooditem() {
		return fooditem;
	}

	public int getQuantity() {
		return quantity;
	}

	public String getDate() {
		return date;
	}
	
	@Override
	public boolean equals(Object obj) {
	    if (obj == null)
	        return false;
	    if (!(obj instanceof FoodEntry))
	        return false;
	    FoodEntry other = (FoodEntry) obj;
	    if(fooditem.getName().equals(other.getFooditem().getName()) &&
	    		quantity == other.getQuantity() &&
	    		date.equals(other.getDate()))
	    	return true;
	    return false;
	}
}
