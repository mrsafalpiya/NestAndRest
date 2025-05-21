package com.nestandrest.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.nestandrest.config.DbConfig;
import com.nestandrest.model.UserModel;
import com.nestandrest.util.PasswordUtil;

/**
 * Service class that handles user login operations. Provides methods to
 * authenticate users and retrieve user roles.
 * 
 * @author 23049063 Himani Chaudhary
 */
public class LoginService {
	// Database connection
	private Connection dbConn;

	/**
	 * Constructor initializes the database connection.
	 */
	public LoginService() {
		try {
			this.dbConn = DbConfig.getDbConnection();
		} catch (SQLException | ClassNotFoundException ex) {
			System.err.println("Database connection error: " + ex.getMessage());
			ex.printStackTrace();
		}
	}

	/**
	 * Authenticates a user and returns their role.
	 * 
	 * @param userModel the user model containing email and password for
	 *                  authentication
	 * @return the role name of the authenticated user, or null if authentication
	 *         fails
	 */
	public String loginUser(UserModel userModel) {
		if (dbConn == null) {
			System.err.println("Database connection is not available.");
			return null;
		}

		String query = "SELECT user.name, user.email, user.password, role.name as role_name FROM user JOIN role ON user.role_id = role.role_id WHERE email = ?";
		try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
			stmt.setString(1, userModel.getEmail());
			ResultSet result = stmt.executeQuery();

			if (result.next()) {
				if (!validatePassword(result, userModel)) {
					return null;
				}
				String role = result.getString("role_name");
				return role;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return null;
	}

	/**
	 * Validates the password retrieved from the database.
	 *
	 * @param result    the ResultSet containing the email and password from the
	 *                  database
	 * @param userModel the StudentModel object containing user credentials
	 * @return true if the passwords match, false otherwise
	 * @throws SQLException if a database access error occurs
	 */
	private boolean validatePassword(ResultSet result, UserModel userModel) throws SQLException {
		String dbEmail = result.getString("email");
		String dbPassword = result.getString("password");

		return dbEmail.equals(userModel.getEmail())
				&& PasswordUtil.decrypt(dbPassword, dbEmail).equals(userModel.getPassword());
	}
}
