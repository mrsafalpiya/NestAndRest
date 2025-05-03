package com.nestandrest.service;

import com.nestandrest.dao.ProductDAO;
import com.nestandrest.model.Product;

public class ProductService {
    private ProductDAO productDAO;

    public ProductService() {
        productDAO = new ProductDAO();
    }

    public void addProduct(Product product) {
        try {
            productDAO.insertProduct(product);
            System.out.println("✅ Product saved to DB: " + product.getName());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("❌ Failed to save product: " + product.getName());
        }
    }
}
