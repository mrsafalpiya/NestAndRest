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

@WebServlet(asyncSupported = true, urlPatterns = { "/products" })
public class ProductListingController extends HttpServlet {
	private static final int PAGE_SIZE = 8;
	private ProductService productService;

	@Override
	public void init() throws ServletException {
		this.productService = new ProductService();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("allCategories", this.productService.getAllCategories());

		// Get currently selected category
		String category = req.getParameter("category");
		if (category == null || category.trim().isEmpty()) {
			category = "0"; // Ensure this is set
		}
		final String categoryFilter = category;
		req.setAttribute("selectedCategory", category);

		// Get search query
		String search = req.getParameter("search");
		if (search == null || search.trim().isEmpty()) {
			search = "";
		}
		req.setAttribute("searchQuery", search);

		// Get order by
		String orderBy = req.getParameter("order");
		if (orderBy == null || orderBy.trim().isEmpty()) {
			orderBy = "product_id";
		}
		req.setAttribute("orderByQuery", orderBy);

		String pageParam = req.getParameter("page");
		int page = (pageParam != null) ? Integer.parseInt(pageParam) : 1;

		// Get all products
		List<ProductModel> allProducts = this.productService.getProducts(search, orderBy);

		// Filter
		List<ProductModel> filteredProducts = categoryFilter.equals("0") ? allProducts
				: allProducts.stream().filter(p -> Integer.toString(p.getCategoryId()).equalsIgnoreCase(categoryFilter))
						.collect(Collectors.toList());

		// Pagination logic
		int totalProducts = filteredProducts.size();
		int totalPages = (int) Math.ceil((double) totalProducts / PAGE_SIZE);

		int start = (page - 1) * PAGE_SIZE;
		int end = Math.min(start + PAGE_SIZE, totalProducts);

		// Handle out-of-range start index
		List<ProductModel> paginated;
		if (start >= totalProducts) {
			paginated = List.of(); // return an empty list
		} else {
			paginated = filteredProducts.subList(start, end);
		}

		// Set attributes
		req.setAttribute("products", paginated);
		req.setAttribute("totalPages", totalPages);
		req.setAttribute("currentPage", page);
		req.setAttribute("selectedCategory", category);

		req.getRequestDispatcher("/WEB-INF/pages/products/product-listing.jsp").forward(req, resp);
	}
}
