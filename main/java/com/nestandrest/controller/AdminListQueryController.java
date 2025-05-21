package com.nestandrest.controller;

import java.io.IOException;

import com.nestandrest.service.QueryService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Controller servlet for managing and listing user queries in the admin panel.
 *
 * @author 23049063 Himani Chaudhary
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/user-query" })
public class AdminListQueryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private QueryService queryService;

	/**
	 * Initializes the QueryService instance when the servlet is first created.
	 *
	 * @throws ServletException if an error occurs during servlet initialization.
	 */
	@Override
	public void init() throws ServletException {
		this.queryService = new QueryService();
	}

	/**
	 * Handles HTTP GET requests to list all user queries. Forwards the request to
	 * the query management list page.
	 *
	 * @param req  The HttpServletRequest containing the client request.
	 * @param resp The HttpServletResponse for sending the response.
	 * @throws ServletException if an error occurs during request handling.
	 * @throws IOException      if an I/O error occurs during request processing.
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("queries", this.queryService.getAllQueries());

		req.getRequestDispatcher("WEB-INF/pages/query-management/list.jsp").forward(req, resp);
	}
}
