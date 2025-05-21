package com.nestandrest.controller;

import com.nestandrest.model.ProductModel;
import com.nestandrest.service.ProductService;
import com.nestandrest.util.ImageUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.*;
import java.util.Collection;
import java.util.List;

/**
 * Controller servlet responsible for handling the addition of new products by
 * admin users. It supports GET and POST methods to render the form and process
 * product data including image upload.
 * 
 * @author 23048460 Safal Piya
 * @author 23047584 Bhumika Karki
 * @author 23047626 Ayush Shrestha
 */
@WebServlet(asyncSupported = true, urlPatterns = "/admin/products/add")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 * 50)
public class AdminAddProductController extends HttpServlet {
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
	 * Handles GET requests to show the "Add Product" form with category data.
	 *
	 * @param request  HTTP request containing data from the client
	 * @param response HTTP response sent back to the client
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("categories", productService.getAllCategories());
		request.getRequestDispatcher("/WEB-INF/pages/admin-products/admin-add-product.jsp").forward(request, response);
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
			String name = request.getParameter("productName");
			String subDescription = request.getParameter("subDescription");
			String fullDescription = request.getParameter("fullDescription");

			int quantity = Integer.parseInt(request.getParameter("quantity"));
			String category = request.getParameter("category");
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

			// Validate input
			if (name == null || name.isEmpty() || subDescription == null || subDescription.isEmpty()
					|| fullDescription == null || fullDescription.isEmpty() || category == null || category.isEmpty()
					|| quantity <= 0 || price <= 0) {
				request.setAttribute("error", "Invalid input. Please check the form.");
				request.setAttribute("categories", productService.getAllCategories());
				request.getRequestDispatcher("/WEB-INF/pages/admin-products/admin-add-product.jsp").forward(request,
						response);
				return;
			}

			// Create ProductModel object
			ProductModel product = new ProductModel(0, name, subDescription, fullDescription, price, discountedPrice,
					Integer.parseInt(category), quantity);

			// Save the product with the variants and image path
			int productId = productService.addProduct(product, variantNames, variantValues);

			// Handle file upload
			Collection<Part> imageParts = request.getParts();
			List<Part> imageFiles = imageParts.stream()
					.filter(part -> "images".equals(part.getName()) && part.getSize() > 0).toList();

			if (imageFiles.isEmpty()) {
				// Handle case where no images were uploaded
				request.setAttribute("error", "No images were uploaded. Please add at least one product image.");
				request.setAttribute("categories", productService.getAllCategories());
				request.getRequestDispatcher("/WEB-INF/pages/admin-products/admin-add-product.jsp").forward(request,
						response);
				return;
			}
			// loop over each images and upload
			for (int i = 0; i < imageFiles.size(); i++) {
				Part imagePart = imageFiles.get(i);
				imageUtil.uploadImage(imagePart, "product-images", productId + (i > 0 ? "-" + (i + 1) : ""));
			}

			// Redirect to product list
			response.sendRedirect(request.getContextPath() + "/admin/products/list");
		} catch (NumberFormatException e) {
			e.printStackTrace();
			request.setAttribute("error", "Error adding product: " + e.getMessage());
			request.setAttribute("categories", productService.getAllCategories());
			request.getRequestDispatcher("/WEB-INF/pages/admin-products/admin-add-product.jsp").forward(request,
					response);
		}
	}
}