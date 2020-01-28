package com.vcare.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vcare.api.dao.ProductVariantDao;
import com.vcare.api.model.ProductVariant;

@Service("productVariantService")
@Transactional
public class ProductVariantServiceImpl implements ProductVariantService {

	@Autowired
	private ProductVariantDao dao;

	@Override
	public void save(ProductVariant productVariant) {
		dao.save(productVariant);
	}

	@Override
	public ProductVariant findById(int id) {
		return dao.findById(id);
	}

	@Override
	public List<ProductVariant> findAllProductVariants() {
		return dao.findAllProductVariants();
	}

	@Override
	public List<ProductVariant> findAllActiveProductVariants(Boolean isActive) {
		return dao.findAllActiveProductVariants(isActive);
	}

	@Override
	public List<ProductVariant> findProductVariantsForProduct(Integer productId) {
		return dao.findProductVariantsForProduct(productId);
	}
}
