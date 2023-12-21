package FnB_Main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class Beverage extends Menu {
    public enum BeverageType {
        SOFT_DRINK, JUICE, COFFEE, TEA
    }

    private BeverageType beverageType;
    private boolean isCarbonated;

    // Constructor, getters and setters
    public Beverage() {
        // Constructor logic
    }

    public void setBeverageType(BeverageType type) {
        this.beverageType = type;
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

    public Vector<Menu> getMenuItems() throws SQLException {
        Vector<Menu> menuItems = new Vector<>();
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM beverages";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        Beverage beverage = new Beverage();
                        beverage.setItemID(resultSet.getString("beverage_id"));
                        beverage.setItemName(resultSet.getString("beverage_name"));
                        beverage.setPrice(resultSet.getDouble("price"));
                        beverage.setBeverageType(BeverageType.valueOf(resultSet.getString("type")));
                        beverage.setCarbonated(resultSet.getBoolean("is_carbonated"));
                        menuItems.add(beverage);
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
            Beverage beverage = (Beverage) item;
            System.out.printf("| %-11s | %-20s | %-15s | %-10s | %-12s |\n",
                              beverage.getItemID(), beverage.getItemName(), beverage.getBeverageType(),
                              (beverage.isCarbonated() ? "Ya" : "Tidak"), beverage.getPrice());
        }
        return true;
    }

}
