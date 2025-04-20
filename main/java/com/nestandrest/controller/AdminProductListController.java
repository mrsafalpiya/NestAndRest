package com.nestandrest.controller;

import java.io.IOException;
import java.util.List;

import com.nestandrest.model.Product;
import com.nestandrest.util.ProductData;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(asyncSupported = true, urlPatterns = "/admin/products/list")
public class AdminProductListController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final int PRODUCTS_PER_PAGE = 6;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Product> allProducts = ProductData.getAllProducts();
        int totalProducts = allProducts.size();

        int currentPage = 1;
        String pageParam = request.getParameter("page");
        if (pageParam != null && !pageParam.isEmpty()) {
            try {
                currentPage = Integer.parseInt(pageParam);
            } catch (NumberFormatException e) {
                currentPage = 1;
            }
        }

        int start = (currentPage - 1) * PRODUCTS_PER_PAGE;
        int end = Math.min(start + PRODUCTS_PER_PAGE, totalProducts);
        List<Product> paginatedProducts = allProducts.subList(start, end);

        int totalPages = (int) Math.ceil((double) totalProducts / PRODUCTS_PER_PAGE);

        request.setAttribute("products", paginatedProducts);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("totalProducts", totalProducts);

        request.getRequestDispatcher("/WEB-INF/pages/admin-products/admin-product-list.jsp")
                .forward(request, response);
    }
}
