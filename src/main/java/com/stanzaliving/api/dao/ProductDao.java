package com.stanzaliving.api.dao;

import java.util.List;

import com.stanzaliving.api.model.Product;

public interface ProductDao {

	void save(Product product);

	Product findById(int id);

	List<Product> findAllProducts();

	List<Product> findAllActiveProducts(Boolean isActive);
}
