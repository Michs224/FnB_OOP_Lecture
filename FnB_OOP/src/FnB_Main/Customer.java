// package FnB_Main;

// import java.sql.Connection;
// import java.sql.PreparedStatement;
// import java.sql.ResultSet;
// import java.sql.SQLException;

// //Kelas abstrak sebagai dasar untuk objek pelanggan

// public class Customer implements CustomerManagement {
//     private String name;
//     private String address;
//     private double balance;
    
//  // constructor, getter, setter methods
// 	public Customer() {
		
// 	}

// 	protected String getName() {
// 		return name;
// 	}

// 	protected void setName(String name) {
// 		this.name = name;
// 	}

// 	protected String getAddress() {
// 		return address;
// 	}

// 	protected void setAddress(String address) {
// 		this.address = address;
// 	}

// 	protected double getBalance() {
// 		return balance;
// 	}

// 	protected void setBalance(double balance) {
// 		this.balance = balance;
// 	}


//     @Override
//     public void addCustomer(String name, String address, double initialBalance) throws SQLException {
//         try (Connection connection = DatabaseConnection.getConnection()) {
//             String query = "INSERT INTO customer (name, address, balance) VALUES (?, ?, ?)";
//             try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
//                 preparedStatement.setString(1, name);
//                 preparedStatement.setString(2, address);
//                 preparedStatement.setDouble(3, initialBalance);

//                 preparedStatement.executeUpdate();
//             }
//         }
//     }

//     // @Override
//     // public void updateCustomer(String name, String address) throws SQLException {
//     //     try (Connection connection = DatabaseConnection.getConnection()) {
//     //         String query = "UPDATE customer SET address = ? WHERE name = ?";
//     //         try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
//     //             preparedStatement.setString(1, address);
//     //             preparedStatement.setString(2, name);

//     //             preparedStatement.executeUpdate();
//     //         }
//     //     }
//     // }

//     @Override
//     public void updateCustomer(String name, String newAddress) throws SQLException {
//         try (Connection connection = DatabaseConnection.getConnection()) {
//             String query = "UPDATE customer SET address = ? WHERE name = ?";
//             try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
//                 preparedStatement.setString(1, newAddress);
//                 preparedStatement.setString(2, name);

//                 int rowsAffected = preparedStatement.executeUpdate();

//                 if (rowsAffected > 0) {
//                     System.out.println("Data pelanggan berhasil diperbarui.");
//                 } else {
//                     System.out.println("Data pelanggan tidak ditemukan.");
//                 }
//             }
//         }
//     }

//     @Override
//     public void deleteCustomer(String name) throws SQLException {
//         try (Connection connection = DatabaseConnection.getConnection()) {
//             String query = "DELETE FROM customer WHERE name = ?";
//             try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
//                 preparedStatement.setString(1, name);

//                 preparedStatement.executeUpdate();
//             }
//         }
//     }
	
//     // @Override
//     public static Customer getCustomerByName(String name) throws SQLException {
//         try (Connection connection = DatabaseConnection.getConnection()) {
//             String query = "SELECT * FROM customer WHERE name = ?";
//             try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
//                 preparedStatement.setString(1, name);

//                 try (ResultSet resultSet = preparedStatement.executeQuery()) {
//                     if (resultSet.next()) {
//                         Customer customer = new Customer();
//                         customer.setName(resultSet.getString("name"));
//                         customer.setAddress(resultSet.getString("address"));
//                         customer.setBalance(resultSet.getDouble("balance"));
//                         return customer;
//                     }
//                 }
//             }
//         }
//         return null;
//     }

//     public void viewAllCustomers() throws SQLException {
//         try (Connection connection = DatabaseConnection.getConnection()) {
//             String query = "SELECT * FROM customer";
//             try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
//                 try (ResultSet resultSet = preparedStatement.executeQuery()) {
//                     System.out.println("List of all customers:");

//                     while (resultSet.next()) {
//                         System.out.println("Name: " + resultSet.getString("name"));
//                         System.out.println("Address: " + resultSet.getString("address"));
//                         System.out.println("Balance: " + resultSet.getDouble("balance"));
//                         System.out.println("-------------------------");
//                     }
//                 }
//             }
//         }
//     }

//     public void topUpBalance(String name, double amount) {
//         try (Connection connection = DatabaseConnection.getConnection()) {
//             String query = "UPDATE customer SET balance = balance + ? WHERE name = ?";
//             try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
//                 preparedStatement.setDouble(1, amount);
//                 preparedStatement.setString(2, name);

//                 int rowsAffected = preparedStatement.executeUpdate();

//                 if (rowsAffected > 0) {
//                     System.out.println("Saldo berhasil ditambahkan.");
//                 } else {
//                     System.out.println("Gagal menambahkan saldo.");
//                 }
//             }
//         } catch (SQLException e) {
//             System.out.println("Error topping up balance: " + e.getMessage());
//         }
//     }
// }


package FnB_Main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Utility.Utility;

// Kelas abstrak sebagai dasar untuk objek pelanggan

public class Customer implements CustomerManagement {
    private String name;
    private String address;
    private String phone;
    private double balance;

    // constructor, getter, setter methods
    public Customer() {

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
                        customer.setName(resultSet.getString("customer_name"));
                        customer.setAddress(resultSet.getString("address"));
                        customer.setPhone(resultSet.getString("phone")); // Assuming you have a setter for phone
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
