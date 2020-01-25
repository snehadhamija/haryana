package com.stanzaliving.api.service;

import java.util.List;

import com.stanzaliving.api.model.ProductVariant;

public interface ProductVariantService {

	void save(ProductVariant productVariant);

	ProductVariant findById(int id);

	List<ProductVariant> findAllProductVariants();

	List<ProductVariant> findAllActiveProductVariants(Boolean isActive);

	List<ProductVariant> findProductVariantsForProduct(Integer productId);
}
