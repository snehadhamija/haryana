package com.stanzaliving.api.dao;

import java.util.List;

import org.hibernate.Criteria;
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

	@Override
	public List<LuggageStoreRoom> findAllLuggageStoreRooms() {
		Criteria crit = createEntityCriteria();
		return (List<LuggageStoreRoom>) crit.setResultTransformer(crit.DISTINCT_ROOT_ENTITY).list();
	}
}
