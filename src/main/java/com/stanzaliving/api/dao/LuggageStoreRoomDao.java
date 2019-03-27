package com.stanzaliving.api.dao;

import java.util.List;

import com.stanzaliving.api.model.LuggageStoreRoom;

public interface LuggageStoreRoomDao {

	void save(LuggageStoreRoom luggageActivity);

	LuggageStoreRoom findById(int id);

	List<LuggageStoreRoom> findAllLuggageStoreRooms();
}
