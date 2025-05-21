package com.nestandrest.controller;

import java.io.IOException;

import com.nestandrest.service.OrderService;
import com.nestandrest.service.OrderStatusService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Controller servlet for managing order details in the admin panel.
 *
 * @author 23048460 Safal Piya
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/admin-order-details" })
public class AdminOrderDetailsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OrderService orderService;
	private OrderStatusService orderStatusService;

	/**
	 * Initializes the OrderService and OrderStatusService instances when the
	 * servlet is first created.
	 *
	 * @throws ServletException if an error occurs during servlet initialization.
	 */
	@Override
	public void init() throws ServletException {
		this.orderService = new OrderService();
		this.orderStatusService = new OrderStatusService();
	}

	/**
	 * Handles HTTP GET requests to fetch and display order details.
	 *
	 * @param req  The HttpServletRequest containing the client request.
	 * @param resp The HttpServletResponse for sending the response.
	 * @throws ServletException if an error occurs during request handling.
	 * @throws IOException      if an I/O error occurs during request processing.
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Get the orders by applying order ID filter (if given)
		String orderIdFilterStr = req.getParameter("id");
		if (orderIdFilterStr == null || orderIdFilterStr == "") {
			orderIdFilterStr = "0";
		}
		int orderIdFilter = Integer.parseInt(orderIdFilterStr);
		req.setAttribute("order", this.orderService.getOrders(orderIdFilter).getFirst());

		// Get all the possible order status
		req.setAttribute("allOrderStatuses", this.orderStatusService.getAllOrderStatus());

		req.getRequestDispatcher("WEB-INF/pages/order-management/details.jsp").forward(req, resp);
	}

	/**
	 * Handles HTTP POST requests to update the status of an order.
	 *
	 * @param req  The HttpServletRequest containing the client request.
	 * @param resp The HttpServletResponse for sending the response.
	 * @throws ServletException if an error occurs during request handling.
	 * @throws IOException      if an I/O error occurs during request processing.
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Get the order and order status id
		int orderId = Integer.parseInt(req.getParameter("order_id"));
		int orderStatusId = Integer.parseInt(req.getParameter("order_status_id"));
		// Update the status
		this.orderService.setOrderStatus(orderId, orderStatusId);

		resp.sendRedirect(
				req.getContextPath() + "/admin-order-details?id=" + req.getParameter("order_id") + "&success=true");
	}
}
