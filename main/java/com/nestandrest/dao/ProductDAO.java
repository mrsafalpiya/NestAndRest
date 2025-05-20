package com.nestandrest.dao;

import com.nestandrest.model.Product;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object (DAO) for performing CRUD operations on the `product` table.
 * It handles inserting, retrieving, updating, and deleting product records.
 * 
 * This class uses JDBC to communicate with the database.
 * 
 * @author Bhumika Karki
 */
public class ProductDAO {

	private Connection connection;

    /**
     * Constructor to initialize DAO with a database connection.
     * 
     * @param connection JDBC database connection
     */
    public ProductDAO(Connection connection) {
        this.connection = connection;
    }

    /**
     * Adds a new product to the database and sets the generated product ID.
     *
     * @param product Product object to be inserted
     * @throws SQLException if a database error occurs
     */
    public void addProduct(Product product) throws SQLException {
        String sql = "INSERT INTO product (name, short_description, long_description, price, discounted_price, category_id, image, stock, color, size, quantity, in_stock, published) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
        	// Setting product properties into SQL placeholder
        	stmt.setString(1, product.getName());
            stmt.setString(2, product.getFullDescription());
            stmt.setString(3, product.getSubDescription());
            stmt.setDouble(4, product.getPrice());
            stmt.setDouble(5, product.getDiscountedPrice());
            stmt.setInt(6, getCategoryIdByName(product.getCategory()));
            stmt.setString(7, product.getImage());
            stmt.setInt(8, product.getStock());
            stmt.setString(9, product.getColor());
            stmt.setString(10, product.getSize());
            stmt.setInt(11, product.getQuantity());
            stmt.setBoolean(12, product.isInStock());
            stmt.setBoolean(13, product.isPublished());
            stmt.executeUpdate();

         // Retrieving and assigning generated product ID
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    product.setProductId(generatedKeys.getInt(1));
                }
            }
        }
    }

    /**
     * Retrieves all products from the database, joined with their category names.
     *
     * @return list of all products
     * @throws SQLException if a database error occurs
     */
    public List<Product> getAllProducts() throws SQLException {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT p.*, c.name AS category_name FROM product p JOIN category c ON p.category_id = c.category_id";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getInt("product_id"));
                product.setName(rs.getString("name"));
                product.setSubDescription(rs.getString("short_description"));
                product.setFullDescription(rs.getString("long_description"));
                product.setPrice(rs.getDouble("price"));
                product.setDiscountedPrice(rs.getDouble("discounted_price"));
                product.setCategory(rs.getString("category_name"));
                product.setImage(rs.getString("image"));
                product.setStock(rs.getInt("stock"));
                product.setColor(rs.getString("color"));
                product.setSize(rs.getString("size"));
                product.setQuantity(rs.getInt("quantity"));
                product.setInStock(rs.getBoolean("in_stock"));
                product.setPublished(rs.getBoolean("published"));
                products.add(product);
            }
        }
        return products;
    }

    /**
     * Retrieves a single product by its ID.
     *
     * @param productId product ID to search
     * @return Product object or null if not found
     * @throws SQLException if a database error occurs
     */
    public Product getProductById(int productId) throws SQLException {
        Product product = null;
        String sql = "SELECT p.*, c.name AS category_name FROM product p JOIN category c ON p.category_id = c.category_id WHERE p.product_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, productId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    product = new Product();
                    product.setProductId(rs.getInt("product_id"));
                    product.setName(rs.getString("name"));
                    product.setSubDescription(rs.getString("short_description"));
                    product.setFullDescription(rs.getString("long_description"));
                    product.setPrice(rs.getDouble("price"));
                    product.setDiscountedPrice(rs.getDouble("discounted_price"));
                    product.setCategory(rs.getString("category_name"));
                    product.setImage(rs.getString("image"));
                    product.setStock(rs.getInt("stock"));
                    product.setColor(rs.getString("color"));
                    product.setSize(rs.getString("size"));
                    product.setQuantity(rs.getInt("quantity"));
                    product.setInStock(rs.getBoolean("in_stock"));
                    product.setPublished(rs.getBoolean("published"));
                }
            }
        }
        return product;
    }

    /**
     * Updates an existing product in the database.
     *
     * @param product Product object with updated data
     * @throws SQLException if a database error occurs
     */
    public void updateProduct(Product product) throws SQLException {
        String sql = "UPDATE product SET name = ?, short_description = ?, long_description = ?, price = ?, discounted_price = ?, category_id = ?, image = ?, stock = ?, color = ?, size = ?, quantity = ?, in_stock = ?, published = ? WHERE product_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, product.getName());
            stmt.setString(2, product.getSubDescription());
            stmt.setString(3, product.getFullDescription());
            stmt.setDouble(4, product.getPrice());
            stmt.setDouble(5, product.getDiscountedPrice());
            stmt.setInt(6, getCategoryIdByName(product.getCategory()));
            stmt.setString(7, product.getImage());
            stmt.setInt(8, product.getStock());
            stmt.setString(9, product.getColor());
            stmt.setString(10, product.getSize());
            stmt.setInt(11, product.getQuantity());
            stmt.setBoolean(12, product.isInStock());
            stmt.setBoolean(13, product.isPublished());
            stmt.setInt(14, product.getProductId());
            stmt.executeUpdate();
        }
    }

    /**
     * Deletes a product from the database based on its ID.
     *
     * @param productId ID of the product to delete
     * @throws SQLException if a database error occurs
     */
    public void deleteProduct(int productId) throws SQLException {
        String sql = "DELETE FROM product WHERE product_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, productId);
            stmt.executeUpdate();
        }
    }

    /**
     * Utility method to get category ID from category name.
     * Throws SQLException if the category is not found.
     *
     * @param categoryName category name
     * @return corresponding category ID
     * @throws SQLException if no category is found
     */
    private int getCategoryIdByName(String categoryName) throws SQLException {
        String sql = "SELECT category_id FROM category WHERE name = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, categoryName);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("category_id");
                }
            }
        }
        throw new SQLException("Category not found: " + categoryName);
    }
}
