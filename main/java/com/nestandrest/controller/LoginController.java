package com.nestandrest.controller;

import java.io.IOException;

import com.nestandrest.util.RedirectionUtil;
import com.nestandrest.util.ValidationUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(asyncSupported = true, urlPatterns = { "/login" })
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ValidationUtil validationUtil;
	private RedirectionUtil redirectionUtil;

	@Override
	public void init() throws ServletException {
		this.validationUtil = new ValidationUtil();
		this.redirectionUtil = new RedirectionUtil();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/pages/auth/login.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String password = req.getParameter("password");

		// Checking if all fields are filled
		if (validationUtil.isNullOrEmpty(email) || validationUtil.isNullOrEmpty(password)) {
			redirectionUtil.setMsgAndRedirect(req, resp, "error", "All fields are required to be filled!",
					RedirectionUtil.loginUrl);
			return;
		}

		// Checking if a valid email was provided
		if (!validationUtil.isValidEmail(email)) {
			redirectionUtil.setMsgAndRedirect(req, resp, "error", "Please enter a valid email address!",
					RedirectionUtil.loginUrl);
			return;
		}

		redirectionUtil.setMsgAndRedirect(req, resp, "success", "Logged in successfully!", RedirectionUtil.homeUrl);
	}
}
