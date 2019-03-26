package com.stanzaliving.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stanzaliving.api.dao.LuggageStoreRoomDao;
import com.stanzaliving.api.model.LuggageStoreRoom;

@Service("luggageStoreRoomService")
@Transactional
public class LuggageStoreRoomServiceImpl implements LuggageStoreRoomService {

	@Autowired
	private LuggageStoreRoomDao dao;

	@Override
	public void save(LuggageStoreRoom luggageStoreRoom) {
		dao.save(luggageStoreRoom);
	}

	@Override
	public LuggageStoreRoom findById(int id) {
		return dao.findById(id);
	}
}
