package com.vcare.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vcare.api.dao.ProductDao;
import com.vcare.api.model.Product;

@Service("productService")
@Transactional
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDao dao;

	@Override
	public void save(Product product) {
		dao.save(product);
	}

	@Override
	public Product findById(int id) {
		return dao.findById(id);
	}

	@Override
	public List<Product> findAllProducts() {
		return dao.findAllProducts();
	}

	@Override
	public List<Product> findAllActiveProducts(Boolean isActive) {
		return dao.findAllActiveProducts(isActive);
	}
}
