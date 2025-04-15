package com.nestandrest.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.nestandrest.util.PasswordUtil;
import com.nestandrest.util.RedirectionUtil;
import com.nestandrest.util.ValidationUtil;

@WebServlet(asyncSupported = true, urlPatterns = { "/registration" })
public class RegistrationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ValidationUtil validationUtil;
	private RedirectionUtil redirectionUtil;

	@Override
	public void init() throws ServletException {
		this.validationUtil = new ValidationUtil();
		this.redirectionUtil = new RedirectionUtil();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/pages/auth/registration.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String firstName = req.getParameter("first-name");
		String lastName = req.getParameter("last-name");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String confirmPassword = req.getParameter("password-confirm");

		// Checking if all fields are filled
		if (validationUtil.isNullOrEmpty(firstName) || validationUtil.isNullOrEmpty(lastName)
				|| validationUtil.isNullOrEmpty(email) || validationUtil.isNullOrEmpty(password)
				|| validationUtil.isNullOrEmpty(confirmPassword)) {
			redirectionUtil.setMsgAndRedirect(req, resp, "error", "All fields are required to be filled!",
					RedirectionUtil.registerUrl);
			return;
		}

		// Checking if a valid email was provided
		if (!validationUtil.isValidEmail(email)) {
			redirectionUtil.setMsgAndRedirect(req, resp, "error", "Please enter a valid email address!",
					RedirectionUtil.registerUrl);
			return;
		}

		// Checking if passwords are matched
		if (!password.equals(confirmPassword)) {
			redirectionUtil.setMsgAndRedirect(req, resp, "error", "Passwords do not match!",
					RedirectionUtil.registerUrl);
			return;
		}

		// Checking if given password is a strong password
		if (!validationUtil.isValidPassword(password)) {
			redirectionUtil.setMsgAndRedirect(req, resp, "error",
					"Password must be at least 8 characters long and include at least one uppercase letter, one number, and one special character (@$!%*?&)!",
					RedirectionUtil.registerUrl);
			return;
		}

		redirectionUtil.setMsgAndRedirect(req, resp, "success", "Account created successfully! You may login now.",
				RedirectionUtil.loginUrl);
	}
}
