package com.nestandrest.model;

/**
 * Represents the model for gender.
 * 
 * @author 23049063 Himani Chaudhary
 */
public class GenderModel {
	// Unique identifier for the gender
	private int genderId;

	// Name of the gender
	private String name;

	/**
	 * Constructor to initialize all fields of the GenderModel.
	 * 
	 * @param genderId the ID of the gender
	 * @param name     the name of the gender
	 */
	public GenderModel(int genderId, String name) {
		super();
		this.genderId = genderId;
		this.name = name;
	}

	/**
	 * Gets the ID of the gender.
	 * 
	 * @return the gender ID
	 */
	public int getGenderId() {
		return genderId;
	}

	/**
	 * Sets the ID of the gender.
	 * 
	 * @param genderId the gender ID to set
	 */
	public void setGenderId(int genderId) {
		this.genderId = genderId;
	}

	/**
	 * Gets the name of the gender.
	 * 
	 * @return the gender name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the gender.
	 * 
	 * @param name the gender name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
}
