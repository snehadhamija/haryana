package com.stanzaliving.api.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.stanzaliving.api.model.LuggageStatus;

@Repository("luggageStatusDao")
public class LuggageStatusDaoImpl extends AbstractDao<Integer, LuggageStatus> implements LuggageStatusDao {

	@Override
	public void save(LuggageStatus luggageStatus) {
		persist(luggageStatus);
	}

	@Override
	public LuggageStatus findById(int id) {
		return getByKey(id);
	}

	@Override
	public List<LuggageStatus> findAllLuggageStatuses() {
		Criteria crit = createEntityCriteria();
		return (List<LuggageStatus>) crit.setResultTransformer(crit.DISTINCT_ROOT_ENTITY).list();
	}
}
