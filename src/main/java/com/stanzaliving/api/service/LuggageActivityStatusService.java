package com.stanzaliving.api.service;

import java.util.List;

import com.stanzaliving.api.model.LuggageActivityStatus;

public interface LuggageActivityStatusService {

	void save(LuggageActivityStatus luggageActivityStatus);

	LuggageActivityStatus findById(int id);

	List<LuggageActivityStatus> findAllLuggageActivityStatuses();
}
