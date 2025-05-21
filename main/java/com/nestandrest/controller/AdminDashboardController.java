package com.nestandrest.controller;

import java.io.IOException;

import com.nestandrest.service.AdminDashboardService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Controller for handling requests to the Admin Dashboard.
 * 
 * @author 23047626 Ayush Shrestha
 * @author 23048460 Safal Piya
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/admin" })
public class AdminDashboardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private AdminDashboardService adminDashboardService;

	/**
	 * Initializes the AdminDashboardService instance when the servlet is first
	 * created.
	 * 
	 * @throws ServletException If an error occurs during initialization.
	 */
	@Override
	public void init() throws ServletException {
		this.adminDashboardService = new AdminDashboardService();
		super.init();
	}

	/**
	 * Handles HTTP GET requests for the Admin Dashboard. Forwards the request to
	 * the admin-dashboard.jsp page with sales data.
	 * 
	 * @param req  The HttpServletRequest object containing client request.
	 * @param resp The HttpServletResponse object for sending response.
	 * @throws ServletException If an error occurs during request handling.
	 * @throws IOException      If an I/O error occurs during request processing.
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("sales_last_7_days", this.adminDashboardService.getSalesOfLast7Days());
		req.setAttribute("total_sales", this.adminDashboardService.getTotalSales());
		req.getRequestDispatcher("WEB-INF/pages/admin-dashboard.jsp").forward(req, resp);
	}
}
