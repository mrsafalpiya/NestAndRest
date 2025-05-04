package com.nestandrest.service;

import com.nestandrest.dao.CategoryDAO;
import com.nestandrest.dao.ProductDAO;
import com.nestandrest.model.Category;
import com.nestandrest.model.Product;
import com.nestandrest.config.DbConfig;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ProductService {
    private ProductDAO productDAO;
    private CategoryDAO categoryDAO;

    public ProductService() {
        try {
            Connection connection = DbConfig.getDbConnection();
            this.productDAO = new ProductDAO(connection);
            this.categoryDAO = new CategoryDAO(connection);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void addProduct(Product product) {
        try {
            product.setStock(product.getQuantity()); // Set stock equal to quantity
            product.setInStock(product.getQuantity() > 0);
            productDAO.addProduct(product);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Product> getAllProducts() {
        try {
            return productDAO.getAllProducts();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Product getProductById(int productId) {
        try {
            return productDAO.getProductById(productId);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void updateProduct(Product product) {
        try {
            product.setStock(product.getQuantity());
            product.setInStock(product.getQuantity() > 0);
            productDAO.updateProduct(product);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteProduct(int productId) {
        try {
            productDAO.deleteProduct(productId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Category> getAllCategories() {
        try {
            return categoryDAO.getAllCategories();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}