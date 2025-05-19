package com.nestandrest.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.nestandrest.config.DbConfig;
import com.nestandrest.model.CartProductVariantValueModel;
import com.nestandrest.model.Product;
import com.nestandrest.model.ProductModel;
import com.nestandrest.model.ProductVariantModel;

public class CartService {
	private Connection dbConn;

	public CartService() {
		try {
			this.dbConn = DbConfig.getDbConnection();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public boolean addProductToCart(int userId, int productId, Map<Integer, Integer> variantSelections, int qty) {
		int cartId = this.createUserCartAndReturnIfNotExist(userId);
		if (cartId == -1) {
			return false;
		}

		for (Map.Entry<Integer, Integer> entry : variantSelections.entrySet()) {
			int variantId = entry.getKey();
			int variantValueId = entry.getValue();

			insertCartProductVariantValue(
					new CartProductVariantValueModel(cartId, productId, variantId, variantValueId, qty));
		}

		return true; // Changed to return true if execution completes successfully
	}

	private int createUserCartAndReturnIfNotExist(int userId) {
		if (dbConn == null) {
			System.err.println("Database connection is not available.");
			return -1;
		}

		try {
			// Get the user's cart which has not been ordered yet
			String selectQuery = "SELECT * FROM `cart` c LEFT JOIN `order` o ON c.cart_id = o.cart_id WHERE c.user_id = ? AND o.order_id IS NULL";
			PreparedStatement selectStmt = dbConn.prepareStatement(selectQuery);
			selectStmt.setInt(1, userId);
			ResultSet rs = selectStmt.executeQuery();
			if (rs.next()) {
				return rs.getInt("cart_id");
			}

			// If we get here, we do not have a unordered cart for the user. So insert it.
			String insertQuery = "INSERT INTO `cart` (`user_id`) VALUES (?);";
			PreparedStatement insertStmt = dbConn.prepareStatement(insertQuery);
			insertStmt.setInt(1, userId);
			insertStmt.executeUpdate();

			// Finally get the user cart ID
			rs = selectStmt.executeQuery();
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
			insertStmt.setInt(4, cartProductVariantValue.getProductVariantValueId());
			insertStmt.setInt(5, cartProductVariantValue.getQty());
			insertStmt.executeUpdate();
		} catch (SQLException e) {
			System.err.println("Error during inserting cart item: " + e.getMessage());
			e.printStackTrace();
			return null;
		}
		return null;
	}

	public List<ProductModel> getUserCartItems(int userId) {
		if (dbConn == null) {
			System.err.println("Database connection is not available.");
			return null;
		}

		String query = "SELECT product_id, qty FROM cart_product_variant_value cpvv LEFT JOIN cart c ON cpvv.cart_id = c.cart_id AND c.user_id = ? LEFT JOIN `order` o ON c.cart_id = o.cart_id WHERE c.user_id = ? AND o.order_id IS NULL GROUP BY cpvv.product_id, qty;";
		try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
			stmt.setInt(1, userId);
			stmt.setInt(2, userId);
			ResultSet result = stmt.executeQuery();

			List<ProductModel> products = new ArrayList<ProductModel>();

			while (result.next()) {
				int productId = result.getInt("product_id");
				ProductModel product = (new ProductService()).getById(productId);
				product.setVariants(this.getCartProductVariantsAsMap(userId, productId));
				product.setCartQty(result.getInt("qty"));
				products.add(product);
			}

			return products;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<ProductModel> getCartItems(int cartId) {
		if (dbConn == null) {
			System.err.println("Database connection is not available.");
			return null;
		}

		String query = "SELECT product_id, qty, c.user_id FROM cart_product_variant_value cpvv LEFT JOIN cart c ON cpvv.cart_id = c.cart_id LEFT JOIN `order` o ON c.cart_id = o.cart_id WHERE c.cart_id = ? GROUP BY cpvv.product_id, qty, c.user_id";
		try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
			stmt.setInt(1, cartId);
			ResultSet result = stmt.executeQuery();

			List<ProductModel> products = new ArrayList<ProductModel>();

			while (result.next()) {
				int productId = result.getInt("product_id");
				int userId = result.getInt("user_id");
				ProductModel product = (new ProductService()).getById(productId);
				product.setVariants(this.getCartProductVariantsAsMap(userId, productId));
				product.setCartQty(result.getInt("qty"));
				products.add(product);
			}

			return products;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	private Map<String, String> getCartProductVariantsAsMap(int userId, int productId) {
		if (dbConn == null) {
			System.err.println("Database connection is not available.");
			return null;
		}

		String query = "SELECT pv.variant_name, pvv.variant_value FROM cart_product_variant_value cpvv LEFT JOIN cart c ON cpvv.cart_id = c.cart_id LEFT JOIN product_variant pv ON cpvv.product_variant_id = pv.product_variant_id LEFT JOIN product_variant_value pvv ON cpvv.product_variant_value_id = pvv.product_variant_value_id WHERE c.user_id = ? AND cpvv.product_id = ?";
		try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
			stmt.setInt(1, userId);
			stmt.setInt(2, productId);
			ResultSet result = stmt.executeQuery();

			Map<String, String> variantsMap = new HashMap<String, String>();

			while (result.next()) {
				variantsMap.put(result.getString("variant_name"), result.getString("variant_value"));
			}

			return variantsMap;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean removeProductFromCart(int userId, int productId) {
		if (dbConn == null) {
			System.err.println("Database connection is not available.");
			return false;
		}

		try {
			// First get the user's cart ID
			String selectCartIdQuery = "SELECT cart_id FROM cart WHERE user_id = ?";
			PreparedStatement selectStmt = dbConn.prepareStatement(selectCartIdQuery);
			selectStmt.setInt(1, userId);
			ResultSet rs = selectStmt.executeQuery();

			if (rs.next()) {
				int cartId = rs.getInt("cart_id");

				// Delete the product variants from cart_product_variant_value table for this
				// cart and product
				String deleteQuery = "DELETE FROM cart_product_variant_value WHERE cart_id = ? AND product_id = ?";
				PreparedStatement deleteStmt = dbConn.prepareStatement(deleteQuery);
				deleteStmt.setInt(1, cartId);
				deleteStmt.setInt(2, productId);
				int affectedRows = deleteStmt.executeUpdate();

				return affectedRows > 0;
			} else {
				// No cart found for user
				return false;
			}
		} catch (SQLException e) {
			System.err.println("Error during removing product from cart: " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	public int addCartItemsToOrder(int userId, int addressId) {
		if (dbConn == null) {
			System.err.println("Database connection is not available.");
			return -1;
		}
		int cartId = this.createUserCartAndReturnIfNotExist(userId);

		try {
			// Get the ID of the pending order status
			int pendingOrderId = 0;
			String selectQuery = "SELECT * FROM `order_status` os WHERE os.name = 'Pending'";
			PreparedStatement selectStmt = dbConn.prepareStatement(selectQuery);
			ResultSet rs = selectStmt.executeQuery();
			if (rs.next()) {
				pendingOrderId = rs.getInt("order_status_id");
			}

			// Get the total price
			List<ProductModel> itemsInCart = this.getUserCartItems(userId);
			int totalPrice = 0;
			for (ProductModel productModel : itemsInCart) {
				totalPrice += (productModel.getSalePrice() * productModel.getCartQty());
			}

			String insertQuery = "INSERT INTO `order` (order_status_id, cart_id, address_id, total_price) VALUES (?, ?, ?, ?);";
			PreparedStatement insertStmt = dbConn.prepareStatement(insertQuery,
					PreparedStatement.RETURN_GENERATED_KEYS);
			insertStmt.setInt(1, pendingOrderId);
			insertStmt.setInt(2, cartId);
			insertStmt.setInt(3, addressId);
			insertStmt.setInt(4, totalPrice);
			insertStmt.executeUpdate();

			// Get the ID of the newly inserted order
			ResultSet generatedKeys = insertStmt.getGeneratedKeys();
			if (generatedKeys.next()) {
				return generatedKeys.getInt(1);
			}
		} catch (SQLException e) {
			System.err.println("Error during user cart: " + e.getMessage());
			e.printStackTrace();
			return -1;
		}
		return -1;
	}

}
