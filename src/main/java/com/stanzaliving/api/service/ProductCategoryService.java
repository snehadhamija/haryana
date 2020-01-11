package com.stanzaliving.api.service;

import java.util.List;

import com.stanzaliving.api.model.ProductCategory;

public interface ProductCategoryService {

	void save(ProductCategory productCategory);

	ProductCategory findById(int id);

	List<ProductCategory> findAllProductCategories();

	List<ProductCategory> findAllActiveProductCategories(Boolean isActive);
}
