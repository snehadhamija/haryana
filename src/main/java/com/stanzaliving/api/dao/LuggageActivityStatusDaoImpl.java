package com.stanzaliving.api.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.stanzaliving.api.model.LuggageActivityStatus;

@Repository("luggageActivityStatusDao")
public class LuggageActivityStatusDaoImpl extends AbstractDao<Integer, LuggageActivityStatus>
		implements LuggageActivityStatusDao {

	@Override
	public void save(LuggageActivityStatus luggageActivityStatus) {
		persist(luggageActivityStatus);
	}

	@Override
	public LuggageActivityStatus findById(int id) {
		return getByKey(id);
	}

	@Override
	public List<LuggageActivityStatus> findAllLuggageActivityStatuses() {
		Criteria crit = createEntityCriteria();
		return (List<LuggageActivityStatus>) crit.setResultTransformer(crit.DISTINCT_ROOT_ENTITY).list();
	}
}
