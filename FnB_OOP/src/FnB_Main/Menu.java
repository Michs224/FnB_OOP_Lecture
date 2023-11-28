package FnB_Main;

import java.sql.SQLException;

public abstract class Menu {
	
    private String itemName;
    private double price;
	
    // constructor, getter methods
//	public Menu() {
//		
//	}
	
    protected String getItemName() {
		return itemName;
	}


	protected void setItemName(String itemName) {
		this.itemName = itemName;
	}


	protected double getPrice() {
		return price;
	}


	protected void setPrice(double price) {
		this.price = price;
	}


	public abstract void saveToDatabase() throws SQLException;

}
