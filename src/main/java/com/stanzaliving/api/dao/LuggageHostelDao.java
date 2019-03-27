package com.stanzaliving.api.dao;

import java.util.List;

import com.stanzaliving.api.model.LuggageHostel;

public interface LuggageHostelDao {

	void save(LuggageHostel luggageHostel);

	LuggageHostel findById(int id);

	List<LuggageHostel> findAllLuggageHostels();
}
