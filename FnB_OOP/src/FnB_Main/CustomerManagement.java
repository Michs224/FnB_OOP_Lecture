package FnB_Main;

import java.sql.SQLException;

//Interface untuk manajemen data pelanggan

public interface CustomerManagement {
	
    void addCustomer(String name, String address, double initialBalance) throws SQLException;
    void updateCustomer(String name, String address) throws SQLException;
    void deleteCustomer(String name) throws SQLException;
    Customer getCustomerByName(String name) throws SQLException;
    void topUpBalance(String name, double amount) throws SQLException;
    void viewAllCustomers() throws SQLException;
}
