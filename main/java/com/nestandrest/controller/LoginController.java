package com.nestandrest.controller;

import java.io.IOException;

import jakarta.servlet.http.Cookie;
import com.nestandrest.util.SessionUtil;
import com.nestandrest.model.UserModel;
import com.nestandrest.service.LoginService;
import com.nestandrest.util.CookiesUtil;
import com.nestandrest.util.PasswordUtil;
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
	private LoginService loginService;

	@Override
	public void init() throws ServletException {
		this.validationUtil = new ValidationUtil();
		this.redirectionUtil = new RedirectionUtil();
		this.loginService = new LoginService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/pages/auth/login.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			UserModel userModel = extractUserModel(req, resp);
			String loginUserRole = loginService.loginUser(userModel);

			if (loginUserRole == null) {
				redirectionUtil.setMsgAndRedirect(req, resp, "error", "Invalid credentials", RedirectionUtil.loginUrl);
				return;
			}

			int cookieTime = 60 * 60;

			if (req.getParameter("rememberMe") != null) {
				cookieTime = 24 * 60 * 60;
			}

			CookiesUtil.addCookie(resp, "email", userModel.getEmail(), cookieTime);
			SessionUtil.setAttribute(req, "role_name", loginUserRole);

			if (loginUserRole.equals("Admin")) {
				resp.sendRedirect(req.getContextPath() + "/admin"); // Redirect to /admin
			} else {
				resp.sendRedirect(req.getContextPath() + "/home"); // Redirect to /home
			}
		} catch (Exception e) {
			redirectionUtil.setMsgAndRedirect(req, resp, "error",
					"An unexpected error occurred. Please try again later!", RedirectionUtil.loginUrl);
			e.printStackTrace();
		}
	}

	private UserModel extractUserModel(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String email = req.getParameter("email");
		String password = req.getParameter("password");

		// Checking if all fields are filled
		if (validationUtil.isNullOrEmpty(email) || validationUtil.isNullOrEmpty(password)) {
			redirectionUtil.setMsgAndRedirect(req, resp, "error", "All fields are required to be filled!",
					RedirectionUtil.loginUrl);
			return null;
		}

		// Checking if a valid email was provided
		if (!validationUtil.isValidEmail(email)) {
			redirectionUtil.setMsgAndRedirect(req, resp, "error", "Please enter a valid email address!",
					RedirectionUtil.registerUrl);
			return null;
		}
		
		return new UserModel(email, password);
	}
}
