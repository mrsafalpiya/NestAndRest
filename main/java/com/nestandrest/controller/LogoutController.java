package com.nestandrest.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.nestandrest.util.CookiesUtil;

/**
 * Controller servlet for handling user logout requests.
 *
 * @author 23049063 Himani Chaudhary
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/logout" })
public class LogoutController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Handles HTTP GET requests to log out the user. Invalidates the session,
	 * deletes cookies, and redirects to the login page.
	 *
	 * @param req  The HttpServletRequest containing the client request.
	 * @param resp The HttpServletResponse for sending the response.
	 * @throws ServletException if an error occurs during request handling.
	 * @throws IOException      if an I/O error occurs during request processing.
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Invalidate the session to log out the user
		req.getSession().invalidate();

		// Delete cookies related to user information
		CookiesUtil.deleteCookie(resp, "email");
		CookiesUtil.deleteCookie(resp, "name");

		// Redirect to the login page with a logout success message
		resp.sendRedirect(req.getContextPath() + "/login?logout=success");
	}
}
