package com.nestandrest.util;

import jakarta.servlet.ServletException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RedirectionUtil {
	private static final String baseUrl = "/WEB-INF/pages/";
	public static final String registerUrl = baseUrl + "/auth/registration.jsp";
	public static final String loginUrl = baseUrl + "/auth/login.jsp";
	public static final String homeUrl = baseUrl + "home.jsp";
	public static final String editUserProfileUrl = baseUrl + "user/user-profile.jsp";
	public static final String checkoutAddressUrl = baseUrl + "checkout/checkout-address.jsp";

	public void setMsgAttribute(HttpServletRequest req, String msgType, String msg) {
		req.setAttribute(msgType, msg);
	}

	public void redirectToPage(HttpServletRequest req, HttpServletResponse resp, String page)
			throws ServletException, IOException {
		req.getRequestDispatcher(page).forward(req, resp);
	}

	public void setMsgAndRedirect(HttpServletRequest req, HttpServletResponse resp, String msgType, String msg,
			String page) throws ServletException, IOException {
		setMsgAttribute(req, msgType, msg);
		redirectToPage(req, resp, page);
	}

}