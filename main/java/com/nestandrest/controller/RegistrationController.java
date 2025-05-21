package com.nestandrest.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.nestandrest.util.SessionUtil;
import com.nestandrest.model.UserModel;
import com.nestandrest.service.RegistrationService;
import com.nestandrest.util.CookiesUtil;
import com.nestandrest.util.PasswordUtil;
import com.nestandrest.util.RedirectionUtil;
import com.nestandrest.util.ValidationUtil;

@WebServlet(asyncSupported = true, urlPatterns = { "/registration" })
public class RegistrationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ValidationUtil validationUtil;
	private RedirectionUtil redirectionUtil;
	private RegistrationService registrationService;

	@Override
	public void init() throws ServletException {
		this.validationUtil = new ValidationUtil();
		this.redirectionUtil = new RedirectionUtil();
		this.registrationService = new RegistrationService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("genders", registrationService.getGenders());
		request.getRequestDispatcher("/WEB-INF/pages/auth/registration.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("genders", registrationService.getGenders());
		try {
			UserModel userModel = extractUserModel(req, resp);
			Boolean isAdded = registrationService.addUser(userModel);

			if (isAdded == null) {
				redirectionUtil.setMsgAndRedirect(req, resp, "error",
						"An unexpected error occurred. Please try again later!", RedirectionUtil.registerUrl);
				return;
			}

			redirectionUtil.setMsgAndRedirect(req, resp, "success", "Your account is successfully created!",
					RedirectionUtil.loginUrl);
		} catch (Exception e) {
			redirectionUtil.setMsgAndRedirect(req, resp, "error",
					"An unexpected error occurred. Please try again later!", RedirectionUtil.registerUrl);
			e.printStackTrace();
		}
	}

	private UserModel extractUserModel(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String firstName = req.getParameter("first-name");
		String lastName = req.getParameter("last-name");
		String email = req.getParameter("email");
		String phone = req.getParameter("phone");
		String password = req.getParameter("password");
		String confirmPassword = req.getParameter("password-confirm");
		String genderId = req.getParameter("gender-id");

		// Set the attributes as the inputs in the form data (to restore input values on
		// validation errors)
		req.setAttribute("restoreFirstName", firstName);
		req.setAttribute("restoreLastName", lastName);
		req.setAttribute("restoreEmail", email);
		req.setAttribute("restorePhone", phone);
		req.setAttribute("restoreGenderId", genderId);

		// Checking if all fields are filled
		if (validationUtil.isNullOrEmpty(firstName) || validationUtil.isNullOrEmpty(lastName)
				|| validationUtil.isNullOrEmpty(email) || validationUtil.isNullOrEmpty(password)
				|| validationUtil.isNullOrEmpty(confirmPassword)) {
			redirectionUtil.setMsgAndRedirect(req, resp, "error", "All fields are required to be filled!",
					RedirectionUtil.registerUrl);
			return null;
		}

		// Checking if a proper first and last name is provided
		if (!validationUtil.isAlphabetic(firstName) || !validationUtil.isAlphabetic(lastName)) {
			redirectionUtil.setMsgAndRedirect(req, resp, "error", "Please enter valid name!",
					RedirectionUtil.registerUrl);
			return null;
		}

		// Checking if a valid email was provided
		if (!validationUtil.isValidEmail(email)) {
			redirectionUtil.setMsgAndRedirect(req, resp, "error", "Please enter a valid email address!",
					RedirectionUtil.registerUrl);
			return null;
		}

		// Checking if a valid phone number was provided
		if (!validationUtil.isValidPhoneNumber(phone)) {
			redirectionUtil.setMsgAndRedirect(req, resp, "error",
					"Please enter a valid phone number! (Start with 98 and of 10 digits)", RedirectionUtil.registerUrl);
			return null;
		}

		// Checking if passwords are matched
		if (!password.equals(confirmPassword)) {
			redirectionUtil.setMsgAndRedirect(req, resp, "error", "Passwords do not match!",
					RedirectionUtil.registerUrl);
			return null;
		}

		// Checking if given password is a strong password
		if (!validationUtil.isValidPassword(password)) {
			redirectionUtil.setMsgAndRedirect(req, resp, "error",
					"Password must be at least 8 characters long and include at least one uppercase letter, one number, and one special character (@$!%*?&)!",
					RedirectionUtil.registerUrl);
			return null;
		}

		// Check if a user with the given email address already exists
		if (registrationService.doesAUserWithEmailExist(email)) {
			redirectionUtil.setMsgAndRedirect(req, resp, "error", "User with the given email address already exists!",
					RedirectionUtil.registerUrl);
			return null;
		}

		// Check if a user with the given phone number already exists
		if (registrationService.doesAUserWithPhoneNumberExist(phone)) {
			redirectionUtil.setMsgAndRedirect(req, resp, "error", "User with the given phone number already exists!",
					RedirectionUtil.registerUrl);
			return null;
		}

		// Encrypt the password
		password = PasswordUtil.encrypt(email, password);

		// Get the customer role ID
		int customerRoleId = registrationService.getCustomerRoleId();
		if (customerRoleId == 0) {
			redirectionUtil.setMsgAndRedirect(req, resp, "error", "Could not get the customer role ID",
					RedirectionUtil.registerUrl);
			return null;
		}

		return new UserModel(firstName.concat(" ").concat(lastName), email, phone, Integer.parseInt(genderId), password,
				customerRoleId);
	}
}
