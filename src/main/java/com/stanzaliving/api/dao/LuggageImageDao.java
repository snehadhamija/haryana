package com.stanzaliving.api.dao;

import com.stanzaliving.api.model.LuggageImage;

public interface LuggageImageDao {

	void save(LuggageImage luggageImage);

	LuggageImage findById(int id);
}
