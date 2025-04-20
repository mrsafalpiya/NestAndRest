package com.nestandrest.service;

import com.nestandrest.model.Product;


public class ProductService {
	
	public void addProduct(Product product) {
	    // TODO:can save to a database or static list here
	    System.out.println("Product added: " + product.getName());
	}

}
