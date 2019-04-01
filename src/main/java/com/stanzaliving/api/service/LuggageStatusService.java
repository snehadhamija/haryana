package com.stanzaliving.api.service;

import java.util.List;

import com.stanzaliving.api.model.LuggageStatus;

public interface LuggageStatusService {

	void save(LuggageStatus luggageStatus);

	LuggageStatus findById(int id);

	List<LuggageStatus> findAllLuggageStatuses();
}
