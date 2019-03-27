package com.stanzaliving.api.dao;

import java.util.List;

import com.stanzaliving.api.model.Luggage;

public interface LuggageDao {

	void save(Luggage luggage);

	Luggage findById(int id);

	List<Luggage> findAllLuggages();
}
