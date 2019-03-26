package com.stanzaliving.api.service;

import com.stanzaliving.api.model.LuggageCategory;

public interface LuggageCategoryService {

	void save(LuggageCategory luggageCategory);

	LuggageCategory findById(int id);
}
