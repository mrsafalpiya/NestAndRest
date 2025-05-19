package com.nestandrest.filter;

import java.io.IOException;

import com.nestandrest.util.CookiesUtil;
import com.nestandrest.util.SessionUtil;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebFilter(asyncSupported = true, urlPatterns = { "/*" })
public class AuthenticationFilter implements Filter {

	private static final String LOGIN = "/login";
	private static final String REGISTRATION = "/registration";
	private static final String HOME = "/home";
	private static final String EDIT_USER_PROFILE = "/userprofile";
	private static final String ADMIN_DASHBOARD = "/admin";
	private static final String USER_MANAGEMENT = "/usermanagement";
	private static final String UNAUTHORIZED = "/error403";
	private static final String ROOT = "/";

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		Filter.super.init(filterConfig);
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// Cast the request and response to HttpServletRequest and HttpServletResponse
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		// Get the context path
		String contextPath = req.getContextPath();

		// Get the requested URI
		String uri = req.getRequestURI();

		// Get the session and check if user is logged in
		boolean isLoggedIn = CookiesUtil.getCookie(req, "email") != null;

		if (isLoggedIn) {
			// Do not allow logged in users to access login and registration pages
			if (uri.endsWith(LOGIN) || uri.endsWith(REGISTRATION)) {
				res.sendRedirect(contextPath + HOME);
				return;
			}
		} else {
			// Do not allow non-logged in users to access the edit user profile page
			if (uri.endsWith(EDIT_USER_PROFILE) || uri.contains(USER_MANAGEMENT)) {
				res.sendRedirect(contextPath + UNAUTHORIZED);
				return;
			}
		}

		// Get the user role from session
		String userRole = (String) SessionUtil.getAttribute((HttpServletRequest) request, "role_name");
		boolean isAdmin = userRole != null && userRole.equals("Admin");

		// Protect admin dashboard and user management pages from unauthorized users
		if ((uri.endsWith(ADMIN_DASHBOARD) || uri.contains(USER_MANAGEMENT)) && (!isLoggedIn || !isAdmin)) {
			res.sendRedirect(contextPath + UNAUTHORIZED);
			return;
		}

		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		Filter.super.destroy();
	}

}