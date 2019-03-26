package com.stanzaliving.api.service;

import com.stanzaliving.api.model.LuggageActivityStatus;

public interface LuggageActivityStatusService {

	void save(LuggageActivityStatus luggageActivityStatus);

	LuggageActivityStatus findById(int id);
}
