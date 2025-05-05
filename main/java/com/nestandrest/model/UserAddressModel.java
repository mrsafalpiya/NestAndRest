package com.nestandrest.model;

public class UserAddressModel {
	private int userAddressId;
	private int userId;
	private String address;
	private String userName;
	private String phoneNumber;
	private boolean isDefault;

	public UserAddressModel() {
		super();
	}

	public UserAddressModel(int userId, String address, String userName, String phoneNumber, boolean isDefault) {
		super();
		this.userId = userId;
		this.address = address;
		this.userName = userName;
		this.phoneNumber = phoneNumber;
		this.isDefault = isDefault;
	}

	public UserAddressModel(int userAddressId, int userId, String address, boolean isDefault) {
		super();
		this.userAddressId = userAddressId;
		this.userId = userId;
		this.address = address;
		this.isDefault = isDefault;
	}

	// Getters and Setters
	public int getUserAddressId() {
		return userAddressId;
	}

	public void setUserAddressId(int userAddressId) {
		this.userAddressId = userAddressId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public boolean isDefault() {
		return isDefault;
	}

	public void setDefault(boolean isDefault) {
		this.isDefault = isDefault;
	}
}
