package com.nestandrest.controller;

import com.nestandrest.dao.CartDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import com.nestandrest.model.Product;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/add-to-cart")
public class AddToCartController extends HttpServlet {
    private CartDAO cartDAO;

    @Override
    public void init() throws ServletException {
        cartDAO = new CartDAO();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int userId = (int) req.getSession().getAttribute("userId");
        int productId = Integer.parseInt(req.getParameter("productId"));

        try {
            int cartId = cartDAO.getActiveCartId(userId);
            if (cartId == -1) {
                cartId = cartDAO.createCart(userId);
            }

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

            resp.sendRedirect(req.getContextPath() + "/cart.jsp");

        } catch (Exception e) {  // ✅ use generic Exception
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error adding product to cart.");
        }
    }

}
