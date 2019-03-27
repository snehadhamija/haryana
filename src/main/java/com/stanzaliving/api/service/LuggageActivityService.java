package com.stanzaliving.api.service;

import java.util.List;

import com.stanzaliving.api.model.LuggageActivity;

public interface LuggageActivityService {

	void save(LuggageActivity luggageActivity);

	LuggageActivity findById(int id);

	List<LuggageActivity> findAllLuggageActivities();

	List<LuggageActivity> findAllActiveLuggageActivities();
}
