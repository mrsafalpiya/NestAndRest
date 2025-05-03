package com.nestandrest.model;

public class UserModel {
	private int userId;
	private String name;
	private String email;
	private String phone;
	private int genderId;
	private String password;
	private int roleId;
	private String address;         
	private String profileImage; 
	
    
	public UserModel() {
		super();
	}

	public UserModel(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

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
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the genderId
	 */
	public int getGenderId() {
		return genderId;
	}

	/**
	 * @param genderId the genderId to set
	 */
	public void setGenderId(int genderId) {
		this.genderId = genderId;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the roleId
	 */
	public int getRoleId() {
		return roleId;
	}

	/**
	 * @param roleId the roleId to set
	 */
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	
	/**
	 * @return the profileImage
	 */
	public String getProfileImage() {
		return profileImage;
	}
	
	/**
	 * @param profileImage the profileImage to set
	 */
	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}
	
}
