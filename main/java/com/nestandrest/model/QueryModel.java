package com.nestandrest.model;

import java.sql.Timestamp;

public class QueryModel {
	private int queryId;
	private String userName;
	private String userEmail;
	private String subject;
	private String message;
	private Timestamp createdAt;

	public QueryModel(String userName, String userEmail, String subject, String text) {
		super();
		this.userName = userName;
		this.userEmail = userEmail;
		this.subject = subject;
		this.message = text;
	}

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

	public int getQueryId() {
		return queryId;
	}

	public void setQueryId(int queryId) {
		this.queryId = queryId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
}
