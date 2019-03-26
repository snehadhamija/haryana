package com.stanzaliving.api.service;

import com.stanzaliving.api.model.LuggageStoreRoom;

public interface LuggageStoreRoomService {

	void save(LuggageStoreRoom luggageStoreRoom);

	LuggageStoreRoom findById(int id);
}
