package com.stanzaliving.api.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.stanzaliving.api.model.LuggageHostel;

@Repository("luggageHostelDao")
public class LuggageHostelDaoImpl extends AbstractDao<Integer, LuggageHostel> implements LuggageHostelDao {

	@Override
	public void save(LuggageHostel luggageHostel) {
		persist(luggageHostel);
	}

	@Override
	public LuggageHostel findById(int id) {
		return getByKey(id);
	}

	@Override
	public List<LuggageHostel> findAllLuggageHostels() {
		Criteria crit = createEntityCriteria();
		return (List<LuggageHostel>) crit.setResultTransformer(crit.DISTINCT_ROOT_ENTITY).list();
	}
}
