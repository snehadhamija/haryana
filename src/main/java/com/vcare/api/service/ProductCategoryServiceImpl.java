package com.vcare.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vcare.api.dao.ProductCategoryDao;
import com.vcare.api.model.ProductCategory;

@Service("productCategoryService")
@Transactional
public class ProductCategoryServiceImpl implements ProductCategoryService {

	@Autowired
	private ProductCategoryDao dao;

	@Override
	public void save(ProductCategory productCategory) {
		dao.save(productCategory);
	}

	@Override
	public ProductCategory findById(int id) {
		return dao.findById(id);
	}

	@Override
	public List<ProductCategory> findAllProductCategories() {
		return dao.findAllProductCategories();
	}

	@Override
	public List<ProductCategory> findAllActiveProductCategories(Boolean isActive) {
		return dao.findAllActiveProductCategories(isActive);
	}

	@Override
	public List<ProductCategory> findAllProductCategoriesForProductId(Boolean isActive, Integer productId) {
		return dao.findAllProductCategoriesForProductId(isActive, productId);
	}
}
