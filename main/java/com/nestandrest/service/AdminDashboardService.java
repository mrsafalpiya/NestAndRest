package com.nestandrest.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.nestandrest.config.DbConfig;
import com.nestandrest.model.GenderModel;
import com.nestandrest.model.Sale;
import com.nestandrest.model.UserModel;

/**
 * Service class that handles admin dashboard related operations. Provides
 * methods to retrieve sales data and statistics.
 * 
 * @author 23047626 Ayush Shrestha
 */
public class AdminDashboardService {

	// Database connection
	private Connection dbConn;

	/**
	 * Constructor initializes the database connection.
	 */
	public AdminDashboardService() {
		try {
			this.dbConn = DbConfig.getDbConnection();
		} catch (SQLException | ClassNotFoundException ex) {
			System.err.println("Database connection error: " + ex.getMessage());
			ex.printStackTrace();
		}
	}

	/**
	 * Retrieves the sales data for the last 7 days.
	 * 
	 * @return ArrayList of Sale objects containing date and sales count, or null if
	 *         an error occurs
	 */
	public ArrayList<Sale> getSalesOfLast7Days() {
		if (dbConn == null) {
			System.err.println("Database connection is not available.");
			return null;
		}

		ArrayList<Sale> sales = new ArrayList<Sale>();

		String query = "SELECT DATE(order_date) AS order_day, COUNT(*) AS total_orders FROM `order` WHERE order_date >= DATE_SUB(CURDATE(), INTERVAL 7 DAY) GROUP BY DATE(order_date) ORDER BY order_day ASC;";
		try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
			ResultSet result = stmt.executeQuery();

			while (result.next()) {
				String date = result.getString("order_day");
				int sales_date = result.getInt("total_orders");
				Sale current_date_sale = new Sale();
				current_date_sale.date = date;
				current_date_sale.sales = sales_date;
				sales.add(current_date_sale);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return sales;
	}

	/**
	 * Gets the total number of sales from all orders.
	 * 
	 * @return Integer representing the total number of sales, or null if an error
	 *         occurs
	 */
	public Integer getTotalSales() {
		if (dbConn == null) {
			System.err.println("Database connection is not available.");
			return null;
		}

		int totalSales = 0;

		String query = "SELECT COUNT(*) AS total_sales FROM `order`;";
		try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
			ResultSet result = stmt.executeQuery();

			if (result.next()) {
				totalSales = result.getInt("total_sales");
				return totalSales;
			}
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
