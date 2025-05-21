package com.nestandrest.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.nestandrest.config.DbConfig;
import com.nestandrest.model.GenderModel;
import com.nestandrest.model.UserModel;

/**
 * Service class that handles user registration operations. Provides methods to
 * add new users and retrieve user-related information.
 * 
 * @author 23049063 Himani Chaudhary
 */
public class RegistrationService {

	// Database connection
	private Connection dbConn;

	/**
	 * Constructor initializes the database connection.
	 */
	public RegistrationService() {
		try {
			this.dbConn = DbConfig.getDbConnection();
		} catch (SQLException | ClassNotFoundException ex) {
			System.err.println("Database connection error: " + ex.getMessage());
			ex.printStackTrace();
		}
	}

	/**
	 * Adds a new user to the system.
	 * 
	 * @param userModel the user model containing user information
	 * @return Boolean indicating success or failure, or null if an error occurs
	 */
	public Boolean addUser(UserModel userModel) {
		if (dbConn == null) {
			System.err.println("Database connection is not available.");
			return null;
		}

		String insertQuery = "INSERT INTO `user` (`user_id`, `name`, `email`, `phone`, `gender_id`, `password`, `role_id`) VALUES (NULL, ?, ?, ?, ?, ?, ?);";

		try {
			PreparedStatement insertStmt = dbConn.prepareStatement(insertQuery);
			// Insert student details
			insertStmt.setString(1, userModel.getName());
			insertStmt.setString(2, userModel.getEmail());
			insertStmt.setString(3, userModel.getPhone());
			insertStmt.setInt(4, userModel.getGenderId());
			insertStmt.setString(5, userModel.getPassword());
			insertStmt.setInt(6, userModel.getRoleId());

			return insertStmt.executeUpdate() > 0;
		} catch (SQLException e) {
			System.err.println("Error during user registration: " + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Retrieves the list of genders from the database.
	 * 
	 * @return List of GenderModel objects, or null if an error occurs
	 */
	public List<GenderModel> getGenders() {
		if (dbConn == null) {
			System.err.println("Database connection is not available!");
			return null;
		}

		String query = "SELECT * FROM gender";

		try {
			PreparedStatement genderStmt = dbConn.prepareStatement(query);
			ResultSet result = genderStmt.executeQuery();

			List<GenderModel> genders = new ArrayList<GenderModel>();

			while (result.next()) {
				genders.add(new GenderModel(result.getInt("gender_id"), result.getString("name")));
			}

			return genders;
		} catch (SQLException e) {
			System.err.println("Error during student registration: " + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Retrieves the role ID for customers.
	 * 
	 * @return Customer role ID, or 0 if an error occurs
	 */
	public int getCustomerRoleId() {
		if (dbConn == null) {
			System.err.println("Database connection is not available!");
			return 0;
		}

		String query = "SELECT * FROM role";

		try {
			PreparedStatement roleStmt = dbConn.prepareStatement(query);
			ResultSet result = roleStmt.executeQuery();

			int customerRoleId = 0;

			while (result.next()) {
				customerRoleId = result.getInt("role_id");
			}

			return customerRoleId;
		} catch (SQLException e) {
			System.err.println("Error during customer ID fetch: " + e.getMessage());
			e.printStackTrace();
			return 0;
		}
	}

	/**
	 * Checks if a user with the given email exists in the system.
	 * 
	 * @param email the email to check
	 * @return Boolean indicating existence, or null if an error occurs
	 */
	public Boolean doesAUserWithEmailExist(String email) {
		if (dbConn == null) {
			System.err.println("Database connection is not available!");
			return null;
		}

		String query = "SELECT * FROM user WHERE email = ?";

		try {
			PreparedStatement roleStmt = dbConn.prepareStatement(query);
			roleStmt.setString(1, email.toLowerCase());
			ResultSet result = roleStmt.executeQuery();

			if (result.next()) {
				return true;
			}
			return false;
		} catch (SQLException e) {
			System.err.println("Error during email fetch: " + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Checks if a user with the given phone number exists in the system.
	 * 
	 * @param phone the phone number to check
	 * @return Boolean indicating existence, or null if an error occurs
	 */
	public Boolean doesAUserWithPhoneNumberExist(String phone) {
		if (dbConn == null) {
			System.err.println("Database connection is not available!");
			return null;
		}

		String query = "SELECT * FROM user WHERE phone = ?";

		try {
			PreparedStatement roleStmt = dbConn.prepareStatement(query);
			roleStmt.setString(1, phone);
			ResultSet result = roleStmt.executeQuery();

			if (result.next()) {
				return true;
			}
			return false;
		} catch (SQLException e) {
			System.err.println("Error during phone number fetch: " + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}
}
