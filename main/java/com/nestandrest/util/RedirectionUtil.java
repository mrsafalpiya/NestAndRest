package com.nestandrest.util;

import jakarta.servlet.ServletException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Utility class for handling page redirections and message attributes in the
 * application. Provides methods for redirecting to different JSP pages and
 * setting message attributes.
 */
public class RedirectionUtil {
	// Base path for all JSP pages
	private static final String baseUrl = "/WEB-INF/pages/";

	// Common URL constants for various pages in the application
	public static final String registerUrl = baseUrl + "/auth/registration.jsp";
	public static final String loginUrl = baseUrl + "/auth/login.jsp";
	public static final String homeUrl = baseUrl + "home.jsp";
	public static final String editUserProfileUrl = baseUrl + "user/user-profile.jsp";
	public static final String checkoutAddressUrl = baseUrl + "checkout/checkout-address.jsp";
	public static final String contactUsUrl = baseUrl + "info/contact-us.jsp";

	/**
	 * Sets a message attribute in the HTTP request.
	 * 
	 * @param req     the HTTP servlet request
	 * @param msgType the type of message (e.g., "success", "error", "info")
	 * @param msg     the message content
	 */
	public void setMsgAttribute(HttpServletRequest req, String msgType, String msg) {
		req.setAttribute(msgType, msg);
	}

	/**
	 * Redirects to a specified JSP page using request dispatcher.
	 * 
	 * @param req  the HTTP servlet request
	 * @param resp the HTTP servlet response
	 * @param page the URL/path of the page to redirect to
	 * @throws ServletException if a servlet exception occurs
	 * @throws IOException      if an I/O exception occurs
	 */
	public void redirectToPage(HttpServletRequest req, HttpServletResponse resp, String page)
			throws ServletException, IOException {
		req.getRequestDispatcher(page).forward(req, resp);
	}

	/**
	 * Sets a message attribute and redirects to a specified page in a single method
	 * call.
	 * 
	 * @param req     the HTTP servlet request
	 * @param resp    the HTTP servlet response
	 * @param msgType the type of message (e.g., "success", "error", "info")
	 * @param msg     the message content
	 * @param page    the URL/path of the page to redirect to
	 * @throws ServletException if a servlet exception occurs
	 * @throws IOException      if an I/O exception occurs
	 */
	public void setMsgAndRedirect(HttpServletRequest req, HttpServletResponse resp, String msgType, String msg,
			String page) throws ServletException, IOException {
		setMsgAttribute(req, msgType, msg);
		redirectToPage(req, resp, page);
	}
}