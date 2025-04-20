package com.nestandrest.controller;

import com.nestandrest.model.Product;
import com.nestandrest.util.ProductData;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/admin/products/details")
public class AdminProductDetailsController extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String idParam = req.getParameter("id");

            if (idParam != null) {
                int id = Integer.parseInt(idParam);
                List<Product> allProducts = ProductData.getAllProducts();

                if (id >= 0 && id < allProducts.size()) {
                    Product product = allProducts.get(id);
                    req.setAttribute("product", product);
                } else {
                    req.setAttribute("error", "Invalid product ID");
                }
            } else {
                req.setAttribute("error", "Product ID is missing");
            }
        } catch (NumberFormatException e) {
            req.setAttribute("error", "Invalid product ID format");
        }

        req.getRequestDispatcher("/WEB-INF/pages/admin-products/admin-product-details.jsp").forward(req, resp);
    }
}
