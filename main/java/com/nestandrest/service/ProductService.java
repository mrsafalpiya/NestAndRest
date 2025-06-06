package com.nestandrest.service;

import com.nestandrest.model.CategoryModel;
import com.nestandrest.model.ProductModel;
import com.nestandrest.model.ProductVariantModel;
import com.nestandrest.model.ProductVariantValueModel;
import com.nestandrest.config.DbConfig;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Service class for managing products, categories, and variants. Provides
 * high-level business logic and database access coordination.
 * 
 * @author 23047626 Ayush Shrestha
 * @author 23047584 Bhumika Karki
 * @author 23048460 Safal Piya
 */
public class ProductService {
	private Connection dbConn;

	/**
	 * Initializes ProductService with DB connection.
	 */
	public ProductService() {
		try {
			this.dbConn = DbConfig.getDbConnection();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Fetches all product categories from the database.
	 *
	 * @return list of Category objects or null if error
	 */
	public List<CategoryModel> getAllCategories() {
		if (dbConn == null) {
			System.err.println("Database connection is not available!");
			return null;
		}

		String query = "SELECT * FROM category";

		try {
			PreparedStatement genderStmt = dbConn.prepareStatement(query);
			ResultSet result = genderStmt.executeQuery();

			List<CategoryModel> categories = new ArrayList<CategoryModel>();

			while (result.next()) {
				categories.add(new CategoryModel(result.getInt("category_id"), result.getString("name"),
						result.getString("description")));
			}

			return categories;
		} catch (SQLException e) {
			System.err.println("Error during products get: " + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Retrieves filtered product list based on search and sort criteria.
	 *
	 * @param searchQuery keyword to search
	 * @param orderBy     sorting criteria: new, cheap, expensive
	 * @return list of ProductModel objects
	 */

	public List<ProductModel> getProducts(String searchQuery, String orderBy) {
		if (dbConn == null) {
			System.err.println("Database connection is not available!");
			return null;
		}

		if (searchQuery == null) {
			searchQuery = "";
		}
		if (orderBy == null) {
			orderBy = "new";
		}

		String orderByDir = "ASC";
		switch (orderBy) {
		case "cheap":
			orderBy = "discounted_price";
			orderByDir = "ASC";
			break;
		case "expensive":
			orderBy = "discounted_price";
			orderByDir = "DESC";
			break;
		case "new":
		default:
			orderBy = "product_id";
			break;
		}

		String query = "SELECT p.*, c.name as category_name FROM product p JOIN category c ON p.category_id = c.category_id WHERE LOWER(p.name) LIKE ? ORDER BY "
				+ orderBy + " " + orderByDir;

		try {
			PreparedStatement genderStmt = dbConn.prepareStatement(query);
			genderStmt.setString(1, "%" + searchQuery + "%");
			ResultSet result = genderStmt.executeQuery();

			List<ProductModel> products = new ArrayList<ProductModel>();

			while (result.next()) {
				products.add(new ProductModel(result.getInt("product_id"), result.getString("name"),
						result.getString("short_description"), result.getString("long_description"),
						result.getDouble("price"), result.getDouble("discounted_price"), result.getInt("category_id"),
						result.getInt("stock_qty"), result.getString("category_name")));
			}

			return products;
		} catch (SQLException e) {
			System.err.println("Error during products get: " + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Gets a single ProductModel by ID (used in detail page).
	 *
	 * @param id product ID
	 * @return ProductModel object or null
	 */
	public ProductModel getById(int id) {
		if (dbConn == null) {
			System.err.println("Database connection is not available!");
			return null;
		}

		String query = "SELECT * FROM product p WHERE p.product_id = ?";

		try {
			PreparedStatement genderStmt = dbConn.prepareStatement(query);
			genderStmt.setInt(1, id);
			ResultSet result = genderStmt.executeQuery();

			if (result.next()) {
				return new ProductModel(result.getInt("product_id"), result.getString("name"),
						result.getString("short_description"), result.getString("long_description"),
						result.getDouble("price"), result.getDouble("discounted_price"), result.getInt("category_id"),
						result.getInt("stock_qty"));
			}

			return null;
		} catch (SQLException e) {
			System.err.println("Error during product get: " + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Retrieves all product variants and their values for a given product.
	 *
	 * @param productId ID of the product
	 * @return list of ProductVariantModel objects
	 */
	public List<ProductVariantModel> getVariantsOfAProduct(int productId) {
		if (dbConn == null) {
			System.err.println("Database connection is not available!");
			return null;
		}

		String query = "SELECT pv.*, pvv.* FROM `product_variant` pv LEFT JOIN `product_variant_value` pvv ON pv.product_variant_id = pvv.product_variant_id LEFT JOIN product p ON pv.product_id = p.product_id WHERE p.product_id = ?";

		try {
			PreparedStatement genderStmt = dbConn.prepareStatement(query);
			genderStmt.setInt(1, productId);
			ResultSet result = genderStmt.executeQuery();

			List<ProductVariantModel> productVariants = new ArrayList<ProductVariantModel>();

			while (result.next()) {
				int productVariantId = result.getInt("product_variant_id");
				String variantName = result.getString("variant_name");
				int productVariantVariantValueId = result.getInt("product_variant_value_id");
				String variantValue = result.getString("variant_value");

				// Check if variant already added
				int addedIdx = -1;
				for (int i = 0; i < productVariants.size(); i++) {
					ProductVariantModel variantEntry = productVariants.get(i);
					if (variantEntry.getProductVariantId() == productVariantId) {
						addedIdx = i;
						break;
					}
				}

				// If not added, create new entry
				if (addedIdx == -1) {
					productVariants.add(new ProductVariantModel(productVariantId, variantName,
							new ArrayList<ProductVariantValueModel>()));
					addedIdx = productVariants.size() - 1;
				}

				List<ProductVariantValueModel> productVariantValues = productVariants.get(addedIdx).getVariantValues();
				productVariantValues.add(
						new ProductVariantValueModel(productVariantVariantValueId, productVariantId, variantValue));

				// Add variant value to existing variant
				productVariants.get(addedIdx).setVariantValues(productVariantValues);
			}

			return productVariants;
		} catch (SQLException e) {
			System.err.println("Error during products get: " + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Updates an existing product in the database with new information.
	 *
	 * @param product The ProductModel object containing updated product details
	 * @return Boolean indicating success (true) or failure (false/null)
	 */
	public Boolean updateProduct(ProductModel product) {
		if (dbConn == null) {
			System.err.println("Database connection is not available!");
			return null;
		}

		String query = "UPDATE product SET name = ?, short_description = ?, long_description = ?, price = ?, discounted_price = ?, category_id = ?, stock_qty = ? WHERE product_id = ?";

		try {
			PreparedStatement stmt = dbConn.prepareStatement(query);
			stmt.setString(1, product.getName());
			stmt.setString(2, product.getShortDescription());
			stmt.setString(3, product.getLongDescription());
			stmt.setDouble(4, product.getPrice());
			stmt.setDouble(5, product.getDiscountedPrice());
			stmt.setInt(6, product.getCategoryId());
			stmt.setInt(7, product.getStockQty());
			stmt.setInt(8, product.getProductId());

			int rowsUpdated = stmt.executeUpdate();
			return rowsUpdated > 0;
		} catch (SQLException e) {
			System.err.println("Error during product get: " + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Retrieves all products that have a discount applied (discounted_price > 0).
	 * These are considered to be on sale.
	 *
	 * @return List of ProductModel objects representing products on sale or null if error
	 */
	public List<ProductModel> getProductsOnSale() {
		if (dbConn == null) {
			System.err.println("Database connection is not available!");
			return null;
		}

		String query = "SELECT p.* FROM product p WHERE discounted_price > 0";

		try {
			PreparedStatement selectStmt = dbConn.prepareStatement(query);
			ResultSet result = selectStmt.executeQuery();

			List<ProductModel> products = new ArrayList<ProductModel>();

			while (result.next()) {
				products.add(new ProductModel(result.getInt("product_id"), result.getString("name"),
						result.getDouble("price"), result.getDouble("discounted_price")));
			}

			return products;
		} catch (SQLException e) {
			System.err.println("Error during products get: " + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Deletes a product by its ID.
	 *
	 * @param productId product ID to delete
	 */
	public void deleteProduct(int productId) {
		try {
			String sql = "DELETE FROM product WHERE product_id = ?";
			PreparedStatement stmt = dbConn.prepareStatement(sql);
			stmt.setInt(1, productId);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Adds a new product to the database along with its variants and variant
	 * values.
	 *
	 * @param product       The ProductModel object containing the product details
	 *                      to be added
	 * @param variantNames  An array of variant names (e.g., "Color", "Size") for
	 *                      the product
	 * @param variantValues An array of variant values corresponding to each variant
	 *                      name (e.g., "Red", "XL")
	 * @return The generated product ID if successful, 0 if failed
	 */
	public int addProduct(ProductModel product, String[] variantNames, String[] variantValues) {
		if (dbConn == null) {
			System.err.println("Database connection is not available!");
			return 0;
		}

		int generatedProductId = 0;

		try {
			// 1. Insert product first
			String productSql = "INSERT INTO product (name, short_description, long_description, price, discounted_price, category_id, stock_qty) VALUES (?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement productStmt = dbConn.prepareStatement(productSql,
					PreparedStatement.RETURN_GENERATED_KEYS);
			productStmt.setString(1, product.getName());
			productStmt.setString(2, product.getShortDescription());
			productStmt.setString(3, product.getLongDescription());
			productStmt.setDouble(4, product.getPrice());
			productStmt.setDouble(5, product.getDiscountedPrice());
			productStmt.setInt(6, product.getCategoryId());
			productStmt.setInt(7, product.getStockQty());

			productStmt.executeUpdate();

			// Get the generated product ID
			ResultSet generatedKeys = productStmt.getGeneratedKeys();
			if (generatedKeys.next()) {
				generatedProductId = generatedKeys.getInt(1);
			}

			// 2. Process variants and variant values if they exist
			if (variantNames != null && variantValues != null && variantNames.length > 0) {
				for (int i = 0; i < variantNames.length; i++) {
					String variantName = variantNames[i];
					if (variantName != null && !variantName.trim().isEmpty()) {
						// Insert the variant
						String variantSql = "INSERT INTO product_variant (product_id, variant_name) VALUES (?, ?)";
						PreparedStatement variantStmt = dbConn.prepareStatement(variantSql,
								PreparedStatement.RETURN_GENERATED_KEYS);
						variantStmt.setInt(1, generatedProductId);
						variantStmt.setString(2, variantName);

						variantStmt.executeUpdate();

						// Get the generated variant ID
						ResultSet variantKeys = variantStmt.getGeneratedKeys();
						if (variantKeys.next()) {
							int variantId = variantKeys.getInt(1);

							// Process variant values if they exist for this variant
							if (variantValues != null && i < variantValues.length) {
								String variantValuesStr = variantValues[i];
								if (variantValuesStr != null && !variantValuesStr.trim().isEmpty()) {
									// Split values by new line
									String[] valueArray = variantValuesStr.split("\\n");
									
									for (String value : valueArray) {
										String trimmedValue = value.trim();
										if (!trimmedValue.isEmpty()) {
											// Insert variant value
											String valuesSql = "INSERT INTO product_variant_value (product_variant_id, variant_value) VALUES (?, ?)";
											PreparedStatement valuesStmt = dbConn.prepareStatement(valuesSql);
											valuesStmt.setInt(1, variantId);
											valuesStmt.setString(2, trimmedValue);
											valuesStmt.executeUpdate();
										}
									}
								}
							}
						}
					}
				}
			}
		} catch (SQLException e) {
			System.err.println("Error during product add: " + e.getMessage());
			e.printStackTrace();
		}

		return generatedProductId;
	}

	/**
	 * Updates the variants and their values for an existing product.
	 * This method first removes all existing variants and values for the product,
	 * then adds the new variants and values.
	 *
	 * @param productId     The ID of the product to update variants for
	 * @param variantNames  An array of variant names (e.g., "Color", "Size") for the product
	 * @param variantValues An array of variant values corresponding to each variant name
	 *                      (e.g., "Red\nBlue\nGreen", "XL\nL\nM\nS")
	 * @return Boolean indicating success (true) or failure (false/null)
	 */
	public Boolean updateProductVariants(int productId, String[] variantNames, String[] variantValues) {
		if (dbConn == null) {
			System.err.println("Database connection is not available!");
			return null;
		}
		
		try {
			// 1. Delete existing variants and their values for this product
			String deleteVariantValuesSql = "DELETE pvv FROM product_variant_value pvv " +
					"JOIN product_variant pv ON pvv.product_variant_id = pv.product_variant_id " +
					"WHERE pv.product_id = ?";
			
			PreparedStatement deleteValuesStmt = dbConn.prepareStatement(deleteVariantValuesSql);
			deleteValuesStmt.setInt(1, productId);
			deleteValuesStmt.executeUpdate();
			
			String deleteVariantsSql = "DELETE FROM product_variant WHERE product_id = ?";
			PreparedStatement deleteVariantsStmt = dbConn.prepareStatement(deleteVariantsSql);
			deleteVariantsStmt.setInt(1, productId);
			deleteVariantsStmt.executeUpdate();
			
			// 2. Process and insert new variants and variant values if they exist
			if (variantNames != null && variantValues != null && variantNames.length > 0) {
				for (int i = 0; i < variantNames.length; i++) {
					String variantName = variantNames[i];
					if (variantName != null && !variantName.trim().isEmpty()) {
						// Insert the variant
						String variantSql = "INSERT INTO product_variant (product_id, variant_name) VALUES (?, ?)";
						PreparedStatement variantStmt = dbConn.prepareStatement(variantSql,
								PreparedStatement.RETURN_GENERATED_KEYS);
						variantStmt.setInt(1, productId);
						variantStmt.setString(2, variantName);

						variantStmt.executeUpdate();

						// Get the generated variant ID
						ResultSet variantKeys = variantStmt.getGeneratedKeys();
						if (variantKeys.next()) {
							int variantId = variantKeys.getInt(1);

							// Process variant values if they exist for this variant
							if (variantValues != null && i < variantValues.length) {
								String variantValuesStr = variantValues[i];
								if (variantValuesStr != null && !variantValuesStr.trim().isEmpty()) {
									// Split values by new line
									String[] valueArray = variantValuesStr.split("\\n");
									
									for (String value : valueArray) {
										String trimmedValue = value.trim();
										if (!trimmedValue.isEmpty()) {
											// Insert variant value
											String valuesSql = "INSERT INTO product_variant_value (product_variant_id, variant_value) VALUES (?, ?)";
											PreparedStatement valuesStmt = dbConn.prepareStatement(valuesSql);
											valuesStmt.setInt(1, variantId);
											valuesStmt.setString(2, trimmedValue);
											valuesStmt.executeUpdate();
										}
									}
								}
							}
						}
					}
				}
			}
			
			return true;
		} catch (SQLException e) {
			System.err.println("Error during product variants update: " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}
}