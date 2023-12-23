package FnB_Main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class Food extends Menu {
    public enum FoodCategory {
        MAIN_COURSE, APPETIZER, DESSERT
    }

    private FoodCategory foodCategory;

    // Constructor, getters and setters
    public Food() {
        // Constructor logic
    }

    public void setFoodCategory(FoodCategory category) {
        this.foodCategory = category;
    }

    public FoodCategory getFoodCategory() {
        return foodCategory;
    }
    
    @Override
    public Vector<Menu> getMenuItems() throws SQLException {
        Vector<Menu> menuItems = new Vector<>();
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM foods";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        Food food = new Food();
                        food.setItemID(resultSet.getString("food_id"));
                        food.setItemName(resultSet.getString("food_name"));
                        food.setPrice(resultSet.getDouble("price"));
                        food.setFoodCategory(FoodCategory.valueOf(resultSet.getString("category")));
                        menuItems.add(food);
                    }
                }
            }
        }
        return menuItems;
    }

    public boolean printMenu() throws SQLException {
        Vector<Menu> menuItems = getMenuItems();
        if (menuItems.isEmpty()) {
            return false;
        }
        for (Menu item : menuItems) {
            Food food = (Food) item;
            System.out.printf("| %-10s | %-29s | %-20s | %-12s |\n",
                              food.getItemID(), food.getItemName(), food.getFoodCategory(), food.getPrice());
        }
        return true;
    }
}
