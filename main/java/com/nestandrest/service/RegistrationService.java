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

public class RegistrationService {

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
