package com.nestandrest.controller;

import java.io.IOException;
import java.util.List;

import com.nestandrest.model.CategoryModel;
import com.nestandrest.model.ProductModel;
import com.nestandrest.service.ProductService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@WebServlet(asyncSupported = true, urlPatterns = { "/home", "/" })
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductService productService;

	@Override
	public void init() throws ServletException {
		this.productService = new ProductService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Redirect to 404 if required
		String uri = req.getRequestURI();
		if (!(uri.equalsIgnoreCase("/nest-and-rest/")) && !(uri.equalsIgnoreCase("/nest-and-rest/home"))) {
			resp.sendRedirect(req.getContextPath() + "/error404");
			return;
		}

		// Get the list of all categories
		List<CategoryModel> allCategories = this.productService.getAllCategories();
		req.setAttribute("allCategories", allCategories);

		// Get all products
		List<ProductModel> allProducts = this.productService.getProducts("", null);

		// Get products with discounts by filtering it from allProducts
		List<ProductModel> productsWithDiscounts = allProducts.stream()
				.filter(product -> product.getDiscountedPrice() > 0).toList();
		req.setAttribute("productsWithDiscounts", productsWithDiscounts);

		// Get products from allProducts of each categories in allCategories and store
		// it in a map
		Map<Integer, List<ProductModel>> categorizedProducts = new HashMap<>();
		for (CategoryModel category : allCategories) {
			List<ProductModel> productsInCategory = allProducts.stream()
					.filter(product -> product.getCategoryId() == category.getCategoryId()).toList();
			categorizedProducts.put(category.getCategoryId(), productsInCategory);
		}
		req.setAttribute("categorizedProducts", categorizedProducts);

		req.getRequestDispatcher("WEB-INF/pages/home.jsp").forward(req, resp);
	}
}
