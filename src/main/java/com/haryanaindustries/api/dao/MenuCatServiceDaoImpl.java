package com.haryanaindustries.api.dao;

import java.util.List;
import java.util.Objects;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.haryanaindustries.api.model.MenuCatService;

@Repository("menuCatServiceDao")
public class MenuCatServiceDaoImpl extends AbstractDao<Integer, MenuCatService> implements MenuCatServiceDao {

	@Override
	public void save(MenuCatService menuCatService) {
		persist(menuCatService);
	}

	@Override
	public MenuCatService findById(int id) {
		return getByKey(id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MenuCatService> findAllMenuCatServices(Boolean isActive, Integer menuCategoryId) {
		Criteria crit = createEntityCriteria();
		crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		crit.createAlias("menuCategory", "menuCategory");
		crit.createAlias("menuCategory.menu", "menu");
		if (Objects.nonNull(isActive)) {
			crit.add(Restrictions.eq("isActive", isActive));
		}
		if (Objects.nonNull(menuCategoryId)) {
			crit.add(Restrictions.eq("menuCategory.id", menuCategoryId));
		}
		crit.addOrder(Order.asc("sequenceId"));
		return (List<MenuCatService>) crit.list();
	}
}
