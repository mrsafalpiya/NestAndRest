package com.nestandrest.controller;

import com.nestandrest.model.Product;
import com.nestandrest.dao.ProductDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(asyncSupported = true, urlPatterns = {"/products"})
public class ProductListingController extends HttpServlet {
    private static final int PAGE_SIZE = 12;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String category = req.getParameter("category");
        if (category == null || category.trim().isEmpty()) {
            category = "all";
        }

        String search = req.getParameter("search");
        String pageParam = req.getParameter("page");
        int page = (pageParam != null) ? Integer.parseInt(pageParam) : 1;

        try {
            ProductDAO productDAO = new ProductDAO();
            List<Product> allProducts;

            // ✅ load products from DB
            if (search != null && !search.trim().isEmpty()) {
                allProducts = productDAO.searchProductsByName(search);
            } else {
                allProducts = productDAO.getAllProducts();
            }

            // ✅ apply category filter
            final String categoryFilter = category;
            List<Product> filteredProducts = categoryFilter.equalsIgnoreCase("all") ?
                    allProducts :
                    allProducts.stream()
                            .filter(p -> p.getCategory().equalsIgnoreCase(categoryFilter))
                            .collect(Collectors.toList());

            // ✅ pagination
            int totalProducts = filteredProducts.size();
            int totalPages = (int) Math.ceil((double) totalProducts / PAGE_SIZE);
            int start = (page - 1) * PAGE_SIZE;
            int end = Math.min(start + PAGE_SIZE, totalProducts);
            List<Product> paginated = start >= totalProducts ? List.of() : filteredProducts.subList(start, end);

            // ✅ pass attributes to JSP
            req.setAttribute("products", paginated);
            req.setAttribute("totalPages", totalPages);
            req.setAttribute("currentPage", page);
            req.setAttribute("selectedCategory", category);
            req.setAttribute("searchKeyword", search != null ? search : "");

            req.getRequestDispatcher("/WEB-INF/pages/products/product-listing.jsp").forward(req, resp);

        } catch (SQLException | ClassNotFoundException e) {
            throw new ServletException(e);
        }
    }
}
