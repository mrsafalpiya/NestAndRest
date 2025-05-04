package com.nestandrest.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(asyncSupported = true, urlPatterns = { "/home", "/" })
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Redirect to 404 if required
		String uri = req.getRequestURI();
		if (!(uri.equalsIgnoreCase("/nest-and-rest/")) && !(uri.equalsIgnoreCase("/nest-and-rest/home"))) {
			resp.sendRedirect(req.getContextPath() + "/error404");
			return;
		}

		req.getRequestDispatcher("WEB-INF/pages/home.jsp").forward(req, resp);
	}
}
