package com.nestandrest.model;

/**
 * Represents the model for a category.
 * 
 * @author 23048460 Safal Piya
 */
public class CategoryModel {
	// Unique identifier for the category
	private int categoryId;

	// Name of the category
	private String name;

	// Description of the category
	private String description;

	/**
	 * Default constructor for CategoryModel.
	 */
	public CategoryModel() {
		// No-argument constructor
	}

	/**
	 * Constructor to initialize all fields of the CategoryModel.
	 * 
	 * @param categoryId  the ID of the category
	 * @param name        the name of the category
	 * @param description the description of the category
	 */
	public CategoryModel(int categoryId, String name, String description) {
		super();
		this.categoryId = categoryId;
		this.name = name;
		this.description = description;
	}

	/**
	 * Gets the ID of the category.
	 * 
	 * @return the category ID
	 */
	public int getCategoryId() {
		return categoryId;
	}

	/**
	 * Sets the ID of the category.
	 * 
	 * @param categoryId the category ID to set
	 */
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	/**
	 * Gets the name of the category.
	 * 
	 * @return the category name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the category.
	 * 
	 * @param name the category name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the description of the category.
	 * 
	 * @return the category description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description of the category.
	 * 
	 * @param description the category description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
}