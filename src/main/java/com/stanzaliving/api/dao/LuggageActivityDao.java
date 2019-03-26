package com.stanzaliving.api.dao;

import com.stanzaliving.api.model.LuggageActivity;

public interface LuggageActivityDao {

	void save(LuggageActivity luggageActivity);

	LuggageActivity findById(int id);
}
