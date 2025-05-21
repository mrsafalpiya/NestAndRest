package com.nestandrest.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.nestandrest.config.DbConfig;
import com.nestandrest.model.OrderStatusModel;

/**
 * Service class that handles order status operations. Provides methods to
 * retrieve and manage order statuses.
 * 
 * @author 23048460 Safal Piya
 */
public class OrderStatusService {

	// Database connection
	private Connection dbConn;

	/**
	 * Constructor initializes the database connection.
	 */
	public OrderStatusService() {
		try {
			this.dbConn = DbConfig.getDbConnection();
		} catch (SQLException | ClassNotFoundException ex) {
			System.err.println("Database connection error: " + ex.getMessage());
			ex.printStackTrace();
		}
	}

	/**
	 * Retrieves all order statuses from the database.
	 * 
	 * @return a list of OrderStatusModel objects, or null if an error occurs
	 */
	public List<OrderStatusModel> getAllOrderStatus() {
		if (dbConn == null) {
			System.err.println("Database connection is not available!");
			return null;
		}

		String query = "SELECT * FROM order_status";

		try {
			PreparedStatement stmt = dbConn.prepareStatement(query);
			ResultSet result = stmt.executeQuery();

			List<OrderStatusModel> orderStatuses = new ArrayList<OrderStatusModel>();

			while (result.next()) {
				orderStatuses.add(new OrderStatusModel(result.getInt("order_status_id"), result.getString("name")));
			}

			return orderStatuses;
		} catch (SQLException e) {
			System.err.println("Error during order status get: " + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}
}
