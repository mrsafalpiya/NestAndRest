package com.nestandrest.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Controller servlet for handling requests to the Terms and Conditions page.
 *
 * @author 23047626 Ayush Shrestha
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/terms" })
public class TermsAndConditions extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Handles HTTP GET requests to display the Terms and Conditions page. Forwards
	 * the request to the terms.jsp page.
	 *
	 * @param req  The HttpServletRequest containing the client request.
	 * @param resp The HttpServletResponse for sending the response.
	 * @throws ServletException if an error occurs during request handling.
	 * @throws IOException      if an I/O error occurs during request processing.
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Forward the request to the terms.jsp page
		req.getRequestDispatcher("/WEB-INF/pages/info/terms.jsp").forward(req, resp);
	}
}
