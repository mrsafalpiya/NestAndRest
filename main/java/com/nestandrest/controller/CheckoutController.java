package com.nestandrest.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/checkout-cart")
public class CheckoutController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    // Forward or redirect to your cart view page
		request.getRequestDispatcher("/WEB-INF/pages/checkout/checkout-cart.jsp").forward(request, response);

	}
}
