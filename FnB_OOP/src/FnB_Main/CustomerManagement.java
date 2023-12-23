package FnB_Main;

import java.sql.SQLException;

//Interface untuk manajemen data pelanggan

public interface CustomerManagement {
	
	void addCustomer(String name, String address, double initialBalance, String phone) throws SQLException;
    public void deleteCustomer(String phoneToDelete) throws SQLException;
    public Customer getCustomerByPhone(String phone) throws SQLException;
    public void topUpBalance(String phone, double amount) throws SQLException;
    public void viewAllCustomers() throws SQLException;
    public void updateCustomerName(String phone, String newName) throws SQLException;
    public void updateCustomerAddress(String phone, String newAddress) throws SQLException;
    public void updateCustomerBalance(String phone, double newBalance) throws SQLException;
}