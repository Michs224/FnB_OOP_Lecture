package FnB_Main;

import java.sql.SQLException;

//Interface untuk manajemen data pelanggan

public interface CustomerManagement {
	
	void addCustomer(String name, String address, double initialBalance, String phone) throws SQLException;
    void updateCustomer(String name, String address) throws SQLException;
    void deleteCustomer(String name) throws SQLException;
    public Customer getCustomerByPhone(String phone) throws SQLException;
    void topUpBalance(String name, double amount) throws SQLException;
    void viewAllCustomers() throws SQLException;
}
