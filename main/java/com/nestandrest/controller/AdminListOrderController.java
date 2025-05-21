package com.nestandrest.controller;

import java.io.IOException;

import com.nestandrest.service.OrderService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(asyncSupported = true, urlPatterns = { "/admin-orders" })
public class AdminListOrderController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OrderService orderService;

	@Override
	public void init() throws ServletException {
		this.orderService = new OrderService();
	}

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
