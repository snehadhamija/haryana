package com.stanzaliving.api.dao;

import org.springframework.stereotype.Repository;

import com.stanzaliving.api.model.Luggage;

@Repository("luggageDao")
public class LuggageDaoImpl extends AbstractDao<Integer, Luggage> implements LuggageDao {

	@Override
	public void save(Luggage luggage) {
		persist(luggage);
	}

	@Override
	public Luggage findById(int id) {
		return getByKey(id);
	}
}
