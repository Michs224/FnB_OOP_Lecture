package FnB_Main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class Order implements OrderManagement, Payment {


	@Override
	public void placeOrder(Customer customer, Vector<Menu> orderedItems, Cashier cashier) throws SQLException {
	    try (Connection connection = DatabaseConnection.getConnection()) {
	        // Nambah entri ke tabel orders
	        String orderQuery = "INSERT INTO orders (customer_id, cashier_id, order_date, total_amount) VALUES (?, ?, NOW(), ?)";
	        double totalAmount = calculateTotalAmount(orderedItems);

	        try (PreparedStatement orderStmt = connection.prepareStatement(orderQuery, Statement.RETURN_GENERATED_KEYS)) {
	            orderStmt.setInt(1, customer.getId());
	            orderStmt.setInt(2, cashier.getId());
	            orderStmt.setDouble(3, totalAmount);
	            orderStmt.executeUpdate();

	            // Get order_id yang dihasilkan
	            try (ResultSet generatedKeys = orderStmt.getGeneratedKeys()) {
	                if (generatedKeys.next()) {
	                    int orderId = generatedKeys.getInt(1);

	                    // Insert atau update jumlah di tabel food_order_details dan beverage_order_details
	                    String foodDetailQuery = "INSERT INTO food_order_details (order_id, food_id, quantity) VALUES (?, ?, ?) ON DUPLICATE KEY UPDATE quantity = quantity + 1";
	                    String beverageDetailQuery = "INSERT INTO beverage_order_details (order_id, beverage_id, quantity) VALUES (?, ?, ?) ON DUPLICATE KEY UPDATE quantity = quantity + 1";

	                    try (PreparedStatement foodStmt = connection.prepareStatement(foodDetailQuery);
	                         PreparedStatement beverageStmt = connection.prepareStatement(beverageDetailQuery)) {

	                        for (Menu item : orderedItems) {
	                            if (item instanceof Food) {
	                                foodStmt.setInt(1, orderId);
	                                foodStmt.setInt(2, Integer.parseInt(item.getItemID())); // Asumsi ID adalah numerik
	                                foodStmt.setInt(3, 1); // Asumsi kuantitas awal
	                                foodStmt.executeUpdate();
	                            } else if (item instanceof Beverage) {
	                                beverageStmt.setInt(1, orderId);
	                                beverageStmt.setInt(2, Integer.parseInt(item.getItemID())); // Asumsi ID adalah numerik
	                                beverageStmt.setInt(3, 1); // Asumsi kuantitas awal
	                                beverageStmt.executeUpdate();
	                            }
	                        }
	                    }
	                } else {
	                    throw new SQLException("Creating order failed, no ID obtained.");
	                }
	            }
	        }
	    }
	}



    @Override
	public void makePayment(Customer customer, double amount) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection()) {
            // Kurangi saldo customer sebesar amount
            String paymentQuery = "UPDATE customers SET balance = balance - ? WHERE customer_id = ?";
            try (PreparedStatement paymentStmt = connection.prepareStatement(paymentQuery)) {
                paymentStmt.setDouble(1, amount);
                paymentStmt.setInt(2, customer.getId());
                paymentStmt.executeUpdate();
            }
        }
    }

    public double calculateTotalAmount(Vector<Menu> orderedItems) {
        double total = 0;
        for (Menu item : orderedItems) {
            total += item.getPrice();
        }
        return total;
    }


}
