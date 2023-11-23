package FnB_Main;

import java.sql.Connection;
import java.sql.PreparedStatement;
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

    public void setFoodCategory(FoodCategory category) {
        this.foodCategory = category;
    }

    public FoodCategory getFoodCategory() {
        return foodCategory;
    }

    @Override
    public void saveToDatabase() throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "INSERT INTO food (name, price, category) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, getItemName());
                preparedStatement.setDouble(2, getPrice());
                preparedStatement.setString(3, getFoodCategory().toString());

                preparedStatement.executeUpdate();
            }
        }
    }

}
