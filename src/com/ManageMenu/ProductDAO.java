package com.ManageMenu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * ProductDAO.java
 * This DAO class provides CRUD database operations for the "products" table in the database.
 */
public class ProductDAO {
    private final String jdbcURL = "jdbc:mysql://localhost:3306/yumride?useSSL=false";
    private final String jdbcUsername = "root";
    private final String jdbcPassword = "bare@coat";

    private static final String INSERT_PRODUCT_SQL = 
        "INSERT INTO products (id, name, category, price, image) VALUES (?, ?, ?, ?, ?);";
    private static final String SELECT_PRODUCT_BY_ID = 
        "SELECT id, name, category, price, image FROM products WHERE id = ?;";
    private static final String SELECT_ALL_PRODUCTS = 
        "SELECT * FROM products;";
    private static final String DELETE_PRODUCT_SQL = 
        "DELETE FROM products WHERE id = ?;";
    private static final String UPDATE_PRODUCT_SQL = 
        "UPDATE products SET name = ?, category = ?, price = ?, image = ? WHERE id = ?;";

    public ProductDAO() {}

    /**
     * Establishes a database connection.
     * 
     * @return the database connection.
     */
    protected Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Error connecting to the database", e);
        }
    }

    /**
     * Inserts a new product into the database.
     * 
     * @param product the product to insert.
     */
    public void insertProduct(Product product) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PRODUCT_SQL)) {
            preparedStatement.setInt(1, product.getId());
            preparedStatement.setString(2, product.getName());
            preparedStatement.setString(3, product.getCategory());
            preparedStatement.setDouble(4, product.getPrice());
            preparedStatement.setString(5, product.getImage());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    /**
     * Selects a product by its ID.
     * 
     * @param id the ID of the product to retrieve.
     * @return the product object, or null if not found.
     */
    public Product selectProduct(int id) {
        Product product = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCT_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                product = new Product(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("category"),
                    rs.getDouble("price"),
                    rs.getString("image")
                );
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return product;
    }

    /**
     * Retrieves all products from the database.
     * 
     * @return a list of products.
     */
    public List<Product> selectAllProducts() {
        List<Product> products = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PRODUCTS)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                products.add(new Product(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("category"),
                    rs.getDouble("price"),
                    rs.getString("image")
                ));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return products;
    }

    /**
     * Deletes a product from the database by its ID.
     * 
     * @param id the ID of the product to delete.
     * @return true if the product was deleted, false otherwise.
     */
    public boolean deleteProduct(int id) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PRODUCT_SQL)) {
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            printSQLException(e);
            return false;
        }
    }

    /**
     * Updates an existing product in the database.
     * 
     * @param product the product to update.
     * @return true if the product was updated, false otherwise.
     */
    public boolean updateProduct(Product product) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PRODUCT_SQL)) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getCategory());
            preparedStatement.setDouble(3, product.getPrice());
            preparedStatement.setString(4, product.getImage());
            preparedStatement.setInt(5, product.getId());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            printSQLException(e);
            return false;
        }
    }

    /**
     * Prints details of an SQL exception to the standard error stream.
     * 
     * @param ex the SQL exception to handle.
     */
    private void printSQLException(SQLException ex) {
        ex.printStackTrace(System.err);
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
