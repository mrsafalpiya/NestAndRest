package com.nestandrest.dao;

import com.nestandrest.model.Category;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object (DAO) for accessing category data from the database.
 * This class provides functionality to retrieve all product categories.
 * 
 * @author Bhumika Karki
 */
public class CategoryDAO {
    private Connection connection;

    /**
     * Constructor that initializes the DAO with a JDBC database connection.
     *
     * @param connection the database connection to use
     */
    public CategoryDAO(Connection connection) {
        this.connection = connection;
    }

    /**
     * Retrieves all categories from the `category` table.
     *
     * @return a list of all Category objects
     * @throws SQLException if a database access error occurs
     */
    public List<Category> getAllCategories() throws SQLException {
        List<Category> categories = new ArrayList<>();
        
     // Corrected SQL query to select all columns
        String sql = "SELECT FROM category";
        
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Category category = new Category();
                category.setCategoryId(rs.getInt("category_id"));
                category.setName(rs.getString("name"));
                category.setDescription(rs.getString("description"));
                categories.add(category);
            }
        }
        return categories;
    }
}