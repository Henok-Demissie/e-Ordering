package com.Adminlogin.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.Adminlogin.bean.LoginBean;

public class LoginDao {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/yumride?useSSL=false";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "bare@coat";
    private static final String QUERY = "SELECT * FROM users WHERE username = ? AND password = ?";

    /**
     * Validates the login credentials of a user.
     *
     * @param loginBean the login bean containing username and password.
     * @return true if the credentials are valid, false otherwise.
     * @throws ClassNotFoundException if the JDBC driver is not found.
     */
    public boolean validate(LoginBean loginBean) throws ClassNotFoundException {
        boolean isValidUser = false;

        // Load MySQL JDBC driver
        Class.forName("com.mysql.cj.jdbc.Driver");

        // Use try-with-resources to ensure proper resource management
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(QUERY)) {

            // Set parameters for the prepared statement
            preparedStatement.setString(1, loginBean.getUsername());
            preparedStatement.setString(2, loginBean.getPassword());

            System.out.println("Executing query: " + preparedStatement);

            // Execute query and check if user exists
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                isValidUser = resultSet.next();
            }
        } catch (SQLException e) {
            // Handle SQL exceptions
            logSQLException(e);
        }
        return isValidUser;
    }

    /**
     * Logs details of a SQLException for debugging purposes.
     *
     * @param ex the SQLException to log.
     */
    private void logSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.err.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
