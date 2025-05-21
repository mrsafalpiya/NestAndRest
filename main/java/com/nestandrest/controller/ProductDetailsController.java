package com.nestandrest.controller;

import java.io.IOException;

import com.nestandrest.model.ProductModel;
import com.nestandrest.model.ProductVariantModel;
import com.nestandrest.model.UserModel;
import com.nestandrest.service.CartService;
import com.nestandrest.service.ProductService;
import com.nestandrest.service.UserService;
import com.nestandrest.util.RedirectionUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Controller that handles the display of individual product details.
 * It retrieves product information and associated variants to render
 * the product-details.jsp page.
 * 
 * URL pattern: /product-details
 * 
 * Example usage:
 * GET /product-details?id=3
 * 
 * @author Bhumika Karki
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/product-details" })
public class ProductDetailsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductService productService;
	private CartService cartService;
	private UserService userService;
	private RedirectionUtil redirectionUtil;

	/**
	 * Initializes the ProductService used to fetch product and variant data.
	 */
	@Override
	public void init() throws ServletException {
		this.productService = new ProductService();
		this.cartService = new CartService();
		this.userService = new UserService();
	}

	/**
	 * Handles GET requests to show a product's detail page.
	 * Validates the product ID, retrieves the product and its variants,
	 * and forwards them to the product-details.jsp page.
	 *
	 * @param req  HttpServletRequest carrying the "id" parameter
	 * @param resp HttpServletResponse to forward the view
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String idParam = req.getParameter("id");

			if (idParam != null) {
				int id = Integer.parseInt(idParam);

				if (id >= 0) {
					ProductModel product = productService.getById(id);
					List<ProductVariantModel> productVariants = productService.getVariantsOfAProduct(id);

					req.setAttribute("product", product);
					req.setAttribute("productVariants", productVariants);
				} else {
					req.setAttribute("error", "Invalid product ID");
				}
			} else {
				req.setAttribute("error", "Product ID is missing");
			}
		} catch (NumberFormatException e) {
			req.setAttribute("error", "Invalid product ID format");
		}

		req.getRequestDispatcher("/WEB-INF/pages/products/product-details.jsp").forward(req, resp);
	}

	/**
	 * Handles POST requests by simply forwarding to the product details view.
	 * Used when product variant selections or quantity are submitted.
	 *
	 * @param req  HttpServletRequest carrying form data
	 * @param resp HttpServletResponse to display the page again
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Check if not logged in
		UserModel currentUser = userService.getCurrentlyLoggedInUser(req, null);
		if (currentUser == null) {
			resp.sendRedirect(req.getContextPath() + "/login");
			return;
		}

		// Get all request parameters
		Enumeration<String> parameterNames = req.getParameterNames();
		Map<Integer, Integer> variantSelections = new HashMap<>();

		// Process quantity
		String quantityStr = req.getParameter("quantity");
		int quantity = 1; // Default value
		try {
			if (quantityStr != null && !quantityStr.isEmpty()) {
				quantity = Integer.parseInt(quantityStr);
			}
		} catch (NumberFormatException e) {
			req.setAttribute("error", "Invalid quantity format");
		}

		// Process product id
		String productIdStr = req.getParameter("product_id");
		int productId = Integer.parseInt(productIdStr);

		// Extract all variant parameters
		while (parameterNames.hasMoreElements()) {
			String paramName = parameterNames.nextElement();
			if (paramName.startsWith("variant-")) {
				try {
					int variantIndex = Integer.parseInt(paramName.substring(8)); // "variant-".length() = 8
					int variantValue = Integer.parseInt(req.getParameter(paramName));
					variantSelections.put(variantIndex, variantValue);
				} catch (NumberFormatException e) {
					// Skip if the variant index isn't a valid number
				}
			}
		}

		this.cartService.addProductToCart(currentUser.getUserId(), productId, variantSelections, quantity);

		resp.sendRedirect(req.getContextPath() + "/checkout-cart");
	}
}