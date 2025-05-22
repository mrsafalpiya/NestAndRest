package com.nestandrest.controller;

import java.io.IOException;
import java.util.List;

import com.nestandrest.model.ProductModel;
import com.nestandrest.model.UserModel;
import com.nestandrest.service.CartService;
import com.nestandrest.service.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Handles displaying the cart and removing products from the cart.
 * 
 * @author 23047589 Sanniva Shakya
 */
@WebServlet("/checkout-cart")
public class CheckoutController extends HttpServlet {
	private CartService cartService;
	private UserService userService;

	/**
	 * Initializes the UserService and CartService instances when the servlet is first created.
	 *
	 * @throws ServletException if an error occurs during servlet initialization.
	 */
	@Override
	public void init() throws ServletException {
		this.userService = new UserService();
		this.cartService = new CartService();
	}

	/**
	 * Handles HTTP GET requests to display the cart page for the currently logged-in user.
	 * Redirects to login if the user is not authenticated.
	 *
	 * @param request  The HttpServletRequest containing the client request.
	 * @param response The HttpServletResponse for sending the response.
	 * @throws ServletException if an error occurs during request handling.
	 * @throws IOException      if an I/O error occurs during request processing.
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserModel currentUser = this.userService.getCurrentlyLoggedInUser(request, null);

		if (currentUser == null) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}

		List<ProductModel> products = this.cartService.getUserCartItems(currentUser.getUserId());
		request.setAttribute("products", products);

		request.getRequestDispatcher("/WEB-INF/pages/checkout/checkout-cart.jsp").forward(request, response);
	}

	/**
	 * Handles HTTP POST requests for actions such as removing an item from the cart.
	 * Redirects to login if the user is not authenticated. After action, redirects back to GET to refresh cart page.
	 *
	 * @param request  The HttpServletRequest containing the client request.
	 * @param response The HttpServletResponse for sending the response.
	 * @throws ServletException if an error occurs during request handling.
	 * @throws IOException      if an I/O error occurs during request processing.
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserModel currentUser = this.userService.getCurrentlyLoggedInUser(request, null);

		if (currentUser == null) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}

		String action = request.getParameter("action");
		if ("remove".equals(action)) {
			String productIdStr = request.getParameter("productId");
			if (productIdStr != null && !productIdStr.isEmpty()) {
				try {
					int productId = Integer.parseInt(productIdStr);
					cartService.removeProductFromCart(currentUser.getUserId(), productId);
				} catch (NumberFormatException e) {
					e.printStackTrace();
					// Optionally handle error, set message to request, etc.
				}
			}
		}

		// After action, redirect back to GET to refresh cart page
		response.sendRedirect(request.getContextPath() + "/checkout-cart");
	}
}
