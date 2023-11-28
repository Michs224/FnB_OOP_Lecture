package FnB_Main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

//Kelas abstrak sebagai dasar untuk objek pelanggan

class CustomerData{// kelas untuk menyimpan data customer
    private String id;
    private String name;
    private String address;
    private double balance;

    public CustomerData(String id,String name, String address,double balance){
        this.id = id;
        this.name = name;
        this.address = address;
        this.balance = balance;
    }

    public String getId(){
        return id;
    }

    public void setId(String id){
            this.id = id;
    }

    public String getName() {
	    return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getBalance() {
        return balance;
    }
        
    public void setBalance(double balance) {
        this.balance = balance;
    }
}

public class Customer implements CustomerManagement {//kelas customer query ke database

 // constructor, getter, setter methods
	
    @Override
    public CustomerData queryCustomer(String name){
        try {
            Connection connection = DatabaseConnection.getConnection();
            String query = "SELECT * FROM customer WHERE name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);

            ResultSet result = preparedStatement.executeQuery();
            String customer_id ="",customer_name = "",address = "";
            double balance = 0;

            while(result.next()){
                    
                customer_id = result.getString("customer_id");
                customer_name = result.getString("customer_name");
                address = result.getString("address");
                balance = result.getDouble("balance");

            }
            //hasil query disimpan ke dalam object customerData
            CustomerData queryResult = new CustomerData(customer_id,customer_name,address,balance);

            return queryResult;
        }
        catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public void addCustomer(String name, String address, double initialBalance) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "INSERT INTO customer (name, address, balance) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, address);
                preparedStatement.setDouble(3, initialBalance);

                preparedStatement.executeUpdate();

            }
        }
    }

    @Override
    public void updateCustomer(String column, String value) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "UPDATE customer SET ? = ? WHERE customer_id IN (SELECT customer_id FROM customer WHERE ? = ?);";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, column);
                preparedStatement.setString(2, value);
                preparedStatement.setString(3, column);
                preparedStatement.setString(4, value);

                preparedStatement.executeUpdate();

            }
        }
    }

    @Override
    public void deleteCustomer(String name) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "DELETE FROM customer WHERE name = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, name);

                preparedStatement.executeUpdate();
            }
        }
    }
	

}
