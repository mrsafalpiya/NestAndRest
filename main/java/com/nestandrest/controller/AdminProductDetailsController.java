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

/**
 * Controller servlet for handling the display of product details in the admin panel.
 * It fetches a product by its ID from a static data source and forwards the data to the JSP view.
 * @author Safal Piya 
 * @author Bhumika Karki
 * @author Ayush Shrestha
 */
@WebServlet(asyncSupported = true, urlPatterns = "/admin/products/details")
public class AdminProductDetailsController extends HttpServlet {
	 /**
     * Handles GET requests for showing product details.
     * It retrieves the product ID from the request, fetches the product from the list,
     * and forwards the result to the product details JSP page.
     *
     * @param req  HttpServletRequest containing the product ID as a parameter
     * @param resp HttpServletResponse used to forward to the view
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs during request processing
     */
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
