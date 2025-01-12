package com.customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CustomerDBUtil {

    private static Connection con = null;
    private static PreparedStatement pstmt = null;
    private static ResultSet rs = null;

    // Validate user credentials
    public static boolean validate(String userName, String password) {
        boolean isSuccess = false;

        try {
            con = DBConnect.getConnection();
            String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, userName);
            pstmt.setString(2, password);
            rs = pstmt.executeQuery();

            isSuccess = rs.next();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }

        return isSuccess;
    }

    // Retrieve customer details by username
    public static List<Customer> getCustomer(String userName) {
        List<Customer> customers = new ArrayList<>();

        try {
            con = DBConnect.getConnection();
            String sql = "SELECT * FROM users WHERE username = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, userName);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Customer customer = extractCustomerFromResultSet(rs);
                customers.add(customer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }

        return customers;
    }

    // Insert a new customer
    public static boolean insertCustomer(String name, String email, String phone, String address, String username, String password, String role) {
        boolean isSuccess = false;

        try {
            con = DBConnect.getConnection();
            String sql = "INSERT INTO users (name, email, phone, address, username, password, role) VALUES (?, ?, ?, ?, ?, ?, ?)";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.setString(3, phone);
            pstmt.setString(4, address);
            pstmt.setString(5, username);
            pstmt.setString(6, password);
            pstmt.setString(7, role);

            isSuccess = pstmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }

        return isSuccess;
    }

    // Update existing customer details
    public static boolean updateCustomer(int id, String name, String email, String phone, String address, String username, String password, String role) {
        boolean isSuccess = false;

        try {
            con = DBConnect.getConnection();
            String sql = "UPDATE users SET name = ?, email = ?, phone = ?, address = ?, username = ?, password = ?, role = ? WHERE id = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.setString(3, phone);
            pstmt.setString(4, address);
            pstmt.setString(5, username);
            pstmt.setString(6, password);
            pstmt.setString(7, role);
            pstmt.setInt(8, id);

            isSuccess = pstmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }

        return isSuccess;
    }

    // Retrieve customer details by ID
    public static List<Customer> getCustomerDetails(int id) {
        List<Customer> customers = new ArrayList<>();

        try {
            con = DBConnect.getConnection();
            String sql = "SELECT * FROM users WHERE id = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Customer customer = extractCustomerFromResultSet(rs);
                customers.add(customer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }

        return customers;
    }

    // Delete a customer by ID
    public static boolean deleteCustomer(int id) {
        boolean isSuccess = false;

        try {
            con = DBConnect.getConnection();
            String sql = "DELETE FROM users WHERE id = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, id);

            isSuccess = pstmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }

        return isSuccess;
    }

    // Helper method to extract customer data from ResultSet
    private static Customer extractCustomerFromResultSet(ResultSet rs) throws Exception {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String email = rs.getString("email");
        String phone = rs.getString("phone");
        String address = rs.getString("address");
        String username = rs.getString("username");
        String password = rs.getString("password");
        String role = rs.getString("role");

        return new Customer(id, name, email, phone, address, username, password, role);
    }

    // Close all database resources
    private static void closeResources() {
        try {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
            if (con != null) con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
