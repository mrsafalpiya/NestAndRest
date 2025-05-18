package com.nestandrest.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import com.nestandrest.model.ProductModel;
import com.nestandrest.model.UserAddressModel;
import com.nestandrest.model.UserModel;
import com.nestandrest.service.CartService;
import com.nestandrest.service.RegistrationService;
import com.nestandrest.service.UserService;
import com.nestandrest.util.PasswordUtil;
import com.nestandrest.util.RedirectionUtil;
import com.nestandrest.util.ValidationUtil;

@WebServlet("/checkout-address")
public class CheckoutAddressController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CartService cartService;
	private UserService userService;
	private ValidationUtil validationUtil;
	private RedirectionUtil redirectionUtil;

	@Override
	public void init() throws ServletException {
		this.userService = new UserService();
		this.cartService = new CartService();
		this.validationUtil = new ValidationUtil();
		this.redirectionUtil = new RedirectionUtil();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		UserModel currentUser = this.userService.getCurrentlyLoggedInUser(request, null);
		if (currentUser == null) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}

		List<ProductModel> products = this.cartService.getUserCartItems(currentUser.getUserId());

		request.setAttribute("products", products);
		request.setAttribute("addresses", this.userService.getAllUserAddresses(currentUser.getUserId()));

		request.getRequestDispatcher("/WEB-INF/pages/checkout/checkout-address.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UserModel currentUser = this.userService.getCurrentlyLoggedInUser(req, null);
		if (currentUser == null) {
			resp.sendRedirect(req.getContextPath() + "/login");
			return;
		}

		List<ProductModel> products = this.cartService.getUserCartItems(currentUser.getUserId());

		req.setAttribute("products", products);
		req.setAttribute("addresses", this.userService.getAllUserAddresses(currentUser.getUserId()));

		try {
			UserAddressModel address = this.extractUserAddressModel(req, resp, currentUser.getUserId());
			this.userService.addAddressOfUser(address);
			resp.sendRedirect(req.getContextPath() + "/checkout-address");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private UserAddressModel extractUserAddressModel(HttpServletRequest req, HttpServletResponse resp, int userId)
			throws Exception {
		String fullName = req.getParameter("full-name");
		String phone = req.getParameter("phone");
		String address = req.getParameter("address");
		String city = req.getParameter("city");

		// Checking if all fields are filled
		if (validationUtil.isNullOrEmpty(fullName) || validationUtil.isNullOrEmpty(phone)
				|| validationUtil.isNullOrEmpty(address) || validationUtil.isNullOrEmpty(city)) {
			redirectionUtil.setMsgAndRedirect(req, resp, "error", "All fields are required to be filled!",
					RedirectionUtil.checkoutAddressUrl);
			return null;
		}

		// Checking if a proper name is provided
		if (!validationUtil.isValidName(fullName)) {
			redirectionUtil.setMsgAndRedirect(req, resp, "error", "Please enter valid name!",
					RedirectionUtil.checkoutAddressUrl);
			return null;
		}

		// Checking if a valid phone number was provided
		if (!validationUtil.isValidPhoneNumber(phone)) {
			redirectionUtil.setMsgAndRedirect(req, resp, "error", "Please enter a valid phone number! (Start with 98 and of 10 digits)",
					RedirectionUtil.checkoutAddressUrl);
			return null;
		}

		return new UserAddressModel(userId, address + " " + city, fullName, phone, false);
	}
}
