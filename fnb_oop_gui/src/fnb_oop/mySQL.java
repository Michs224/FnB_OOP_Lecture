package fnb_oop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class mySQL {
	private static final String URL = "jdbc:mysql://47.245.117.105/db_fnb?useSSL=false";
	private static final String USER = "root";
	private static final String PASSWORD = "fqF+UP7=^Bc#p<n[r6!b(h";
	
	static {//execute once when loaded to JVM a.k.a initializer
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("sucessfull connected");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Failed to load MySQL JDBC driver");
		}
	}
	
	public static Connection getConnection() throws SQLException{
		return DriverManager.getConnection(URL,USER,PASSWORD);
	}
	
	public user userQuery(String username) throws SQLException {
		try (Connection connection = getConnection()) {
	        String query = "SELECT * FROM users WHERE username = ?";
	        
	        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
	            // Set the parameter for the username
	            preparedStatement.setString(1, username);
	            
	            try (ResultSet resultSet = preparedStatement.executeQuery()) {
	                if (resultSet.next()) {
	                    String userId = resultSet.getString("userId");
	                    String created = resultSet.getString("created");
	                    String userName = resultSet.getString("username");
	                    String password = resultSet.getString("password");
	                    String name = resultSet.getString("name");
	                    String email = resultSet.getString("email");
	                    String address = resultSet.getString("address");
	                    
	                    user user1 = new user(userId,created,userName,password,name,email,address);
	                    return user1;
	                }
	            }
	        }
	    }
		return null;
	}
	
	public String getPasswordQuery(String username) throws SQLException {
	    String password = null;

	    try (Connection connection = getConnection()) {
	        String query = "SELECT password FROM users WHERE username = ?";
	        
	        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
	            // Set the parameter for the username
	            preparedStatement.setString(1, username);
	            
	            try (ResultSet resultSet = preparedStatement.executeQuery()) {
	                if (resultSet.next()) {
	                    password = resultSet.getString("password");
	                }
	            }
	        }
	    }

	    return password;
	}

	public void createNewUser(String name,String username,String email,String password) throws SQLException{

	    try (Connection connection = getConnection()) {
	        String query = "INSERT INTO users(username,password,name,email,address) VALUES (?,?,?,?,?);";
	        
	        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
	            // Set the parameter for the username
	            preparedStatement.setString(1, username);
	            preparedStatement.setString(2, password);
	            preparedStatement.setString(3, name);
	            preparedStatement.setString(4, email);
	            preparedStatement.setString(5, "N/A");
	            
	            int i = preparedStatement.executeUpdate();
	            if (i > 0) {
	                System.out.println("ROW INSERTED");
	            } else {
	                System.out.println("ROW NOT INSERTED");
	            }
	        }
	    }
	}
	
	public void updateUserProfile(user myUser) throws SQLException{
		try (Connection connection = getConnection()) {
	        String query = "UPDATE users SET username = ?,password = ?,name = ?,email = ?,address = ? WHERE userId = ?;";
	        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
	            // Set the parameter for the username
	            preparedStatement.setString(1, myUser.getUsername());
	            preparedStatement.setString(2, myUser.getPassword());
	            preparedStatement.setString(3, myUser.getName());
	            preparedStatement.setString(4, myUser.getEmail());
	            preparedStatement.setString(5, myUser.getAddress());
	            preparedStatement.setString(6, myUser.getUserId());
	            
	            int i = preparedStatement.executeUpdate();
	            if (i > 0) {
	                System.out.println("ROW INSERTED");
	            } else {
	                System.out.println("ROW NOT INSERTED");
	            }
	        }
		}
	}
	
}
