package com.stanzaliving.api.service;

import java.util.List;

import com.stanzaliving.api.model.LuggageStoreRoom;

public interface LuggageStoreRoomService {

	void save(LuggageStoreRoom luggageStoreRoom);

	LuggageStoreRoom findById(int id);

	List<LuggageStoreRoom> findAllLuggageStoreRooms();
}
