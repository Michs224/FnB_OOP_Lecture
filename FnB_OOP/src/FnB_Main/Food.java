package FnB_Main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Food extends Menu{
	
    public enum FoodCategory {
        MAIN_COURSE,
        APPETIZER,
        DESSERT
    }

    private FoodCategory foodCategory; // Kategori makanan (Main Course, Appetizer, atau Dessert)

    // constructor, getter, setter methods
//	public Food() {
//		// TODO Auto-generated constructor stub
//	}

//    public void setFoodCategory(FoodCategory category) {
//        this.foodCategory = category;
//    }
    
    public void setFoodCategory(String category) {
        try {
            this.foodCategory = FoodCategory.valueOf(category);
        } catch (IllegalArgumentException e) {
            System.out.println("Kategori makanan tidak valid: " + category);
            // Atau Anda dapat menangani kasus ini dengan cara lain yang sesuai
        }
    }

    public FoodCategory getFoodCategory() {
        return foodCategory;
    }

    @Override
    public boolean printMenu() throws SQLException {
        boolean dataExists = false;
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM foods";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                    	dataExists = true;
                        Food food = new Food();
                        food.setItemID(resultSet.getString("food_id"));
                        food.setItemName(resultSet.getString("food_name"));
                        food.setFoodCategory(resultSet.getString("category"));
                        food.setPrice(resultSet.getDouble("price"));

                        // Mencetak detail makanan
                        System.out.printf("| %-10s | %-29s | %-20s | %-12s |\n",
                        		food.getItemID(),food.getItemName(),food.getFoodCategory(),food.getPrice());
//                        System.out.println("ID: " + food.getItemID() + ", Nama: " + food.getItemName() +
//                                           ", Kategori: " + food.getFoodCategory() + ", Harga: " + food.getPrice());
                    }
                }
            }
        }
        return dataExists;
    }

//    @Override
//    public void saveToDatabase() throws SQLException {
//        try (Connection connection = DatabaseConnection.getConnection()) {
//            String query = "INSERT INTO food (name, price, category) VALUES (?, ?, ?)";
//            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
//                preparedStatement.setString(1, getItemName());
//                preparedStatement.setDouble(2, getPrice());
//                preparedStatement.setString(3, getFoodCategory().toString());
//
//                preparedStatement.executeUpdate();
//            }
//        }
//    }


}
