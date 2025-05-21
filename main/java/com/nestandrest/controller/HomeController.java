package com.nestandrest.controller;

import java.io.IOException;
import java.util.List;

import com.nestandrest.model.CategoryModel;
import com.nestandrest.model.ProductModel;
import com.nestandrest.service.ProductService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Controller servlet for handling requests to the home page.
 *
 * @author 23049063 Himani Chaudhary
 * @author 23048460 Safal Piya
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/home", "/" })
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductService productService;

	/**
	 * Initializes the ProductService instance when the servlet is first created.
	 *
	 * @throws ServletException if an error occurs during servlet initialization.
	 */
	@Override
	public void init() throws ServletException {
		// Initialize the ProductService instance
		this.productService = new ProductService();
	}

	/**
	 * Handles HTTP GET requests to display the home page. Retrieves categories and
	 * products, and forwards the request to the home JSP page.
	 *
	 * @param req  The HttpServletRequest containing the client request.
	 * @param resp The HttpServletResponse for sending the response.
	 * @throws ServletException if an error occurs during request handling.
	 * @throws IOException      if an I/O error occurs during request processing.
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Redirect to 404 if the URI is invalid
		String uri = req.getRequestURI();
		if (!(uri.equalsIgnoreCase("/nest-and-rest/")) && !(uri.equalsIgnoreCase("/nest-and-rest/home"))) {
			resp.sendRedirect(req.getContextPath() + "/error404");
			return;
		}

		// Get the list of all categories
		List<CategoryModel> allCategories = this.productService.getAllCategories();
		req.setAttribute("allCategories", allCategories);

		// Get all products
		List<ProductModel> allProducts = this.productService.getProducts("", null);

		// Filter products with discounts
		List<ProductModel> productsWithDiscounts = allProducts.stream()
				.filter(product -> product.getDiscountedPrice() > 0).toList();
		req.setAttribute("productsWithDiscounts", productsWithDiscounts);

		// Categorize products by their categories
		Map<Integer, List<ProductModel>> categorizedProducts = new HashMap<>();
		for (CategoryModel category : allCategories) {
			List<ProductModel> productsInCategory = allProducts.stream()
					.filter(product -> product.getCategoryId() == category.getCategoryId()).toList();
			categorizedProducts.put(category.getCategoryId(), productsInCategory);
		}
		req.setAttribute("categorizedProducts", categorizedProducts);

		// Forward the request to the home JSP page
		req.getRequestDispatcher("WEB-INF/pages/home.jsp").forward(req, resp);
	}
}
