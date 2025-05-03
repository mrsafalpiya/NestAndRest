package com.nestandrest.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.nestandrest.model.Product;
import com.nestandrest.config.DbConfig;

public class ProductDAO {

    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM products"; // checking table name
        
        try (Connection conn = DbConfig.getDbConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getInt("productId")); //  matching DB column name
                product.setName(rs.getString("name"));
                product.setImage(rs.getString("image"));
                product.setCategory(rs.getString("category"));
                product.setPrice(rs.getInt("price"));
                product.setStock(rs.getInt("stock"));
                // add other fields if needed
                products.add(product);
            }
            
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        
        return products;
    }

    public Product getProductById(int id) {
        Product product = null;
        String query = "SELECT * FROM products WHERE productId = ?"; // matching DB column name
        
        try (Connection conn = DbConfig.getDbConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
             
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                product = new Product();
                product.setProductId(rs.getInt("productId")); // matching DB column name
                product.setName(rs.getString("name"));
                product.setImage(rs.getString("image"));
                product.setCategory(rs.getString("category"));
                product.setPrice(rs.getDouble("price"));
                product.setStock(rs.getInt("stock"));
               
            }
            
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        
        return product;
    }
}
