package com.nestandrest.dao;

import java.sql.*;
import java.util.*;
import com.nestandrest.model.Product;
import com.nestandrest.config.DbConfig;

public class CartDAO {

    // Get cart_id for user
    public int getActiveCartId(int userId) {
        String query = "SELECT cart_id FROM cart WHERE user_id = ?";
        try (Connection conn = DbConfig.getDbConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("cart_id");
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return -1; // no cart found
    }

    // Create new cart for user
    public int createCart(int userId) {
        String query = "INSERT INTO cart (user_id) VALUES (?)";
        try (Connection conn = DbConfig.getDbConnection();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, userId);
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return -1;
    }

    // Check if product is already in cart
    public boolean isProductInCart(int cartId, int productId) {
        String query = "SELECT COUNT(*) FROM cart_product WHERE cart_id = ? AND product_id = ?";
        try (Connection conn = DbConfig.getDbConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, cartId);
            stmt.setInt(2, productId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Add product to cart with quantity 1
    public void addProductToCart(int cartId, int productId) {
        String query = "INSERT INTO cart_product (cart_id, product_id, quantity) VALUES (?, ?, 1)";
        try (Connection conn = DbConfig.getDbConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, cartId);
            stmt.setInt(2, productId);
            stmt.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Update quantity for a product in cart
    public void updateProductQuantity(int cartId, int productId, int newQuantity) {
        String query = "UPDATE cart_product SET quantity = ? WHERE cart_id = ? AND product_id = ?";
        try (Connection conn = DbConfig.getDbConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, newQuantity);
            stmt.setInt(2, cartId);
            stmt.setInt(3, productId);
            stmt.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Get products in cart
    public List<Product> getCartItems(int cartId) {
        List<Product> cartItems = new ArrayList<>();
        String query = "SELECT p.*, cp.quantity FROM cart_product cp JOIN products p ON cp.product_id = p.productId WHERE cp.cart_id = ?";

        try (Connection conn = DbConfig.getDbConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, cartId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getInt("productId"));
                product.setName(rs.getString("name"));
                product.setImage(rs.getString("image"));
                product.setCategory(rs.getString("category"));
                product.setPrice(rs.getInt("price"));
                product.setStock(rs.getInt("stock"));
                product.setQuantity(rs.getInt("quantity")); // ✅ set quantity
                cartItems.add(product);
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return cartItems;
    }

    // Clear cart
    public void clearCart(int cartId) {
        String query = "DELETE FROM cart_product WHERE cart_id = ?";
        try (Connection conn = DbConfig.getDbConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, cartId);
            stmt.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
