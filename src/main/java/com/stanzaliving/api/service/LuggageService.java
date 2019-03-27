package com.stanzaliving.api.service;

import java.util.List;

import com.stanzaliving.api.model.Luggage;

public interface LuggageService {

	void save(Luggage luggage);

	Luggage findById(int id);

	List<Luggage> findAllLuggages();
}
