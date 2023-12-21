package FnB_Main;

import java.sql.SQLException;
//import java.util.ArrayList;
import java.util.Vector;

//Interface untuk manajemen data pemesanan

public interface OrderManagement {
	public void placeOrder(Customer customer, Vector<Menu> orderedItems, Cashier cashier) throws SQLException;
	
}
