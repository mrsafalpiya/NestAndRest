package com.nestandrest.controller;

import com.nestandrest.model.Product;

import com.nestandrest.service.ProductService;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletException;
import java.io.IOException;

@WebServlet(asyncSupported = true, urlPatterns = "/admin/products/add")
public class AdminAddProductController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private ProductService productService;

    @Override
    public void init() throws ServletException {
        productService = new ProductService(); 
    }

    // Show the add product form
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/pages/admin-products/admin-add-product.jsp").forward(request, response);
    }

    // Handle form submission
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Retrieve form data
        String name = request.getParameter("name");
        String subDescription = request.getParameter("subDescription");
        String fullDescription = request.getParameter("fullDescription");
        String category = request.getParameter("category");
        String color = request.getParameter("color");
        String size = request.getParameter("size");
        double price = Double.parseDouble(request.getParameter("price"));
        double discountedPrice = Double.parseDouble(request.getParameter("discountedPrice"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        boolean inStock = request.getParameter("inStock") != null;
        boolean isPublished = request.getParameter("publish") != null;
        String image = request.getParameter("image"); // Adjust if you use file upload

        // Create Product object
        Product product = new Product();
        product.setName(name);
        product.setSubDescription(subDescription);
        product.setFullDescription(fullDescription);
        product.setCategory(category);
        product.setColor(color);
        product.setSize(size);
        product.setPrice(price);
        product.setDiscountedPrice(discountedPrice);
        product.setQuantity(quantity);
        product.setInStock(inStock);
        product.setPublished(isPublished);
        product.setImage(image); 

        // Save the product
        productService.addProduct(product);

        // Redirect to product list
        response.sendRedirect(request.getContextPath() + "/admin/products/list");
    }
}
