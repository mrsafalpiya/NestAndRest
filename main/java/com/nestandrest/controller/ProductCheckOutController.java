package com.nestandrest.controller;

import java.io.IOException;

import com.nestandrest.model.UserModel;
import com.nestandrest.service.CartService;
import com.nestandrest.service.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Controller servlet for handling product checkout completion requests.
 *
 * @author 23048460 Safal Piya
 * @author 23047589 Sanniva Shakya
 */
@WebServlet("/product-checkout-complete")
public class ProductCheckOutController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private CartService cartService;
	private UserService userService;

	/**
	 * Initializes the CartService and UserService instances when the servlet is
	 * first created.
	 *
	 * @throws ServletException if an error occurs during servlet initialization.
	 */
	@Override
	public void init() throws ServletException {
		this.cartService = new CartService();
		this.userService = new UserService();
	}

	/**
	 * Handles HTTP GET requests to complete the product checkout process. Creates
	 * an order for the cart items of the currently logged-in user and forwards the
	 * request to the checkout completion JSP page.
	 *
	 * @param req  The HttpServletRequest containing the client request.
	 * @param resp The HttpServletResponse for sending the response.
	 * @throws ServletException if an error occurs during request handling.
	 * @throws IOException      if an I/O error occurs during request processing.
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Create order of the cart items of current user
		UserModel currentUser = this.userService.getCurrentlyLoggedInUser(req, null);
		if (currentUser == null) {
			resp.sendRedirect(req.getContextPath() + "/login");
			return;
		}
		int newOrderId = this.cartService.addCartItemsToOrder(currentUser.getUserId(),
				Integer.parseInt(req.getParameter("address_id")));

		// Set it as a request attribute to access in JSP
		req.setAttribute("orderId", newOrderId);

		// Forward to JSP view
		req.getRequestDispatcher("/WEB-INF/pages/checkout/product-checkout-complete.jsp").forward(req, resp);
	}
}
