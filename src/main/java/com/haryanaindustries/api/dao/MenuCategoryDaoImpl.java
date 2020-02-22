package com.haryanaindustries.api.dao;

import java.util.List;
import java.util.Objects;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.haryanaindustries.api.model.MenuCategory;

@Repository("menuCategoryDao")
public class MenuCategoryDaoImpl extends AbstractDao<Integer, MenuCategory> implements MenuCategoryDao {

	@Override
	public void save(MenuCategory menuCategory) {
		persist(menuCategory);
	}

	@Override
	public MenuCategory findById(int id) {
		return getByKey(id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MenuCategory> findAllMenuCategories(Boolean isActive, Integer menuId) {
		Criteria crit = createEntityCriteria();
		crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		crit.createAlias("menu", "menu");
		if (Objects.nonNull(isActive)) {
			crit.add(Restrictions.eq("isActive", isActive));
		}
		if (Objects.nonNull(menuId)) {
			crit.add(Restrictions.eq("menu.id", menuId));
		}
		crit.addOrder(Order.asc("sequenceId"));
		return (List<MenuCategory>) crit.list();
	}
}
