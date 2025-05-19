package com.nestandrest.model;

/**
 * Model class representing a user's address information.
 * Contains fields for address details, associated user, and default status.
 */
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

	/**
	 * @return the unique ID of this user address.
	 */
	public int getUserAddressId() {
		return userAddressId;
	}
	
	/**
	 * @param userAddressId the unique ID of this user address to set.
	 */
	public void setUserAddressId(int userAddressId) {
		this.userAddressId = userAddressId;
	}
	
	/**
	 * @return the ID of the user this address belongs to.
	 */
	public int getUserId() {
		return userId;
	}
	
	/**
	 * @param userId the ID of the user this address belongs to.
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	/**
	 * @return the address string.
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address string to set.
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the user name associated with this address.
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the user name to set for this address.
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the phone number associated with this address.
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber the phone number to set for this address.
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @return true if this is the default address, false otherwise.
	 */
	public boolean isDefault() {
		return isDefault;
	}

	/**
	 * @param isDefault set whether this address is the default.
	 */
	public void setDefault(boolean isDefault) {
		this.isDefault = isDefault;
	}
}
