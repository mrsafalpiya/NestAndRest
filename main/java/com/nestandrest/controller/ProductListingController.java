package com.nestandrest.controller;

import com.nestandrest.model.Product;
import com.nestandrest.model.ProductModel;
import com.nestandrest.service.ProductService;
import com.nestandrest.util.ProductData;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller that handles product listing functionality for the "/products" route.
 * Supports category filtering, search, sorting, and pagination of products.
 *
 * @author Bhumika Karki
 */

@WebServlet(asyncSupported = true, urlPatterns = { "/products" })
public class ProductListingController extends HttpServlet {
	private static final int PAGE_SIZE = 8;     // Number of products per page
	private ProductService productService;

	
	/**
	 * Initializes the ProductService instance when the servlet is first loaded.
	 */
	@Override
	public void init() throws ServletException {
		this.productService = new ProductService();
	}

	/**
	 * Handles GET requests for displaying the product listing page.
	 * Applies optional filters: category, search keyword, sorting, and pagination.
	 *
	 * @param req  HttpServletRequest object containing client request parameters
	 * @param resp HttpServletResponse object for sending the response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an input/output error occurs
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Fetch all available categories for the dropdown
		req.setAttribute("allCategories", this.productService.getAllCategories());

		// Get currently selected category (default to "0" for All)
		String category = req.getParameter("category");
		if (category == null || category.trim().isEmpty()) {
			category = "0"; // Ensuring this is set
		}
		final String categoryFilter = category;
		req.setAttribute("selectedCategory", category);

		// Get search query (default to empty)
		String search = req.getParameter("search");
		if (search == null || search.trim().isEmpty()) {
			search = "";
		}
		req.setAttribute("searchQuery", search);

		// Get order/sorting option (default to product_id)
		String orderBy = req.getParameter("order");
		if (orderBy == null || orderBy.trim().isEmpty()) {
			orderBy = "product_id";
		}
		req.setAttribute("orderByQuery", orderBy);

		// Get current page number (default to 1)
		String pageParam = req.getParameter("page");
		int page = (pageParam != null) ? Integer.parseInt(pageParam) : 1;

		// Get all products based on search and order criteria
		List<ProductModel> allProducts = this.productService.getProducts(search, orderBy);

		// Apply category filter if necessary
		List<ProductModel> filteredProducts = categoryFilter.equals("0") ? allProducts
				: allProducts.stream().filter(p -> Integer.toString(p.getCategoryId()).equalsIgnoreCase(categoryFilter))
						.collect(Collectors.toList());

		// Pagination logic: calculate total pages and determine slice for current page
		int totalProducts = filteredProducts.size();
		int totalPages = (int) Math.ceil((double) totalProducts / PAGE_SIZE);

		int start = (page - 1) * PAGE_SIZE;
		int end = Math.min(start + PAGE_SIZE, totalProducts);

		// Handle out-of-range start index
		List<ProductModel> paginated;
		if (start >= totalProducts) {
			paginated = List.of();     // If page exceeds range, return empty list
		} else {
			paginated = filteredProducts.subList(start, end);
		}

		// Set required attributes for JSP rendering
		req.setAttribute("products", paginated);
		req.setAttribute("totalPages", totalPages);
		req.setAttribute("currentPage", page);
		req.setAttribute("selectedCategory", category);

		// Forward to JSP view
		req.getRequestDispatcher("/WEB-INF/pages/products/product-listing.jsp").forward(req, resp);
	}
}
