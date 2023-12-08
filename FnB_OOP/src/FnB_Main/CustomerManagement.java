package FnB_Main;

import java.sql.SQLException;

//Interface untuk manajemen data pelanggan

public interface CustomerManagement {
	
    void addCustomer(String name, String address, double initialBalance) throws SQLException;
    void updateCustomer(String name, String address) throws SQLException;
    void deleteCustomer(String name) throws SQLException;
    static Customer getCustomerByName(String name) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCustomerByName'");
    }
    void topUpBalance(String name, double amount) throws SQLException;
    // void viewAllCustomers();
}
