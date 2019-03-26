package com.stanzaliving.api.dao;

import com.stanzaliving.api.model.LuggageHostel;

public interface LuggageHostelDao {

	void save(LuggageHostel luggageHostel);

	LuggageHostel findById(int id);
}
