package com.nestandrest.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Controller servlet for handling 500 Internal Server Error page requests.
 *
 * @author 23049063 Himani Chaudhary
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/error500" })
public class Error500Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Handles HTTP GET requests to display the 500 error page.
	 *
	 * @param request  The HttpServletRequest containing the client request.
	 * @param response The HttpServletResponse for sending the response.
	 * @throws ServletException if an error occurs during request handling.
	 * @throws IOException      if an I/O error occurs during request processing.
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Forward the request to the 500 error JSP page
		request.getRequestDispatcher("/WEB-INF/pages/error/error500.jsp").forward(request, response);
	}
}
