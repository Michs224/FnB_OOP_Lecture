package FnB_Main;

import java.sql.SQLException;
import java.util.Vector;

public abstract class Menu {
	
    private String itemName,itemID;
	private double price;
	
    // constructor, getter methods
//	public Menu() {
//		
//	}
	
    protected String getItemID() {
		return itemID;
	}


	protected void setItemID(String itemID) {
		this.itemID = itemID;
	}
	
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


	public abstract boolean printMenu() throws SQLException;
	public abstract Vector<Menu> getMenuItems() throws SQLException;

}
