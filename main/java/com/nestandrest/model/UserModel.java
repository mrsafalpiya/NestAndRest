package com.nestandrest.model;

/**
 * Represents the model for a user.
 * 
 * This class contains fields and methods to manage user details such as user
 * ID, name, email, phone, gender, password, role, address, and profile image.
 * 
 * @author 23049063 Himani Chaudhary
 * @author 23048460 Safal Piya
 * @author 23047589 Sanniva Shakya
 */
public class UserModel {
	// Unique identifier for the user
	private int userId;

	// Name of the user
	private String name;

	// Email address of the user
	private String email;

	// Phone number of the user
	private String phone;

	// Identifier for the gender of the user
	private int genderId;

	// Password of the user
	private String password;

	// Role identifier for the user
	private int roleId;

	// Address of the user
	private String address;

	// Profile image of the user
	private String profileImage;

	/**
	 * Default constructor for UserModel.
	 */
	public UserModel() {
		super();
	}

	/**
	 * Constructor to initialize user with email and password.
	 * 
	 * @param email    the email of the user
	 * @param password the password of the user
	 */
	public UserModel(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	/**
	 * Constructor to initialize user with name, email, phone, gender, password, and
	 * role.
	 * 
	 * @param name     the name of the user
	 * @param email    the email of the user
	 * @param phone    the phone number of the user
	 * @param genderId the gender ID of the user
	 * @param password the password of the user
	 * @param roleId   the role ID of the user
	 */
	public UserModel(String name, String email, String phone, int genderId, String password, int roleId) {
		super();
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.genderId = genderId;
		this.password = password;
		this.roleId = roleId;
	}

	/**
	 * Constructor to initialize user with user ID, name, email, and phone.
	 * 
	 * @param userId the ID of the user
	 * @param name   the name of the user
	 * @param email  the email of the user
	 * @param phone  the phone number of the user
	 */
	public UserModel(int userId, String name, String email, String phone) {
		super();
		this.userId = userId;
		this.name = name;
		this.phone = phone;
	}

	/**
	 * Gets the user ID.
	 * 
	 * @return the user ID
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * Sets the user ID.
	 * 
	 * @param userId the user ID to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * Gets the name of the user.
	 * 
	 * @return the name of the user
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the user.
	 * 
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the first name of the user.
	 * 
	 * @return the first name of the user
	 */
	public String getFirstName() {
		return name.split(" ")[0];
	}

	/**
	 * Gets the last name of the user.
	 * 
	 * @return the last name of the user
	 */
	public String getLastName() {
		return name.split(" ")[1];
	}

	/**
	 * Gets the email of the user.
	 * 
	 * @return the email of the user
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email of the user.
	 * 
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets the phone number of the user.
	 * 
	 * @return the phone number of the user
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * Sets the phone number of the user.
	 * 
	 * @param phone the phone number to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * Gets the gender ID of the user.
	 * 
	 * @return the gender ID of the user
	 */
	public int getGenderId() {
		return genderId;
	}

	/**
	 * Sets the gender ID of the user.
	 * 
	 * @param genderId the gender ID to set
	 */
	public void setGenderId(int genderId) {
		this.genderId = genderId;
	}

	/**
	 * Gets the password of the user.
	 * 
	 * @return the password of the user
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password of the user.
	 * 
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Gets the role ID of the user.
	 * 
	 * @return the role ID of the user
	 */
	public int getRoleId() {
		return roleId;
	}

	/**
	 * Sets the role ID of the user.
	 * 
	 * @param roleId the role ID to set
	 */
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	/**
	 * Gets the address of the user.
	 * 
	 * @return the address of the user
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Sets the address of the user.
	 * 
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * Gets the profile image of the user.
	 * 
	 * @return the profile image of the user
	 */
	public String getProfileImage() {
		return profileImage;
	}

	/**
	 * Sets the profile image of the user.
	 * 
	 * @param profileImage the profile image to set
	 */
	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}
}
