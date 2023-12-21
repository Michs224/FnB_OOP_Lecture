package FnB_Main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Utility.Utility;

// Kelas abstrak sebagai dasar untuk objek pelanggan

public class Customer implements CustomerManagement {
	private int id;
    private String name;
    private String address;
    private String phone;
    private double balance;

    // constructor, getter, setter methods
    public Customer() {

    }
    

    protected int getId() {
		return id;
	}


	protected void setId(int id) {
		this.id = id;
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
   
    protected String getPhone() {
		return phone;
	}

	protected void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public void addCustomer(String name, String address, double initialBalance, String phone) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "INSERT INTO customers (customer_name, address, balance, phone) VALUES (?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, address);
                preparedStatement.setDouble(3, initialBalance);
                preparedStatement.setString(4, phone);

                preparedStatement.executeUpdate();
            }
        }
    }

    @Override
    public void deleteCustomer(String phoneToDelete) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "DELETE FROM customers WHERE phone = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, phoneToDelete);

                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected == 0) {
                    throw new SQLException("Deletion failed, no rows affected.");
                }
            }
        }
    }

    
    @Override
    public Customer getCustomerByPhone(String phone) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM customers WHERE phone = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, phone);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        Customer customer = new Customer();
                        customer.setId(resultSet.getInt("customer_id"));  // Misalkan nama kolomnya adalah customer_id
                        customer.setName(resultSet.getString("customer_name"));
                        customer.setAddress(resultSet.getString("address"));
                        customer.setPhone(resultSet.getString("phone"));
                        customer.setBalance(resultSet.getDouble("balance"));
                        return customer;
                    }
                }
            }
        }
        return null;
    }


    @Override
    public void updateCustomerName(String phone, String newName) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "UPDATE customers SET customer_name = ? WHERE phone = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, newName);
                preparedStatement.setString(2, phone);

                preparedStatement.executeUpdate();
            }
        }
    }

    @Override
    public void updateCustomerAddress(String phone, String newAddress) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "UPDATE customers SET address = ? WHERE phone = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, newAddress);
                preparedStatement.setString(2, phone);

                preparedStatement.executeUpdate();
            }
        }
    }
   
    
    @Override
    public void updateCustomerBalance(String phone, double newBalance) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "UPDATE customers SET balance = ? WHERE phone = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setDouble(1, newBalance);
                preparedStatement.setString(2, phone);

                preparedStatement.executeUpdate();
            }
        }
    }

    @Override
    public void viewAllCustomers() throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT customer_name, phone, address, balance FROM customers";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (!resultSet.isBeforeFirst()) {
                        System.out.println("No customers found.");
                        return;
                    }
                    
                   
                    System.out.println("+--------------------------------+--------------+--------------------------------+-----------+");
                    System.out.printf("| %-30s | %-12s | %-30s | %-9s |\n", "Customer Name", "Phone", "Address", "Balance");
                    System.out.println("+--------------------------------+--------------+--------------------------------+-----------+");
                    
                    
                    while (resultSet.next()) {
                        System.out.printf("| %-30s | %-12s | %-30s | %-9.2f |\n",
                            resultSet.getString("customer_name"),
                            resultSet.getString("phone"),
                            resultSet.getString("address"),
                            resultSet.getDouble("balance"));
                    }
                    System.out.println("+--------------------------------+--------------+--------------------------------+-----------+");
                }
            }
        }
    }


    @Override
    public void topUpBalance(String name, double amount) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "UPDATE customers SET balance = balance + ? WHERE name = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setDouble(1, amount);
                preparedStatement.setString(2, name);

                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Saldo berhasil ditambahkan.");
                } else {
                    System.out.println("Gagal menambahkan saldo.");
                }
            }
        }
    }
}