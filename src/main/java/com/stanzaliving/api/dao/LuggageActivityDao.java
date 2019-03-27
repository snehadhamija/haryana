package com.stanzaliving.api.dao;

import java.util.List;

import com.stanzaliving.api.model.LuggageActivity;

public interface LuggageActivityDao {

	void save(LuggageActivity luggageActivity);

	LuggageActivity findById(int id);

	List<LuggageActivity> findAllLuggageActivities();

	List<LuggageActivity> findAllActiveLuggageActivities();
}
