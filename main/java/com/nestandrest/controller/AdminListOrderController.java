package com.nestandrest.controller;

import java.io.IOException;

import com.nestandrest.service.OrderService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Controller servlet for listing and filtering orders in the admin panel.
 *
 * @author 23048460 Safal Piya
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/admin-orders" })
public class AdminListOrderController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OrderService orderService;

	/**
	 * Initializes the OrderService instance when the servlet is first created.
	 *
	 * @throws ServletException if an error occurs during servlet initialization.
	 */
	@Override
	public void init() throws ServletException {
		this.orderService = new OrderService();
	}

	/**
	 * Handles HTTP GET requests to list orders, optionally filtering by order ID.
	 *
	 * @param req  The HttpServletRequest containing the client request.
	 * @param resp The HttpServletResponse for sending the response.
	 * @throws ServletException if an error occurs during request handling.
	 * @throws IOException      if an I/O error occurs during request processing.
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Get the orders by applying order ID filter (if given)
		String orderIdFilterStr = req.getParameter("order_id");
		req.setAttribute("orderId", orderIdFilterStr);
		if (orderIdFilterStr == null || orderIdFilterStr == "") {
			orderIdFilterStr = "0";
		}
		int orderIdFilter = Integer.parseInt(orderIdFilterStr);
		req.setAttribute("orders", this.orderService.getOrders(orderIdFilter));

		req.getRequestDispatcher("WEB-INF/pages/order-management/list.jsp").forward(req, resp);
	}
}
