package com.vcare.api.dao;

import java.util.List;
import java.util.Objects;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.vcare.api.model.ProductVariant;

@Repository("productVariantDao")
public class ProductVariantDaoImpl extends AbstractDao<Integer, ProductVariant> implements ProductVariantDao {

	@Override
	public void save(ProductVariant productVariant) {
		persist(productVariant);
	}

	@Override
	public ProductVariant findById(int id) {
		return getByKey(id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProductVariant> findAllProductVariants() {
		Criteria crit = createEntityCriteria();
		crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		crit.addOrder(Order.asc("sequenceId"));
		return (List<ProductVariant>) crit.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProductVariant> findAllActiveProductVariants(Boolean isActive) {
		Criteria crit = createEntityCriteria();
		if (Objects.nonNull(isActive)) {
			crit.add(Restrictions.eq("isActive", isActive));
		}
		crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		crit.addOrder(Order.asc("sequenceId"));
		return (List<ProductVariant>) crit.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ProductVariant> findProductVariantsForProduct(Integer productId) {
		Criteria crit = createEntityCriteria();
		crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		crit.addOrder(Order.asc("sequenceId"));
		if (Objects.nonNull(productId)) {
			crit.add(Restrictions.eq("product.productId", productId));
		}
		return (List<ProductVariant>) crit.list();
	}
}
