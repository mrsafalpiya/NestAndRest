package com.nestandrest.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.nestandrest.config.DbConfig;
import com.nestandrest.model.GenderModel;
import com.nestandrest.model.OrderModel;
import com.nestandrest.model.UserAddressModel;
import com.nestandrest.model.UserModel;

/**
 * Service class that handles order related operations. Provides methods to
 * retrieve, create and manage orders.
 * 
 * @author 23048460 Safal Piya
 */
public class OrderService {

	// Database connection
	private Connection dbConn;

	// Cart service for accessing cart related functionality
	private CartService cartService;

	/**
	 * Constructor initializes the database connection and cart service.
	 */
	public OrderService() {
		try {
			this.dbConn = DbConfig.getDbConnection();
		} catch (SQLException | ClassNotFoundException ex) {
			System.err.println("Database connection error: " + ex.getMessage());
			ex.printStackTrace();
		}

		this.cartService = new CartService();
	}

	/**
	 * Retrieves orders based on the order ID. If orderId is 0, retrieves all
	 * orders.
	 * 
	 * @param orderId the ID of the order to retrieve, or 0 for all orders
	 * @return a list of OrderModel objects, or null if an error occurs
	 */
	public List<OrderModel> getOrders(int orderId) {
		if (dbConn == null) {
			System.err.println("Database connection is not available!");
			return null;
		}

		String query = "SELECT o.*, os.name AS order_status_name, ua.address AS address_address, ua.user_name AS address_user_name, ua.phone_number AS address_phone_number, u.user_id AS user_id, u.name AS user_name, u.email AS user_email, u.phone AS user_phone FROM `order` o LEFT JOIN `order_status` os ON o.order_status_id = os.order_status_id LEFT JOIN user_address ua ON o.address_id = ua.user_address_id LEFT JOIN user u ON ua.user_id = u.user_id";
		if (orderId > 0) {
			query += " WHERE o.order_id = ?";
		}

		try {
			PreparedStatement stmt = dbConn.prepareStatement(query);
			if (orderId > 0) {
				stmt.setInt(1, orderId);
			}
			ResultSet result = stmt.executeQuery();

			List<OrderModel> orders = new ArrayList<OrderModel>();

			while (result.next()) {
				OrderModel order = new OrderModel(result.getInt("order_id"), result.getTimestamp("order_date"),
						result.getInt("order_status_id"), result.getString("order_status_name"),
						result.getInt("cart_id"), result.getInt("address_id"),
						new UserAddressModel(0, result.getString("address_address"),
								result.getString("address_user_name"), result.getString("address_phone_number"), false),
						result.getDouble("total_price"));
				order.setCartProducts(this.cartService.getCartItems(result.getInt("cart_id")));
				order.setUser(new UserModel(result.getInt("user_id"), result.getString("user_name"),
						result.getString("user_email"), result.getString("user_phone")));
				orders.add(order);
			}

			return orders;
		} catch (SQLException e) {
			System.err.println("Error during student registration: " + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Updates the status of an order.
	 * 
	 * @param orderId       the ID of the order to update
	 * @param orderStatusId the new status ID to set for the order
	 * @return true if the update was successful, false otherwise
	 */
	public boolean setOrderStatus(int orderId, int orderStatusId) {
		if (dbConn == null) {
			System.err.println("Database connection is not available.");
			return false;
		}

		try {
			String updateQuery = "UPDATE `order` SET order_status_id = ? WHERE order_id = ?";
			PreparedStatement updateStmt = dbConn.prepareStatement(updateQuery);
			updateStmt.setInt(1, orderStatusId);
			updateStmt.setInt(2, orderId);
			int affectedRows = updateStmt.executeUpdate();

			return affectedRows > 0;
		} catch (SQLException e) {
			System.err.println("Error during setting order status: " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}
}
