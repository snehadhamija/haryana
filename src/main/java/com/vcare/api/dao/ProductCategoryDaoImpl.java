package com.vcare.api.dao;

import java.util.List;
import java.util.Objects;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.vcare.api.model.ProductCategory;

@Repository("productCategoryDao")
public class ProductCategoryDaoImpl extends AbstractDao<Integer, ProductCategory> implements ProductCategoryDao {

	@Override
	public void save(ProductCategory productCategory) {
		persist(productCategory);
	}

	@Override
	public ProductCategory findById(int id) {
		return getByKey(id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProductCategory> findAllProductCategories() {
		Criteria crit = createEntityCriteria();
		crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		crit.addOrder(Order.asc("sequenceId"));
		return (List<ProductCategory>) crit.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProductCategory> findAllActiveProductCategories(Boolean isActive) {
		Criteria crit = createEntityCriteria();
		if (Objects.nonNull(isActive)) {
			crit.add(Restrictions.eq("isActive", isActive));
		}
		crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		crit.addOrder(Order.asc("sequenceId"));
		return (List<ProductCategory>) crit.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProductCategory> findAllProductCategoriesForProductId(Boolean isActive, Integer productId) {
		Criteria crit = createEntityCriteria();
		if (Objects.nonNull(isActive)) {
			crit.add(Restrictions.eq("isActive", isActive));
		}
		if (Objects.nonNull(productId)) {
			crit.createAlias("products", "product");
			crit.add(Restrictions.eq("product.productId", productId));
		}
		crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		crit.addOrder(Order.asc("sequenceId"));
		return (List<ProductCategory>) crit.list();
	}
}
