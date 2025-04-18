package com.nestandrest.controller;

import com.nestandrest.model.Product;
import com.nestandrest.util.ProductData;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(asyncSupported = true, urlPatterns = {"/products"})
public class ProductListingController extends HttpServlet {
    private static final int PAGE_SIZE = 6;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	String category = req.getParameter("category");
    	if (category == null || category.trim().isEmpty()) {
    	    category = "all"; // Ensure this is set
    	}
    	final String categoryFilter = category;
    	
    	req.setAttribute("selectedCategory", category);

        String pageParam = req.getParameter("page");
        int page = (pageParam != null) ? Integer.parseInt(pageParam) : 1;


     // Get all products
        List<Product> allProducts = ProductData.getAllProducts();

        // Filter
        List<Product> filteredProducts = categoryFilter.equals("all") ?
                allProducts :
                allProducts.stream()
                           .filter(p -> p.getCategory().equalsIgnoreCase(categoryFilter))
                           .collect(Collectors.toList());

        // Pagination logic
        int totalProducts = filteredProducts.size();
        int totalPages = (int) Math.ceil((double) totalProducts / PAGE_SIZE);

        int start = (page - 1) * PAGE_SIZE;
        int end = Math.min(start + PAGE_SIZE, totalProducts);

        // Handle out-of-range start index
        List<Product> paginated;
        if (start >= totalProducts) {
            paginated = List.of(); // return an empty list
        } else {
            paginated = filteredProducts.subList(start, end);
        }

        // Set attributes
        req.setAttribute("products", paginated);
        req.setAttribute("totalPages", totalPages);
        req.setAttribute("currentPage", page);
        req.setAttribute("selectedCategory", category);

        req.getRequestDispatcher("/WEB-INF/pages/products/product-listing.jsp").forward(req, resp);
    }
}
