package com.nestandrest.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Controller for handling requests to the About Us page. Serves the
 * about-us.jsp page when users navigate to /about-us URL.
 * 
 * @author 23047626 Ayush Shrestha
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/about-us" })
public class AboutUsController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Handles HTTP GET requests for the About Us page. Forwards the request to the
	 * about-us.jsp page for rendering.
	 *
	 * @param req  The HttpServletRequest object containing client request
	 * @param resp The HttpServletResponse object for sending response
	 * @throws ServletException If an error occurs during request handling
	 * @throws IOException      If an I/O error occurs during request processing
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/pages/info/about-us.jsp").forward(req, resp);
	}
}
