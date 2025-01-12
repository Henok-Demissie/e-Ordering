package com.customer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {

    private static final String URL = "jdbc:mysql://localhost:3306/yumride";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "bare@coat";
    private static Connection connection = null;

    /**
     * Establishes and returns a connection to the database.
     *
     * @return a Connection object or null if the connection fails
     */
    public static Connection getConnection() {
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the connection
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                System.out.println("Database connection established successfully.");
            }
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC Driver not found. Ensure the MySQL driver is added to the project.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Failed to establish database connection. Check the URL, username, and password.");
            e.printStackTrace();
        }

        return connection;
    }
}
