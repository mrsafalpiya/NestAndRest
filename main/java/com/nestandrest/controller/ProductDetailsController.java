package com.nestandrest.controller;

import java.io.IOException;

import com.nestandrest.model.Product;
import com.nestandrest.model.ProductModel;
import com.nestandrest.model.ProductVariantModel;
import com.nestandrest.service.ProductService;
import com.nestandrest.util.ProductData;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet(asyncSupported = true, urlPatterns = { "/product-details" })
public class ProductDetailsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductService productService;

	@Override
	public void init() throws ServletException {
		this.productService = new ProductService();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String idParam = req.getParameter("id");

			if (idParam != null) {
				int id = Integer.parseInt(idParam);

				if (id >= 0) {
					ProductModel product = productService.getById(id);
					List<ProductVariantModel> productVariants = productService.getVariantsOfAProduct(id);

					req.setAttribute("product", product);
					req.setAttribute("productVariants", productVariants);
				} else {
					req.setAttribute("error", "Invalid product ID");
				}
			} else {
				req.setAttribute("error", "Product ID is missing");
			}
		} catch (NumberFormatException e) {
			req.setAttribute("error", "Invalid product ID format");
		}

		req.getRequestDispatcher("/WEB-INF/pages/products/product-details.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/pages/products/product-details.jsp").forward(req, resp);
	}
}