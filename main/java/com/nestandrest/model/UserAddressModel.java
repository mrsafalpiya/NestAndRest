package com.nestandrest.model;

/**
 * Represents the model for a user's address.
 * 
 * This class contains fields and methods to manage user address details such as
 * the address ID, user ID, address string, user name, phone number, and default
 * status.
 * 
 * @author 23047589 Sanniva Shakya
 */
public class UserAddressModel {
	// Unique identifier for the user address
	private int userAddressId;

	// Identifier for the user associated with this address
	private int userId;

	// The address string
	private String address;

	// Name of the user associated with this address
	private String userName;

	// Phone number associated with this address
	private String phoneNumber;

	// Indicates whether this is the default address
	private boolean isDefault;

	/**
	 * Default constructor for UserAddressModel.
	 */
	public UserAddressModel() {
		super();
	}

	/**
	 * Constructor to initialize all fields of the UserAddressModel.
	 * 
	 * @param userId      the ID of the user
	 * @param address     the address string
	 * @param userName    the name of the user
	 * @param phoneNumber the phone number associated with the address
	 * @param isDefault   whether this is the default address
	 */
	public UserAddressModel(int userId, String address, String userName, String phoneNumber, boolean isDefault) {
		super();
		this.userId = userId;
		this.address = address;
		this.userName = userName;
		this.phoneNumber = phoneNumber;
		this.isDefault = isDefault;
	}

	/**
	 * Constructor to initialize fields of the UserAddressModel with address ID.
	 * 
	 * @param userAddressId the unique ID of the user address
	 * @param userId        the ID of the user
	 * @param address       the address string
	 * @param isDefault     whether this is the default address
	 */
	public UserAddressModel(int userAddressId, int userId, String address, boolean isDefault) {
		super();
		this.userAddressId = userAddressId;
		this.userId = userId;
		this.address = address;
		this.isDefault = isDefault;
	}

	/**
	 * Gets the unique ID of this user address.
	 * 
	 * @return the user address ID
	 */
	public int getUserAddressId() {
		return userAddressId;
	}

	/**
	 * Sets the unique ID of this user address.
	 * 
	 * @param userAddressId the user address ID to set
	 */
	public void setUserAddressId(int userAddressId) {
		this.userAddressId = userAddressId;
	}

	/**
	 * Gets the ID of the user this address belongs to.
	 * 
	 * @return the user ID
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * Sets the ID of the user this address belongs to.
	 * 
	 * @param userId the user ID to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * Gets the address string.
	 * 
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Sets the address string.
	 * 
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * Gets the user name associated with this address.
	 * 
	 * @return the user name
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * Sets the user name associated with this address.
	 * 
	 * @param userName the user name to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * Gets the phone number associated with this address.
	 * 
	 * @return the phone number
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * Sets the phone number associated with this address.
	 * 
	 * @param phoneNumber the phone number to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * Checks if this is the default address.
	 * 
	 * @return true if this is the default address, false otherwise
	 */
	public boolean isDefault() {
		return isDefault;
	}

	/**
	 * Sets whether this is the default address.
	 * 
	 * @param isDefault true to set this as the default address, false otherwise
	 */
	public void setDefault(boolean isDefault) {
		this.isDefault = isDefault;
	}
}
