package com.nestandrest.service;

import com.nestandrest.model.UserModel;
import com.nestandrest.util.CookiesUtil;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

import com.nestandrest.model.UserAddressModel;

import com.nestandrest.config.DbConfig;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserService {

	private static final Logger logger = Logger.getLogger(UserService.class.getName());
	private Connection dbConn;

	/**
	 * Constructor initializes the database connection.
	 */
	public UserService() {
		try {
			this.dbConn = DbConfig.getDbConnection();
		} catch (SQLException | ClassNotFoundException ex) {
			System.err.println("Database connection error: " + ex.getMessage());
			ex.printStackTrace();
		}
	}

	/**
	 * 
	 * @param request
	 * @param customEmail Optionally provide the email of the user (useful in edit
	 *                    user profile when the user just updated the email)
	 * @return
	 */
	public UserModel getCurrentlyLoggedInUser(HttpServletRequest request, String customEmail) {
		String email = customEmail;

		if (email == null) {
			Cookie currentUserEmailCookie = CookiesUtil.getCookie(request, "email");
			if (currentUserEmailCookie == null) {
				return null;
			}
			email = currentUserEmailCookie.getValue();
		}

		return getUserByEmail(email);
	}

	/**
	 * Retrieves a list of users from the database with pagination.
	 *
	 * @param offset starting point in the database
	 * @param limit  number of records to fetch
	 * @return list of UserModel objects
	 */
	public List<UserModel> getUsers(int offset, int limit) {
		List<UserModel> users = new ArrayList<>();
		String query = "SELECT * FROM user LIMIT ? OFFSET ?";

		try (Connection conn = DbConfig.getDbConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {

			stmt.setInt(1, limit);
			stmt.setInt(2, offset);
			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					UserModel user = new UserModel();
					user.setUserId(rs.getInt("user_id"));
					user.setName(rs.getString("name"));
					user.setEmail(rs.getString("email"));
					user.setPhone(rs.getString("phone"));
					user.setGenderId(rs.getInt("gender_id"));
					user.setRoleId(rs.getInt("role_id"));
					users.add(user);
				}
			}
		} catch (SQLException | ClassNotFoundException e) {
			logger.log(Level.SEVERE, "Error while fetching users", e);
		}
		return users;
	}

	/**
	 * Gets the total count of users in the database.
	 *
	 * @return total number of users
	 */
	public int getUserCount() {
		String query = "SELECT COUNT(*) FROM user";
		try (Connection conn = DbConfig.getDbConnection();
				PreparedStatement stmt = conn.prepareStatement(query);
				ResultSet rs = stmt.executeQuery()) {

			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException | ClassNotFoundException e) {
			logger.log(Level.SEVERE, "Error while counting users", e);
		}
		return 0;
	}

	/**
	 * Retrieves a user by their ID.
	 *
	 * @param userId ID of the user
	 * @return UserModel object if found, otherwise null
	 */

	/** Retrieves a user by their ID. */
	public UserModel getUserById(int userId) {
		String query = "SELECT * FROM user WHERE user_id = ?";
		try (Connection conn = DbConfig.getDbConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
			stmt.setInt(1, userId);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				UserModel user = new UserModel();
				user.setUserId(rs.getInt("user_id"));
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
				user.setPhone(rs.getString("phone"));
				user.setGenderId(rs.getInt("gender_id"));
				user.setRoleId(rs.getInt("role_id"));
				return user;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Retrieves a user by their email.
	 *
	 * @param userEmail Email of the user
	 * @return UserModel object if found, otherwise null
	 */

	/** Retrieves a user by their ID. */
	public UserModel getUserByEmail(String userEmail) {
		String query = "SELECT * FROM user WHERE email = ?";
		try (Connection conn = DbConfig.getDbConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
			stmt.setString(1, userEmail);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				UserModel user = new UserModel();
				user.setUserId(rs.getInt("user_id"));
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
				user.setPhone(rs.getString("phone"));
				user.setGenderId(rs.getInt("gender_id"));
				user.setRoleId(rs.getInt("role_id"));
				return user;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<UserAddressModel> getAddressByUserId(int userId) {
		String query = "SELECT * FROM user_address WHERE user_id = ?";
		try (Connection conn = DbConfig.getDbConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {

			stmt.setInt(1, userId);
			ResultSet rs = stmt.executeQuery();

			List<UserAddressModel> addresses = new ArrayList<UserAddressModel>();

			while (rs.next()) {
				UserAddressModel address = new UserAddressModel();
				address.setUserAddressId(rs.getInt("user_address_id"));
				address.setUserId(rs.getInt("user_id"));
				address.setAddress(rs.getString("address"));
				address.setDefault(rs.getBoolean("is_default"));
				addresses.add(address);
			}

			return addresses;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Searches for users whose name or email contains the given search term, with
	 * pagination.
	 *
	 * @param searchTerm the term to search for (matched anywhere in name or email)
	 * @param offset     zero-based index of the first record to return
	 * @param limit      maximum number of records to return
	 * @return list of UserModel objects matching the search criteria
	 */
	public List<UserModel> searchUsers(String searchTerm, int offset, int limit) {
		String sql = "SELECT * FROM user WHERE name LIKE ? OR email LIKE ? LIMIT ? OFFSET ?";
		List<UserModel> list = new ArrayList<>();
		try (Connection c = DbConfig.getDbConnection(); PreparedStatement p = c.prepareStatement(sql)) {
			String pattern = "%" + searchTerm + "%";
			p.setString(1, pattern);
			p.setString(2, pattern);
			p.setInt(3, limit);
			p.setInt(4, offset);
			try (ResultSet rs = p.executeQuery()) {
				while (rs.next()) {
					UserModel u = new UserModel();
					u.setUserId(rs.getInt("user_id"));
					u.setName(rs.getString("name"));
					u.setEmail(rs.getString("email"));
					u.setPhone(rs.getString("phone"));
					u.setRoleId(rs.getInt("role_id"));
					list.add(u);
				}
			}
		} catch (SQLException | ClassNotFoundException e) {
			logger.log(Level.SEVERE, "Error in searchUsers", e);
		}
		return list;
	}

	/**
	 * Counts how many users whose name or email contains the given search term.
	 * Used to compute total pages when searching.
	 *
	 * @param searchTerm the term to search for (matched anywhere in name or email)
	 * @return the total number of matching user records
	 */
	public int countSearchUsers(String searchTerm) {
		String sql = "SELECT COUNT(*) FROM user WHERE name LIKE ? OR email LIKE ?";
		try (Connection c = DbConfig.getDbConnection(); PreparedStatement p = c.prepareStatement(sql)) {
			String pattern = "%" + searchTerm + "%";
			p.setString(1, pattern);
			p.setString(2, pattern);
			try (ResultSet rs = p.executeQuery()) {
				if (rs.next()) {
					return rs.getInt(1);
				}
			}
		} catch (SQLException | ClassNotFoundException e) {
			logger.log(Level.SEVERE, "Error in countSearchUsers", e);
		}
		return 0;
	}

	/**
	 * Updates an existing user's information in the database.
	 *
	 * @param user a UserModel object containing the updated user data
	 * @return true if the update was successful, false otherwise
	 *
	 */
	public boolean updateUser(UserModel user) {
		String updateUserQuery = "UPDATE user SET name = ?, email = ?, phone = ?, gender_id = ?, role_id = ? WHERE user_id = ?";

		try (Connection conn = DbConfig.getDbConnection();
				PreparedStatement stmt = conn.prepareStatement(updateUserQuery)) {

			// Setting the parameters correctly for the prepared statement
			stmt.setString(1, user.getName());
			stmt.setString(2, user.getEmail());
			stmt.setString(3, user.getPhone());
			stmt.setInt(4, user.getGenderId()); // Add gender_id
			stmt.setInt(5, user.getRoleId()); // Add role_id
			stmt.setInt(6, user.getUserId()); // Set user_id

			// Execute the update
			int rowsUpdated = stmt.executeUpdate();

			// Check if the update was successful
			return rowsUpdated > 0;

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Updates the address of a user in the user_address table based on the provided
	 * user ID.
	 *
	 * @param address A UserAddressModel object containing the new address and the
	 *                associated user ID.
	 * @return true if the update was successful (at least one row affected), false
	 *         otherwise.
	 */
	public boolean updateUserAddress(UserAddressModel address) {
		String query = "UPDATE user_address SET address = ? WHERE user_id = ?";
		try (Connection conn = DbConfig.getDbConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {

			stmt.setString(1, address.getAddress());
			stmt.setInt(2, address.getUserId());

			int rowsUpdated = stmt.executeUpdate();
			return rowsUpdated > 0;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Updates an existing user's password in the database.
	 *
	 * @param user a UserModel object containing the updated user data
	 * @return true if the update was successful, false otherwise
	 *
	 */
	public boolean updateUserPassword(UserModel user) {
		String updateUserQuery = "UPDATE user SET password = ? WHERE user_id = ?";

		try (Connection conn = DbConfig.getDbConnection();
				PreparedStatement stmt = conn.prepareStatement(updateUserQuery)) {

			// Setting the parameters correctly for the prepared statement
			stmt.setString(1, user.getPassword());
			stmt.setInt(2, user.getUserId()); // Set user_id

			// Execute the update
			int rowsUpdated = stmt.executeUpdate();

			// Check if the update was successful
			return rowsUpdated > 0;

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Deletes a user from the database based on the provided userId.
	 * 
	 * @param userId the ID of the user to be deleted
	 * @return true if the user was successfully deleted, false otherwise
	 */
	public boolean deleteUserById(int userId) throws SQLException, ClassNotFoundException {
		try (Connection connection = DbConfig.getDbConnection()) {
			// Assuming cascading deletes are in place for foreign keys
			String sql = "DELETE FROM user WHERE user_id = ?";

			try (PreparedStatement stmt = connection.prepareStatement(sql)) {
				stmt.setInt(1, userId);
				int rowsAffected = stmt.executeUpdate();
				System.out.println("Rows affected: " + rowsAffected); // Debugging line
				return rowsAffected > 0;

			}
		}
	}

	public List<UserAddressModel> getAllUserAddresses(int userId) {
		String sql = "SELECT * FROM user_address WHERE user_id = ? ORDER BY is_default DESC, user_address_id DESC";
		List<UserAddressModel> list = new ArrayList<>();
		try (Connection c = DbConfig.getDbConnection(); PreparedStatement p = c.prepareStatement(sql)) {
			p.setInt(1, userId);
			try (ResultSet rs = p.executeQuery()) {
				while (rs.next()) {
					UserAddressModel u = new UserAddressModel(rs.getInt("user_address_id"), rs.getInt("user_id"),
							rs.getString("address"), rs.getInt("is_default") == 1);
					u.setUserName(rs.getString("user_name"));
					u.setPhoneNumber(rs.getString("phone_number"));
					list.add(u);
				}
			}
		} catch (SQLException | ClassNotFoundException e) {
			logger.log(Level.SEVERE, "Error in get user addresses", e);
		}
		return list;
	}

	public Boolean addAddressOfUser(UserAddressModel userAddress) {
		if (dbConn == null) {
			System.err.println("Database connection is not available.");
			return null;
		}

		String insertQuery = "INSERT INTO `user_address` (`user_id`, `address`, `user_name`, `phone_number`, `is_default`) VALUES (?, ?, ?, ?, ?);";

		try {
			PreparedStatement insertStmt = dbConn.prepareStatement(insertQuery);
			// Insert address details
			insertStmt.setInt(1, userAddress.getUserId());
			insertStmt.setString(2, userAddress.getAddress());
			insertStmt.setString(3, userAddress.getUserName());
			insertStmt.setString(4, userAddress.getPhoneNumber());
			insertStmt.setInt(5, userAddress.isDefault() ? 1 : 0);

			return insertStmt.executeUpdate() > 0;
		} catch (SQLException e) {
			System.err.println("Error during user address add: " + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}
}
