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

@WebServlet("/checkout-cart")
public class CheckoutController extends HttpServlet {
	private CartService cartService;
	private UserService userService;

	@Override
	public void init() throws ServletException {
		this.userService = new UserService();
		this.cartService = new CartService();
	}

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

		// Forward or redirect to your cart view page
		request.getRequestDispatcher("/WEB-INF/pages/checkout/checkout-cart.jsp").forward(request, response);
	}
}
