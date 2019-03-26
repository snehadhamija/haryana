package com.stanzaliving.api.dao;

import com.stanzaliving.api.model.LuggageActivityStatus;

public interface LuggageActivityStatusDao {

	void save(LuggageActivityStatus luggageActivityStatus);

	LuggageActivityStatus findById(int id);
}
