package com.nestandrest.controller;

import com.nestandrest.dao.CartDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import com.nestandrest.model.Product;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Controller that handles the logic for adding a product to the user's cart.
 * It ensures a cart exists for the user, adds the product, or updates its quantity if it already exists.
 */
@WebServlet("/add-to-cart")
public class AddToCartController extends HttpServlet {
    private CartDAO cartDAO;

    
    /**
     * Initializes the CartDAO when the servlet is created.
     */
    @Override
    public void init() throws ServletException {
        cartDAO = new CartDAO();
    }

    
    /**
     * Handles POST requests to add a product to the cart.
     * - Checks if the user has an active cart; creates one if not.
     * - If the product is already in the cart, increases its quantity.
     * - Otherwise, adds the product as a new item to the cart.
     *
     * @param req  HttpServletRequest containing productId and session with userId
     * @param resp HttpServletResponse used for redirection or error reporting
     * @throws ServletException if servlet-specific error occurs
     * @throws IOException      if I/O error occurs during processing
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int userId = (int) req.getSession().getAttribute("userId");
        int productId = Integer.parseInt(req.getParameter("productId"));

        try {
        	// Get or create cart for the user
            int cartId = cartDAO.getActiveCartId(userId);
            if (cartId == -1) {
                cartId = cartDAO.createCart(userId);
            }

         // Get or create cart for the user
            if (cartDAO.isProductInCart(cartId, productId)) {
                int currentQuantity = cartDAO.getCartItems(cartId).stream()
                        .filter(item -> item.getProductId() == productId)
                        .mapToInt(Product::getQuantity)
                        .findFirst()
                        .orElse(1);

                int newQuantity = currentQuantity + 1;
                cartDAO.updateProductQuantity(cartId, productId, newQuantity);
            } else {
                cartDAO.addProductToCart(cartId, productId);
            }

         // Redirect to cart page
            resp.sendRedirect(req.getContextPath() + "/cart.jsp");

        } catch (Exception e) {  // ✅ use generic Exception
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error adding product to cart.");
        }
    }

}
