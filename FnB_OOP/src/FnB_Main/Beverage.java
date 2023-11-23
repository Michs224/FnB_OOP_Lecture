package FnB_Main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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

    @Override
    public void saveToDatabase() throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "INSERT INTO beverage (name, price, type, is_carbonated) VALUES (?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, getItemName());
                preparedStatement.setDouble(2, getPrice());
                preparedStatement.setString(3, getBeverageType().toString());
                preparedStatement.setBoolean(4, isCarbonated);

                preparedStatement.executeUpdate();
            }
        }
    }

}
