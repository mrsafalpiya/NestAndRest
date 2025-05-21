package com.nestandrest.service;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nestandrest.config.DbConfig;
import com.nestandrest.model.ProductModel;

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
	 * Retrieves the total number of products sold.
	 * 
	 * @return Integer representing the total number of products sold
	 */
	public Integer getProductsSold() {
		if (dbConn == null) {
			System.err.println("Database connection is not available.");
			return null;
		}

		String query = "SELECT SUM(cpvv.qty) AS total_products_sold FROM `order` o LEFT JOIN cart c ON o.cart_id = c.cart_id LEFT JOIN cart_product_variant_value cpvv ON c.cart_id = cpvv.cart_id;";
		try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
			ResultSet result = stmt.executeQuery();

			if (result.next()) {
				int total_sales = result.getInt("total_products_sold");
				return total_sales;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	/**
	 * Retrieves the total sales of all orders.
	 * 
	 * @return Integer representing the total sales of all orders
	 */
	public Integer getTotalSales() {
		if (dbConn == null) {
			System.err.println("Database connection is not available.");
			return null;
		}

		String query = "SELECT SUM(o.total_price) AS total_sales FROM `order` o;";
		try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
			ResultSet result = stmt.executeQuery();

			if (result.next()) {
				int total_sales = result.getInt("total_sales");
				return total_sales;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	/**
	 * Retrieves the sales count by gender.
	 * 
	 * @return Map<String, Integer> representing the sales count by gender
	 */
	public Map<String, Integer> getSalesByGender() {
		if (dbConn == null) {
			System.err.println("Database connection is not available.");
			return null;
		}

		Map<String, Integer> salesByGender = new HashMap<String, Integer>();

		String query = "SELECT SUM(qty) AS qty, g.name AS gender_name FROM `order` o LEFT JOIN cart c ON o.cart_id = c.cart_id LEFT JOIN cart_product_variant_value cpvv ON c.cart_id = cpvv.cart_id LEFT JOIN `user` u ON c.user_id = u.user_id LEFT JOIN gender g ON u.gender_id = g.gender_id GROUP BY g.name;";
		try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
			ResultSet result = stmt.executeQuery();

			while (result.next()) {
				String genderName = result.getString("gender_name");
				int sales = result.getInt("qty");

				salesByGender.put(genderName, sales);
			}

			return salesByGender;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Retrieves the sales by month.
	 * 
	 * @return int[] representing the sales of each month from idx 0 to 11
	 */
	public int[] getYearlySales() {
		if (dbConn == null) {
			System.err.println("Database connection is not available.");
			return null;
		}

		int[] yearlySales = new int[12];
		// Pre-fill sales of all months as 0
		Arrays.fill(yearlySales, 0);

		String query = "SELECT MONTH(o.order_date) AS month, YEAR(o.order_date) AS year, SUM(cpvv.qty * o.total_price) AS total_sales FROM `order` o JOIN cart c ON o.cart_id = c.cart_id JOIN cart_product_variant_value cpvv ON c.cart_id = cpvv.cart_id WHERE o.order_date >= '2025-01-01' AND o.order_date < '2026-01-01' GROUP BY YEAR(o.order_date), MONTH(o.order_date) ORDER BY year, month;";
		try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
			ResultSet result = stmt.executeQuery();

			while (result.next()) {
				int month = result.getInt("month");
				int sales = result.getInt("total_sales");

				yearlySales[month - 1] = sales;
			}

			return yearlySales;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Retrieves the top 5 sold products.
	 * 
	 * @return List<ProductModel> representing the top 5 sold products
	 */
	public List<ProductModel> getTop5SoldProducts() {
		if (dbConn == null) {
			System.err.println("Database connection is not available.");
			return null;
		}

		List<ProductModel> top5SoldProducts = new ArrayList<ProductModel>();

		String query = "SELECT p.product_id, p.name AS product_name, p.price AS product_price, p.discounted_price AS product_discounted_price, SUM(cpvv.qty) AS total_sold FROM `order` o JOIN cart c ON o.cart_id = c.cart_id JOIN cart_product_variant_value cpvv ON c.cart_id = cpvv.cart_id JOIN product p ON cpvv.product_id = p.product_id GROUP BY p.product_id, p.name ORDER BY total_sold DESC LIMIT 5;";
		try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
			ResultSet result = stmt.executeQuery();

			while (result.next()) {
				int productId = result.getInt("product_id");
				String productName = result.getString("product_name");
				double productPrice = result.getDouble("product_price");
				double productDiscountedPrice = result.getDouble("product_discounted_price");
				int totalSold = result.getInt("total_sold");
				
				// Create the product model object
				ProductModel product = new ProductModel();
				product.setProductId(productId);
				product.setName(productName);
				product.setPrice(productPrice);
				product.setDiscountedPrice(productDiscountedPrice);
				product.setCartQty(totalSold);

				top5SoldProducts.add(product);
			}

			return top5SoldProducts;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
