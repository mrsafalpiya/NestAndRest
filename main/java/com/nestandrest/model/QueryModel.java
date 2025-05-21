package com.nestandrest.model;

import java.sql.Timestamp;

/**
 * Represents the model for a user query.
 * 
 * This class contains fields and methods to manage user query details such as
 * the query ID, user information, subject, message, and creation timestamp.
 * 
 * @author 23049063 Himani Chaudhary
 */
public class QueryModel {
	// Unique identifier for the query
	private int queryId;

	// Name of the user who submitted the query
	private String userName;

	// Email of the user who submitted the query
	private String userEmail;

	// Subject of the query
	private String subject;

	// Message content of the query
	private String message;

	// Timestamp when the query was created
	private Timestamp createdAt;

	/**
	 * Constructor to initialize a query with user details and message.
	 * 
	 * @param userName  the name of the user
	 * @param userEmail the email of the user
	 * @param subject   the subject of the query
	 * @param text      the message content of the query
	 */
	public QueryModel(String userName, String userEmail, String subject, String text) {
		super();
		this.userName = userName;
		this.userEmail = userEmail;
		this.subject = subject;
		this.message = text;
	}

	/**
	 * Constructor to initialize all fields of the QueryModel.
	 * 
	 * @param queryId   the ID of the query
	 * @param userName  the name of the user
	 * @param userEmail the email of the user
	 * @param subject   the subject of the query
	 * @param text      the message content of the query
	 * @param createdAt the timestamp when the query was created
	 */
	public QueryModel(int queryId, String userName, String userEmail, String subject, String text,
			Timestamp createdAt) {
		super();
		this.queryId = queryId;
		this.userName = userName;
		this.userEmail = userEmail;
		this.subject = subject;
		this.message = text;
		this.setCreatedAt(createdAt);
	}

	/**
	 * Gets the ID of the query.
	 * 
	 * @return the query ID
	 */
	public int getQueryId() {
		return queryId;
	}

	/**
	 * Sets the ID of the query.
	 * 
	 * @param queryId the query ID to set
	 */
	public void setQueryId(int queryId) {
		this.queryId = queryId;
	}

	/**
	 * Gets the name of the user who submitted the query.
	 * 
	 * @return the user's name
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * Sets the name of the user who submitted the query.
	 * 
	 * @param userName the user's name to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * Gets the email of the user who submitted the query.
	 * 
	 * @return the user's email
	 */
	public String getUserEmail() {
		return userEmail;
	}

	/**
	 * Sets the email of the user who submitted the query.
	 * 
	 * @param userEmail the user's email to set
	 */
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	/**
	 * Gets the subject of the query.
	 * 
	 * @return the query subject
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * Sets the subject of the query.
	 * 
	 * @param subject the query subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * Gets the message content of the query.
	 * 
	 * @return the query message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Sets the message content of the query.
	 * 
	 * @param message the query message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * Gets the timestamp when the query was created.
	 * 
	 * @return the creation timestamp
	 */
	public Timestamp getCreatedAt() {
		return createdAt;
	}

	/**
	 * Sets the timestamp when the query was created.
	 * 
	 * @param createdAt the creation timestamp to set
	 */
	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
}
