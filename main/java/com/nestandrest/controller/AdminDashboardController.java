package com.nestandrest.controller;

import java.io.IOException;

import com.nestandrest.service.AdminDashboardService;
import com.nestandrest.service.ProductService;

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
	private ProductService productService;

	/**
	 * Initializes the AdminDashboardService instance when the servlet is first
	 * created.
	 * 
	 * @throws ServletException If an error occurs during initialization.
	 */
	@Override
	public void init() throws ServletException {
		this.adminDashboardService = new AdminDashboardService();
		this.productService = new ProductService();
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
		req.setAttribute("total_products", this.productService.getProducts(null, null).size());
		req.setAttribute("products_sold", this.adminDashboardService.getProductsSold());
		req.setAttribute("total_sales", this.adminDashboardService.getTotalSales());
		req.setAttribute("sales_by_gender", this.adminDashboardService.getSalesByGender());
		req.setAttribute("yearly_sales", this.adminDashboardService.getYearlySales());
		req.setAttribute("top_5_products", this.adminDashboardService.getTop5SoldProducts());

		req.getRequestDispatcher("WEB-INF/pages/admin-dashboard.jsp").forward(req, resp);
	}
}
