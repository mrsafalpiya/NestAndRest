package com.nestandrest.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.nestandrest.model.Product;
import com.nestandrest.config.DbConfig;

public class ProductDAO {

    // ✅ USER SIDE
	public List<Product> getAllProducts() throws SQLException, ClassNotFoundException {
	    List<Product> products = new ArrayList<>();
	    String query = "SELECT p.*, v.variant_name " +
	               		"FROM product p " +
	               		"LEFT JOIN product_variant v ON p.product_id = v.product_id";

	    try (Connection conn = DbConfig.getDbConnection();
	         PreparedStatement stmt = conn.prepareStatement(query);
	         ResultSet rs = stmt.executeQuery()) {
	        while (rs.next()) {
	            Product product = extractProduct(rs);
	            products.add(product);
	        }
	    }
	    return products;
	}


	public List<Product> searchProductsByName(String keyword) throws SQLException, ClassNotFoundException {
	    List<Product> products = new ArrayList<>();
	    String query = "SELECT p.*, v.variant_name " +
	               		"FROM product p " +
	               		"LEFT JOIN product_variant v ON p.product_id = v.product_id " +
	               		"WHERE p.name LIKE ?";
	    try (Connection conn = DbConfig.getDbConnection();
	         PreparedStatement stmt = conn.prepareStatement(query)) {
	        stmt.setString(1, "%" + keyword + "%");
	        ResultSet rs = stmt.executeQuery();
	        while (rs.next()) {
	            Product product = extractProduct(rs);
	            products.add(product);
	        }
	    }
	    return products;
	}


    public Product getProductById(int id) throws SQLException, ClassNotFoundException {
        Product product = null;
        String query = "SELECT * FROM product WHERE product_id=?";
        try (Connection conn = DbConfig.getDbConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                product = extractProduct(rs);
            }
        }
        return product;
    }

    private Product extractProduct(ResultSet rs) throws SQLException {
        Product product = new Product();
        product.setProductId(rs.getInt("product_id"));
        product.setName(rs.getString("name"));
        product.setSubDescription(rs.getString("short_description"));
        product.setFullDescription(rs.getString("long_description"));
        product.setPrice(rs.getDouble("price"));
        product.setDiscountedPrice(rs.getDouble("discounted_price"));
        product.setCategory(mapCategoryIdToName(rs.getInt("category_id")));
        product.setColor(rs.getString("variant_name"));
        product.setSize(rs.getString("variant_name"));
        product.setImage(rs.getString("image"));
        product.setQuantity(rs.getInt("quantity"));
        product.setInStock(rs.getBoolean("in_stock"));
        product.setPublished(rs.getBoolean("published"));
        return product;
    }

    private String mapCategoryIdToName(int id) {
        switch (id) {
            case 1: return "sofa";
            case 2: return "chair";
            case 3: return "stool";
            case 4: return "rack";
            case 5: return "hanger";
            case 6: return "table";
            default: return "unknown";
        }
    }

    private int mapCategoryNameToId(String name) {
        switch (name.toLowerCase()) {
            case "sofa": return 1;
            case "chair": return 2;
            case "stool": return 3;
            case "rack": return 4;
            case "hanger": return 5;
            case "table": return 6;
            default: return 0;
        }
    }

    // ✅ ADMIN SIDE
    public void insertProduct(Product product) throws SQLException, ClassNotFoundException {
        String query = "INSERT INTO product (name, short_description, long_description, price, discounted_price, category_id, color, size, image, quantity, in_stock, published) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DbConfig.getDbConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, product.getName());
            stmt.setString(2, product.getSubDescription());
            stmt.setString(3, product.getFullDescription());
            stmt.setDouble(4, product.getPrice());
            stmt.setDouble(5, product.getDiscountedPrice());
            stmt.setInt(6, mapCategoryNameToId(product.getCategory()));
            stmt.setString(7, product.getColor());
            stmt.setString(8, product.getSize());
            stmt.setString(9, product.getImage());
            stmt.setInt(10, product.getQuantity());
            stmt.setBoolean(11, product.isInStock());
            stmt.setBoolean(12, product.isPublished());
            stmt.executeUpdate();
        }
    }

    public void updateProduct(Product product) throws SQLException, ClassNotFoundException {
        String query = "UPDATE product SET name=?, short_description=?, long_description=?, price=?, discounted_price=?, category_id=?, color=?, size=?, image=?, quantity=?, in_stock=?, published=? WHERE product_id=?";
        try (Connection conn = DbConfig.getDbConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, product.getName());
            stmt.setString(2, product.getSubDescription());
            stmt.setString(3, product.getFullDescription());
            stmt.setDouble(4, product.getPrice());
            stmt.setDouble(5, product.getDiscountedPrice());
            stmt.setInt(6, mapCategoryNameToId(product.getCategory()));
            stmt.setString(7, product.getColor());
            stmt.setString(8, product.getSize());
            stmt.setString(9, product.getImage());
            stmt.setInt(10, product.getQuantity());
            stmt.setBoolean(11, product.isInStock());
            stmt.setBoolean(12, product.isPublished());
            stmt.setInt(13, product.getProductId());
            stmt.executeUpdate();
        }
    }

    public void deleteProduct(int productId) throws SQLException, ClassNotFoundException {
        String query = "DELETE FROM product WHERE product_id=?";
        try (Connection conn = DbConfig.getDbConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, productId);
            stmt.executeUpdate();
        }
    }
}
