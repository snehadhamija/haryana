package com.stanzaliving.api.dao;

import org.springframework.stereotype.Repository;

import com.stanzaliving.api.model.LuggageStoreRoom;

@Repository("luggageStoreRoomDao")
public class LuggageStoreRoomDaoImpl extends AbstractDao<Integer, LuggageStoreRoom> implements LuggageStoreRoomDao {

	@Override
	public void save(LuggageStoreRoom luggageStoreRoom) {
		persist(luggageStoreRoom);
	}

	@Override
	public LuggageStoreRoom findById(int id) {
		return getByKey(id);
	}
}
