package com.stanzaliving.api.dao;

import java.util.List;

import com.stanzaliving.api.model.LuggageActivityStatus;

public interface LuggageActivityStatusDao {

	void save(LuggageActivityStatus luggageActivityStatus);

	LuggageActivityStatus findById(int id);

	List<LuggageActivityStatus> findAllLuggageActivityStatuses();
}
