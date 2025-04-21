package com.nestandrest.model;

public class GenderModel {
	private int genderId;
	private String name;

	public GenderModel(int genderId, String name) {
		super();
		this.genderId = genderId;
		this.name = name;
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

}
