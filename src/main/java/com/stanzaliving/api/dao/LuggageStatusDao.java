package com.stanzaliving.api.dao;

import java.util.List;

import com.stanzaliving.api.model.LuggageStatus;

public interface LuggageStatusDao {

	void save(LuggageStatus luggageStatus);

	LuggageStatus findById(int id);

	List<LuggageStatus> findAllLuggageStatuses();
}
