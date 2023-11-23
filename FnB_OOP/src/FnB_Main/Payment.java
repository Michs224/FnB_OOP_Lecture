package FnB_Main;

import java.sql.SQLException;

//Interface untuk manajemen pembayaran

public interface Payment {
	void makePayment(Customer customer, double amount) throws SQLException;
}
