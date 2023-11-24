package FnB_Main;

import java.sql.SQLException;

//Interface untuk manajemen data pelanggan

public interface CustomerManagement {
	
    void addCustomer(String name, String address, double initialBalance) throws SQLException;
    void updateCustomer(String column,String value) throws SQLException;
    void deleteCustomer(String name) throws SQLException;
    
}
