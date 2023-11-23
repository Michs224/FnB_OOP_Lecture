package FnB_Main;

import java.sql.Connection;
import java.sql.SQLException;
//import java.util.ArrayList;
import java.util.Vector;

public class Order implements OrderManagement, Payment{

	public Order() {
		// TODO Auto-generated constructor stub
	}

    @Override
    public void placeOrder(Customer customer, Vector<Menu> orderedItems) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection()) {
            // Implementasi penambahan pesanan ke database MySQL
            // Gunakan orderedItems untuk menambahkan detail pesanan ke tabel order_items
        }
    }

    @Override
    public void makePayment(Customer customer, double amount) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection()) {
            // Implementasi pembayaran dan pengurangan saldo dari database MySQL
            // Tampilkan invoice setelah pembayaran berhasil
        }
    }

}
