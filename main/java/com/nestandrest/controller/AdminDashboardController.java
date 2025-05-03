package com.nestandrest.controller;

import java.io.IOException;

import com.nestandrest.service.AdminDashboardService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(asyncSupported = true, urlPatterns = { "/admin" })
public class AdminDashboardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private AdminDashboardService adminDashboardService;
	
	@Override
	public void init() throws ServletException {
		this.adminDashboardService = new AdminDashboardService();
		super.init();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("sales_last_7_days", this.adminDashboardService.getSalesOfLast7Days());
		req.setAttribute("total_sales", this.adminDashboardService.getTotalSales());
		req.getRequestDispatcher("WEB-INF/pages/admin-dashboard.jsp").forward(req, resp);
	}
}
