package com.nestandrest.controller;

import com.nestandrest.model.ProductModel;
import com.nestandrest.service.ProductService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Controller servlet for handling admin-side product listing, pagination,
 * search, and product management actions like update and delete.
 * 
 * @author 23048460 Safal Piya
 * @author 23047584 Bhumika Karki
 * @author 23047626 Ayush Shrestha
 */
@WebServlet(asyncSupported = true, urlPatterns = "/admin/products/list")
public class AdminProductListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int PRODUCTS_PER_PAGE = 6;
	private ProductService productService;

	/**
	 * Initializes the ProductService instance during servlet startup.
	 */
	@Override
	public void init() throws ServletException {
		productService = new ProductService();
	}

	/**
	 * Handles GET requests to fetch and display paginated list of products with
	 * optional search functionality.
	 *
	 * @param request  HTTP request containing optional search and pagination
	 *                 parameters
	 * @param response HTTP response forwarded to the admin product list JSP
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs during forwarding
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("allCategories", this.productService.getAllCategories());

		// Get search query
		String search = request.getParameter("search");
		if (search == null || search.trim().isEmpty()) {
			search = "";
		}
		request.setAttribute("searchQuery", search);

		List<ProductModel> allProducts = productService.getProducts(search, null);
		int totalProducts = allProducts.size();

		int currentPage = 1;
		String pageParam = request.getParameter("page");
		if (pageParam != null && !pageParam.isEmpty()) {
			try {
				currentPage = Integer.parseInt(pageParam);
			} catch (NumberFormatException e) {
				currentPage = 1;
			}
		}

		int start = (currentPage - 1) * PRODUCTS_PER_PAGE;
		int end = Math.min(start + PRODUCTS_PER_PAGE, totalProducts);
		List<ProductModel> paginatedProducts = (totalProducts > 0) ? allProducts.subList(start, end) : allProducts;

		int totalPages = (int) Math.ceil((double) totalProducts / PRODUCTS_PER_PAGE);

		request.setAttribute("products", paginatedProducts);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("totalPages", totalPages);
		request.setAttribute("totalProducts", totalProducts);

		request.getRequestDispatcher("/WEB-INF/pages/admin-products/admin-product-list.jsp").forward(request, response);
	}

	/**
	 * Handles POST requests to perform actions like deleting or updating products
	 * from the admin panel.
	 *
	 * @param request  HTTP request containing form data and action type
	 * @param response HTTP response redirected back to the product list
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs during redirect
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Received POST request with action: " + request.getParameter("action"));
		String action = request.getParameter("action");

		if ("delete".equalsIgnoreCase(action)) {
			try {
				int productId = Integer.parseInt(request.getParameter("productId"));
				productService.deleteProduct(productId);
				System.out.println("Deleted product with ID: " + productId);
			} catch (NumberFormatException e) {
				System.out.println("Error: Invalid product ID");
				request.setAttribute("error", "Invalid product ID");
			}
		} else if ("update".equalsIgnoreCase(action)) {
			try {
				int productId = Integer.parseInt(request.getParameter("productId"));
				String name = request.getParameter("name");
				int stock = Integer.parseInt(request.getParameter("stock"));
				double price = Double.parseDouble(request.getParameter("price"));
				double discountedPrice = Double.parseDouble(request.getParameter("discount-price"));
				String category = request.getParameter("category");

				if (name == null || name.trim().isEmpty() || stock < 0 || price <= 0 || category == null
						|| category.trim().isEmpty()) {
					request.setAttribute("error", "Invalid input. Please check the form.");
					ProductModel product = productService.getById(productId);
					request.setAttribute("editProduct", product);
				} else {
					ProductModel product = productService.getById(productId);
					if (product != null) {
						product.setName(name);
						product.setStockQty(stock);
						product.setPrice(price);
						product.setDiscountedPrice(discountedPrice);
						product.setCategoryId(Integer.parseInt(category));
						productService.updateProduct(product);
						System.out.println("Updated product with ID: " + productId);
					} else {
						request.setAttribute("error", "Product not found");
					}
				}
			} catch (NumberFormatException e) {
				System.out.println("Error: Invalid product ID or numeric values");
				request.setAttribute("error", "Invalid product ID or numeric values");
			}
		}

		response.sendRedirect(request.getContextPath() + "/admin/products/list");
	}
}
