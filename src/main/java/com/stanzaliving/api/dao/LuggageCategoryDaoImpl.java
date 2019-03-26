package com.stanzaliving.api.dao;

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
}
