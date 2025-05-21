package com.nestandrest.controller;

import com.nestandrest.model.Product;
import com.nestandrest.service.ProductService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.*;
import java.sql.SQLException;

/**
 * Controller servlet responsible for handling the addition of new products by admin users.
 * It supports GET and POST methods to render the form and process product data including image upload.
 * @author Safal Piya 
 * @author Bhumika Karki
 * @author Ayush Shrestha
 */
@WebServlet(asyncSupported = true, urlPatterns = "/admin/products/add")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 * 50)
public class AdminAddProductController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ProductService productService;

    
    /**
     * Initializes the ProductService instance when the servlet is first created.
     */
    @Override
    public void init() throws ServletException {
        productService = new ProductService();
    }

    
    /**
     * Handles GET requests to show the "Add Product" form with category data.
     *
     * @param request  HTTP request containing data from the client
     * @param response HTTP response sent back to the client
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("categories", productService.getAllCategories());
        request.getRequestDispatcher("/WEB-INF/pages/admin-products/admin-add-product.jsp").forward(request, response);
    }

    
    /**
     * Handles POST requests to process form submission, validate input,
     * handle image upload, and persist product information.
     *
     * @param request  HTTP request containing form and file data
     * @param response HTTP response sent back to the client
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Retrieve form data
            String name = request.getParameter("productName");
            String subDescription = request.getParameter("subDescription");
            String fullDescription = request.getParameter("fullDescription");
            String category = request.getParameter("category");
            String color = request.getParameter("colors");
            String size = request.getParameter("sizes");
            double price = Double.parseDouble(request.getParameter("price"));
            String discountedPriceStr = request.getParameter("discountedPrice");
            double discountedPrice = (discountedPriceStr != null && !discountedPriceStr.isEmpty()) ? Double.parseDouble(discountedPriceStr) : 0.0;
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            boolean inStock = quantity > 0;
            boolean isPublished = request.getParameter("publish") != null;

            // Handle file upload
            String imagePath = null;
            Part filePart = request.getPart("images");
            if (filePart != null && filePart.getSize() > 0) {
                String fileName = extractFileName(filePart);
                String savePath = getServletContext().getRealPath("/Uploads") + File.separator + fileName;
                File fileSaveDir = new File(getServletContext().getRealPath("/Uploads"));
                if (!fileSaveDir.exists()) {
                    fileSaveDir.mkdir();
                }
                filePart.write(savePath);
                imagePath = "Uploads/" + fileName;
            }

            // Validate input
            if (name == null || name.trim().isEmpty() || price <= 0 || category == null || category.trim().isEmpty() || quantity < 0) {
                request.setAttribute("error", "Invalid input. Please check the form.");
                request.setAttribute("categories", productService.getAllCategories());
                request.getRequestDispatcher("/WEB-INF/pages/admin-products/admin-add-product.jsp").forward(request, response);
                return;
            }

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
            product.setImage(imagePath);

            // Save the product
            productService.addProduct(product);

            // Redirect to product list
            response.sendRedirect(request.getContextPath() + "/admin/products/list");
        } catch (NumberFormatException e) {
            e.printStackTrace();
            request.setAttribute("error", "Error adding product: " + e.getMessage());
            request.setAttribute("categories", productService.getAllCategories());
            request.getRequestDispatcher("/WEB-INF/pages/admin-products/admin-add-product.jsp").forward(request, response);
        }
    }

    
    /**
     * Extracts the file name from the Content-Disposition header of the uploaded file.
     *
     * @param part The Part object representing the uploaded file
     * @return The extracted file name as a string
     */
    private String extractFileName(Part part) {
        String contentDisposition = part.getHeader("content-disposition");
        String[] items = contentDisposition.split(";");
        for (String item : items) {
            if (item.trim().startsWith("filename")) {
                return item.substring(item.indexOf("=") + 2, item.length() - 1);
            }
        }
        return "";
    }
}