package com.stanzaliving.api.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.stanzaliving.api.model.LuggageCategory;

@Repository("luggageCategoryDao")
public class LuggageCategoryDaoImpl extends AbstractDao<Integer, LuggageCategory> implements LuggageCategoryDao {

	@Override
	public void save(LuggageCategory luggageCategory) {
		persist(luggageCategory);
	}

	@Override
	public LuggageCategory findById(int id) {
		return getByKey(id);
	}

	@Override
	public List<LuggageCategory> findAllLuggageCategories() {
		Criteria crit = createEntityCriteria();
		return (List<LuggageCategory>) crit.setResultTransformer(crit.DISTINCT_ROOT_ENTITY).list();
	}
}
