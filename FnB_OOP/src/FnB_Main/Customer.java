package FnB_Main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

//Kelas abstrak sebagai dasar untuk objek pelanggan

public class Customer implements CustomerManagement {
    private String name;
    private String address;
    private double balance;
    
 // constructor, getter, setter methods
	public Customer() {
		//ini constructor cuma gimmick :P
	}

	protected String getName() {
		return name;
	}

	protected void setName(String name) {
		this.name = name;
	}

	protected String getAddress() {
		return address;
	}

	protected void setAddress(String address) {
		this.address = address;
	}

	protected double getBalance() {
		return balance;
	}

	protected void setBalance(double balance) {
		this.balance = balance;
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
    public void updateCustomer(String name, String column, String value) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "UPDATE customer SET ? = ? WHERE name = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, column);
                preparedStatement.setString(2, value);
                preparedStatement.setString(3, name);

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
