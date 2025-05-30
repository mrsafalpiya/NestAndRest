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

/**
 * This servlet handles both GET and POST requests for the checkout address
 * step. - GET: Retrieves user's cart and addresses, then forwards to the
 * address selection page. - POST: Validates and saves a new address entered by
 * the user.
 * 
 * @author 23047589 Sanniva Shakya
 */
@WebServlet("/checkout-address")
public class CheckoutAddressController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CartService cartService;
	private UserService userService;
	private ValidationUtil validationUtil;
	private RedirectionUtil redirectionUtil;

	/**
	 * Initializes all service and utility objects used by this controller.
	 * 
	 * @throws ServletException if an error occurs during servlet initialization.
	 */
	@Override
	public void init() throws ServletException {
		this.userService = new UserService();
		this.cartService = new CartService();
		this.validationUtil = new ValidationUtil();
		this.redirectionUtil = new RedirectionUtil();
	}

	/**
	 * Handles HTTP GET requests to display cart items and user addresses. Retrieves
	 * the currently logged-in user's cart and addresses, then forwards the request
	 * to the checkout address JSP page.
	 * 
	 * @param request  The HttpServletRequest containing the client request.
	 * @param response The HttpServletResponse for sending the response.
	 * @throws ServletException if an error occurs during request handling.
	 * @throws IOException      if an I/O error occurs during request processing.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Get the currently logged-in user
		UserModel currentUser = this.userService.getCurrentlyLoggedInUser(request, null);
		if (currentUser == null) {
			// Redirect to login if user is not authenticated
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
		// Retrieve cart items and user addresses
		List<ProductModel> products = this.cartService.getUserCartItems(currentUser.getUserId());
		request.setAttribute("products", products);
		request.setAttribute("addresses", this.userService.getAllUserAddresses(currentUser.getUserId()));

		// Forward to JSP page for displaying the checkout address page
		request.getRequestDispatcher("/WEB-INF/pages/checkout/checkout-address.jsp").forward(request, response);
	}

	/**
	 * Handles HTTP POST requests to add a new address from the checkout page.
	 * Validates the submitted address form data, saves the new address, and
	 * redirects back to the checkout address page.
	 * 
	 * @param req  The HttpServletRequest containing the client request.
	 * @param resp The HttpServletResponse for sending the response.
	 * @throws ServletException if an error occurs during request handling.
	 * @throws IOException      if an I/O error occurs during request processing.
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UserModel currentUser = this.userService.getCurrentlyLoggedInUser(req, null);
		if (currentUser == null) {
			resp.sendRedirect(req.getContextPath() + "/login");
			return;
		}

		// Re-fetch user cart and addresses in case form submission fails
		List<ProductModel> products = this.cartService.getUserCartItems(currentUser.getUserId());

		req.setAttribute("products", products);
		req.setAttribute("addresses", this.userService.getAllUserAddresses(currentUser.getUserId()));

		try {
			// Extract and validate the submitted address form data
			UserAddressModel address = this.extractUserAddressModel(req, resp, currentUser.getUserId());

			// Save the new address
			this.userService.addAddressOfUser(address);

			// Redirect back to the checkout address page
			resp.sendRedirect(req.getContextPath() + "/checkout-address");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Extracts and validates user input to build a UserAddressModel. Validates the
	 * submitted address form fields and constructs a UserAddressModel object if all
	 * validations pass.
	 * 
	 * @param req    The HttpServletRequest containing the client request.
	 * @param resp   The HttpServletResponse for sending the response.
	 * @param userId The ID of the currently logged-in user.
	 * @return A UserAddressModel object containing the validated address data.
	 * @throws Exception if validation fails or an error occurs during processing.
	 */
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
			redirectionUtil.setMsgAndRedirect(req, resp, "error",
					"Please enter a valid phone number! (Start with 98 and of 10 digits)",
					RedirectionUtil.checkoutAddressUrl);
			return null;
		}

		// Construct and return the address object
		return new UserAddressModel(userId, address + " " + city, fullName, phone, false);
	}
}
