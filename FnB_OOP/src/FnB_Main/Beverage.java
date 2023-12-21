package FnB_Main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import FnB_Main.Food.FoodCategory;

public class Beverage extends Menu{
	
    public enum BeverageType {
        SOFT_DRINK,
        JUICE,
        COFFEE,
        TEA
    }

    private BeverageType beverageType; // Jenis minuman (Soft drink, Juice, Coffee, Tea)
    private boolean isCarbonated; // Apakah minuman berkarbonasi?

    // constructor, getter, setter methods
	public Beverage() {
		// TODO Auto-generated constructor stub
	}

    public void setBeverageType(String type) {
        try {
            this.beverageType = BeverageType.valueOf(type);
        } catch (IllegalArgumentException e) {
            System.out.println("Kategori makanan tidak valid: " + type);
            // Atau Anda dapat menangani kasus ini dengan cara lain yang sesuai
        }
    }

    public BeverageType getBeverageType() {
        return beverageType;
    }

    public void setCarbonated(boolean carbonated) {
        isCarbonated = carbonated;
    }

    public boolean isCarbonated() {
        return isCarbonated;
    }
    
    
    @Override
    public boolean printMenu() throws SQLException {
        boolean dataExists = false;
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM beverages";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                    	dataExists = true;
                        Beverage beverage = new Beverage();
                        beverage.setItemID(resultSet.getString("beverage_id"));
                        beverage.setItemName(resultSet.getString("beverage_name"));
                        beverage.setPrice(resultSet.getDouble("price"));
                        beverage.setBeverageType(resultSet.getString("type")); 

                        // Mengkonversi nilai int menjadi boolean
                        boolean carbonated = resultSet.getInt("is_carbonated") == 1;
                        beverage.setCarbonated(carbonated);

                        // Mencetak detail minuman
                        System.out.printf("| %-11s | %-20s | %-15s | %-10s | %-12s |\n",
                        		beverage.getItemID(),beverage.getItemName(),beverage.getBeverageType(),
                        		(beverage.isCarbonated() ? "Ya" : "Tidak"),beverage.getPrice());
                    }
                }
            }
        }
        return dataExists;
    }


//    @Override
//    public void saveToDatabase() throws SQLException {
//        try (Connection connection = DatabaseConnection.getConnection()) {
//            String query = "INSERT INTO beverage (name, price, type, is_carbonated) VALUES (?, ?, ?, ?)";
//            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
//                preparedStatement.setString(1, getItemName());
//                preparedStatement.setDouble(2, getPrice());
//                preparedStatement.setString(3, getBeverageType().toString());
//                preparedStatement.setBoolean(4, isCarbonated);
//
//                preparedStatement.executeUpdate();
//            }
//        }
//    }

}
