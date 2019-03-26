package com.stanzaliving.api.dao;

import org.springframework.stereotype.Repository;

import com.stanzaliving.api.model.LuggageActivity;

@Repository("luggageActivityDao")
public class LuggageActivityDaoImpl extends AbstractDao<Integer, LuggageActivity> implements LuggageActivityDao {

	@Override
	public void save(LuggageActivity luggageActivity) {
		persist(luggageActivity);
	}

	@Override
	public LuggageActivity findById(int id) {
		return getByKey(id);
	}
}
