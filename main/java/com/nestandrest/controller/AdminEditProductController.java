package com.nestandrest.controller;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import com.nestandrest.model.ProductModel;
import com.nestandrest.model.ProductVariantModel;
import com.nestandrest.service.ProductService;
import com.nestandrest.util.ImageUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

/**
 * Controller servlet responsible for handling the addition of new products by
 * admin users. It supports GET and POST methods to render the form and process
 * product data including image upload.
 * 
 * @author 23047584 Bhumika Karki
 * @author 23047626 Ayush Shrestha
 */
@WebServlet(asyncSupported = true, urlPatterns = "/admin/products/edit")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 * 50)
public class AdminEditProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductService productService;
	private ImageUtil imageUtil;

	/**
	 * Initializes the ProductService instance when the servlet is first created.
	 */
	@Override
	public void init() throws ServletException {
		productService = new ProductService();
		this.imageUtil = new ImageUtil();
	}

	/**
	 * Handles GET requests to show the "Edit Product" form with category data.
	 *
	 * @param request  HTTP request containing data from the client
	 * @param response HTTP response sent back to the client
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Set categories
		request.setAttribute("categories", productService.getAllCategories());

		// Set product
		int productId = Integer.valueOf(request.getParameter("id"));
		ProductModel product = productService.getById(productId);
		request.setAttribute("product", product);

		// Set variants of the product
		List<ProductVariantModel> variants = productService.getVariantsOfAProduct(productId);
		request.setAttribute("variants", variants);

		request.getRequestDispatcher("/WEB-INF/pages/admin-products/admin-edit-product.jsp").forward(request, response);
	}

	/**
	 * Handles POST requests to process form submission, validate input, handle
	 * image upload, and persist product information.
	 *
	 * @param request  HTTP request containing form and file data
	 * @param response HTTP response sent back to the client
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// Retrieve form data
			String productIdStr = request.getParameter("id");
			int productId = Integer.valueOf(productIdStr);
			String name = request.getParameter("productName");
			String subDescription = request.getParameter("subDescription");
			String fullDescription = request.getParameter("fullDescription");

			int quantity = Integer.parseInt(request.getParameter("quantity"));
			String category = request.getParameter("category");
			int categoryId = Integer.valueOf(category);
			double price = Double.parseDouble(request.getParameter("price"));
			String discountedPriceStr = request.getParameter("discountedPrice");
			double discountedPrice = (discountedPriceStr != null && !discountedPriceStr.isEmpty())
					? Double.parseDouble(discountedPriceStr)
					: 0.0;

			// Retrieve variant names array
			String[] variantNames = request.getParameterValues("variantNames[]");
			// If no variants were submitted, initialize as empty array
			if (variantNames == null) {
				variantNames = new String[0];
			}

			// Retrieve variant values array
			String[] variantValues = request.getParameterValues("variantValues[]");
			// If no variants were submitted, initialize as empty array
			if (variantValues == null) {
				variantValues = new String[0];
			}

			// Set attributes

			// Set categories
			request.setAttribute("categories", productService.getAllCategories());

			// Set product
			ProductModel product = productService.getById(productId);
			request.setAttribute("product", product);

			// Set variants of the product
			List<ProductVariantModel> variants = productService.getVariantsOfAProduct(productId);
			request.setAttribute("variants", variants);

			// Validate input
			if (name == null || name.isEmpty() || subDescription == null || subDescription.isEmpty()
					|| fullDescription == null || fullDescription.isEmpty() || category == null || category.isEmpty()
					|| quantity <= 0 || price <= 0) {
				request.setAttribute("error", "Invalid input. Please check the form.");
				request.setAttribute("categories", productService.getAllCategories());
				request.getRequestDispatcher("/WEB-INF/pages/admin-products/admin-edit-product.jsp").forward(request,
						response);
				return;
			}

			// Update ProductModel object
			product.setName(name);
			product.setShortDescription(subDescription);
			product.setLongDescription(fullDescription);
			product.setPrice(price);
			product.setDiscountedPrice(discountedPrice);
			product.setStockQty(quantity);
			product.setCategoryId(categoryId);
			request.setAttribute("product", product);

			productService.updateProduct(product);

			// Update the variants
			productService.updateProductVariants(productId, variantNames, variantValues);

			// Handle image file uploads
			for (int i = 1; i <= 4; i++) {
				String fileInputName = "productImage" + i;
				Part filePart = request.getPart(fileInputName);
				
				// Check if a file was uploaded for this position
				if (filePart != null && filePart.getSize() > 0) {
					// Generate a filename based on productId and position
					String filename = productId + (i > 1 ? "-" + i : "");
					
					// Upload the new image
					imageUtil.uploadImage(filePart, "product-images", filename);
				}
			}

			// Redirect to product list
			response.sendRedirect(request.getContextPath() + "/admin/products/list");
		} catch (NumberFormatException e) {
			e.printStackTrace();
			request.setAttribute("error", "Error editing product: " + e.getMessage());
			request.setAttribute("categories", productService.getAllCategories());
			request.getRequestDispatcher("/WEB-INF/pages/admin-products/admin-edit-product.jsp").forward(request,
					response);
		}
	}
}