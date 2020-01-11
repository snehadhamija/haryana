package com.stanzaliving.api.dao;

import java.util.List;

import com.stanzaliving.api.model.ProductCategory;

public interface ProductCategoryDao {

	void save(ProductCategory productCategory);

	ProductCategory findById(int id);

	List<ProductCategory> findAllProductCategories();

	List<ProductCategory> findAllActiveProductCategories(Boolean isActive);
}
