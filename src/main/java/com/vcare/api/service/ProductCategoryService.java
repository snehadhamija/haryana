package com.vcare.api.service;

import java.util.List;

import com.vcare.api.model.ProductCategory;

public interface ProductCategoryService {

	void save(ProductCategory productCategory);

	ProductCategory findById(int id);

	List<ProductCategory> findAllProductCategories();

	List<ProductCategory> findAllActiveProductCategories(Boolean isActive);

	List<ProductCategory> findAllProductCategoriesForProductId(Boolean isActive, Integer productId);
}
