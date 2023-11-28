package FnB_Main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    /* 
        Establish connection to mysql hosted on alibaba cloud 
    */
    private static final String URL = "jdbc:mysql://47.245.117.105/MY_FnB?useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD = "12345678";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}