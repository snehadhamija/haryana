package com.stanzaliving.api.service;

import com.stanzaliving.api.model.LuggageActivity;

public interface LuggageActivityService {

	void save(LuggageActivity luggageActivity);

	LuggageActivity findById(int id);
}
