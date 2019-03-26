package com.stanzaliving.api.dao;

import com.stanzaliving.api.model.LuggageStoreRoom;

public interface LuggageStoreRoomDao {

	void save(LuggageStoreRoom luggageActivity);

	LuggageStoreRoom findById(int id);
}
