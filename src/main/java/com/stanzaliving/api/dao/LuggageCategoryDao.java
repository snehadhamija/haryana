package com.stanzaliving.api.dao;

import com.stanzaliving.api.model.LuggageCategory;

public interface LuggageCategoryDao {

	void save(LuggageCategory luggageCategory);

	LuggageCategory findById(int id);
}
