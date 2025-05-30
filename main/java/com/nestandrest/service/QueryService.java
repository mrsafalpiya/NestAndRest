package com.nestandrest.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.nestandrest.config.DbConfig;
import com.nestandrest.model.QueryModel;

/**
 * Service class that handles user query operations. Provides methods to save
 * and retrieve user queries.
 * 
 * @author 23049063 Himani Chaudhary
 */
public class QueryService {

	// Database connection
	private Connection dbConn;

	/**
	 * Constructor initializes the database connection.
	 */
	public QueryService() {
		try {
			this.dbConn = DbConfig.getDbConnection();
		} catch (SQLException | ClassNotFoundException ex) {
			System.err.println("Database connection error: " + ex.getMessage());
			ex.printStackTrace();
		}
	}

	/**
	 * Saves a new user query to the database.
	 * 
	 * @param queryModel the query model containing user information and message
	 * @return Boolean indicating success or failure, or null if an error occurs
	 */
	public Boolean saveQuery(QueryModel queryModel) {
		if (dbConn == null) {
			System.err.println("Database connection is not available.");
			return null;
		}

		String insertQuery = "INSERT INTO `query` (`user_name`, `user_email`, `subject`, `message`) VALUES (?, ?, ?, ?);";

		try {
			PreparedStatement insertStmt = dbConn.prepareStatement(insertQuery);
			// Insert query details
			insertStmt.setString(1, queryModel.getUserName());
			insertStmt.setString(2, queryModel.getUserEmail());
			insertStmt.setString(3, queryModel.getSubject());
			insertStmt.setString(4, queryModel.getMessage());

			return insertStmt.executeUpdate() > 0;
		} catch (SQLException e) {
			System.err.println("Error during query add: " + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Retrieves all user queries from the database.
	 * 
	 * @return List of QueryModel objects representing user queries, or null if an
	 *         error occurs
	 */
	public List<QueryModel> getAllQueries() {
		if (dbConn == null) {
			System.err.println("Database connection is not available!");
			return null;
		}

		String query = "SELECT * FROM query";

		try {
			PreparedStatement stmt = dbConn.prepareStatement(query);
			ResultSet result = stmt.executeQuery();

			List<QueryModel> queries = new ArrayList<QueryModel>();

			while (result.next()) {
				QueryModel queryEntry = new QueryModel(result.getInt("query_id"), result.getString("user_name"),
						result.getString("user_email"), result.getString("subject"), result.getString("message"),
						result.getTimestamp("created_at"));
				queries.add(queryEntry);
			}

			return queries;
		} catch (SQLException e) {
			System.err.println("Error during queries get: " + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}
}
