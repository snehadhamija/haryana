package com.haryanaindustries.api.dao;

import java.util.List;
import java.util.Objects;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.haryanaindustries.api.model.Menu;

@Repository("menuDao")
public class MenuDaoImpl extends AbstractDao<Integer, Menu> implements MenuDao {

	@Override
	public void save(Menu menu) {
		persist(menu);
	}

	@Override
	public Menu findById(int id) {
		return getByKey(id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Menu> findAllMenus(Boolean isActive) {
		Criteria crit = createEntityCriteria();
		crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		if (Objects.nonNull(isActive)) {
			crit.add(Restrictions.eq("isActive", isActive));
		}
		crit.addOrder(Order.asc("sequenceId"));
		return (List<Menu>) crit.list();
	}
}
