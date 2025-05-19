package com.nestandrest.service;

import com.nestandrest.dao.CategoryDAO;
import com.nestandrest.dao.ProductDAO;
import com.nestandrest.model.Category;
import com.nestandrest.model.Product;
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
 * @author Bhumika Karki
 */
public class ProductService {
	private ProductDAO productDAO;
	private CategoryDAO categoryDAO;
	private Connection dbConn;

	/**
	 * Initializes ProductService with DAO instances and DB connection.
	 */
	public ProductService() {
		try {
			this.dbConn = DbConfig.getDbConnection();
			this.productDAO = new ProductDAO(dbConn);
			this.categoryDAO = new CategoryDAO(dbConn);
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Adds a new product and sets stock status based on quantity.
	 *
	 * @param Product product object to insert
	 */
	public void addProduct(Product product) {
		try {
			product.setStock(product.getQuantity()); // Set stock equal to quantity
			product.setInStock(product.getQuantity() > 0);
			productDAO.addProduct(product);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Retrieves all products from the database.
	 *
	 * @return list of products; returns an empty list if an error occurs
	 */
	public List<Product> getAllProducts() {
		try {
			return productDAO.getAllProducts();
		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

	/**
	 * Retrieves a product by its ID.
	 *
	 * @param productId product ID to fetch
	 * @return Product object or null if not found
	 */
	public Product getProductById(int productId) {
		try {
			return productDAO.getProductById(productId);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Updates a product and its stock status.
	 *
	 * @param Product product to update
	 */
	public void updateProduct(Product product) {
		try {
			product.setStock(product.getQuantity());
			product.setInStock(product.getQuantity() > 0);
			productDAO.updateProduct(product);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Deletes a product by its ID.
	 *
	 * @param productId product ID to delete
	 */
	public void deleteProduct(int productId) {
		try {
			productDAO.deleteProduct(productId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Fetches all product categories from the database.
	 *
	 * @return list of Category objects or null if error
	 */
	public List<Category> getAllCategories() {
		if (dbConn == null) {
			System.err.println("Database connection is not available!");
			return null;
		}

		String query = "SELECT * FROM category";

		try {
			PreparedStatement genderStmt = dbConn.prepareStatement(query);
			ResultSet result = genderStmt.executeQuery();

			List<Category> categories = new ArrayList<Category>();

			while (result.next()) {
				categories.add(new Category(result.getInt("category_id"), result.getString("name"),
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
}