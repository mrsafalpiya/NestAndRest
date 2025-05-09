package com.nestandrest.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.nestandrest.config.DbConfig;
import com.nestandrest.model.CartProductVariantValueModel;
import com.nestandrest.model.ProductVariantModel;
import com.nestandrest.model.ProductVariantValueModel;

public class CartService {
	private Connection dbConn;

	public CartService() {
		try {
			this.dbConn = DbConfig.getDbConnection();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public boolean addProductToCart(int userId, int productId, List<ProductVariantModel> variants, int qty) {
		int cartId = this.createUserCartAndReturnIfNotExist(userId);
		if (cartId == -1) {
			return false;
		}

		for (ProductVariantModel entry : variants) {
			for (ProductVariantValueModel valueEntry : entry.getVariantValues()) {
				insertCartProductVariantValue(new CartProductVariantValueModel(cartId, productId,
						entry.getProductVariantId(), valueEntry.getProductVariantValueId(), qty));
			}
		}

		return false;
	}

	private int createUserCartAndReturnIfNotExist(int userId) {
		if (dbConn == null) {
			System.err.println("Database connection is not available.");
			return -1;
		}

		try {
			// Upsert the user's cart
			String insertQuery = "INSERT IGNORE INTO `cart` (`user_id`) VALUES (?);";
			PreparedStatement insertStmt = dbConn.prepareStatement(insertQuery);
			insertStmt.setInt(1, userId);
			insertStmt.executeUpdate();

			// Get the user's cart
			String selectQuery = "SELECT * FROM `cart` WHERE `cart`.`user_id` = ?";
			PreparedStatement selectStmt = dbConn.prepareStatement(selectQuery);
			selectStmt.setInt(1, userId);
			ResultSet rs = selectStmt.executeQuery();
			if (rs.next()) {
				return rs.getInt("cart_id");
			}
		} catch (SQLException e) {
			System.err.println("Error during user cart: " + e.getMessage());
			e.printStackTrace();
			return -1;
		}
		return -1;
	}

	private Boolean insertCartProductVariantValue(CartProductVariantValueModel cartProductVariantValue) {
		if (dbConn == null) {
			System.err.println("Database connection is not available.");
			return null;
		}

		String insertQuery = "INSERT INTO `cart_product_variant_value` (`cart_id`, `product_id`, `product_variant_id`, `product_variant_value_id`, `qty`) VALUES (?, ?, ?, ?, ?);";
		try {
			// Insert to user's cart
			PreparedStatement insertStmt = dbConn.prepareStatement(insertQuery);
			insertStmt.setInt(1, cartProductVariantValue.getCartId());
			insertStmt.setInt(2, cartProductVariantValue.getProductId());
			insertStmt.setInt(3, cartProductVariantValue.getProductVariantId());
			insertStmt.setInt(4, cartProductVariantValue.getProductVariantId());
			insertStmt.setInt(5, cartProductVariantValue.getQty());
			insertStmt.executeUpdate();
		} catch (SQLException e) {
			System.err.println("Error during inserting cart item: " + e.getMessage());
			e.printStackTrace();
			return null;
		}
		return null;
	}
}
