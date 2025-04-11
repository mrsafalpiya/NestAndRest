package com.nestandrest.controller;

import java.io.IOException;
import java.util.UUID;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// This maps the controller to /checkout path
@WebServlet("/product-checkout-complete")
public class ProductCheckOutController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // Generate a random order ID (UUID format)
        String orderId = UUID.randomUUID().toString();

        // Set it as a request attribute to access in JSP
        req.setAttribute("orderId", orderId);

        // Forward to JSP view
        req.getRequestDispatcher("/WEB-INF/pages/checkout/product-checkout-complete.jsp").forward(req, resp);
    }
}

