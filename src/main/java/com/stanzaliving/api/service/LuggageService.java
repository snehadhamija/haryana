package com.stanzaliving.api.service;

import com.stanzaliving.api.model.Luggage;

public interface LuggageService {

	void save(Luggage luggage);

	Luggage findById(int id);
}
