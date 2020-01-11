package com.stanzaliving.api.service;

import java.util.List;

import com.stanzaliving.api.model.Product;

public interface ProductService {

	void save(Product product);

	Product findById(int id);

	List<Product> findAllProducts();

	List<Product> findAllActiveProducts(Boolean isActive);
}
