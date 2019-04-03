package com.stanzaliving.api.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.stanzaliving.api.model.LuggageLifecycle;
import com.stanzaliving.api.model.LuggageTransactionDetail;

@Repository("luggageLifecycleDao")
public class LuggageLifecycleDaoImpl extends AbstractDao<Integer, LuggageLifecycle> implements LuggageLifecycleDao {

	@Override
	public void save(LuggageLifecycle luggageLifecycle) {
		persist(luggageLifecycle);
	}

	@Override
	public LuggageLifecycle findById(int id) {
		return getByKey(id);
	}

	@Override
	public List<LuggageLifecycle> findAllLuggageLifecycles() {
		Criteria crit = createEntityCriteria();
		return (List<LuggageLifecycle>) crit.setResultTransformer(crit.DISTINCT_ROOT_ENTITY).list();
	}

	@Override
	public List<LuggageLifecycle> findAllLuggageLifecyclesForLuggageId(String luggageId) {
		Criteria crit = createEntityCriteria();
		crit.createAlias("luggageTransactionDetail", "ltd");
		crit.add(Restrictions.eq("ltd.luggageId", luggageId));
		return (List<LuggageLifecycle>) crit.setResultTransformer(crit.DISTINCT_ROOT_ENTITY).list();
	}

	@Override
	public LuggageLifecycle findLuggageLifecycleForLuggageTransactionDetail(
			LuggageTransactionDetail luggageTransactionDetail) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("luggageTransactionDetail", luggageTransactionDetail));
		List<LuggageLifecycle> luggageLifecycles = (List<LuggageLifecycle>) crit
				.setResultTransformer(crit.DISTINCT_ROOT_ENTITY).list();
		if (!luggageLifecycles.isEmpty()) {
			return luggageLifecycles.get(0);
		}
		return null;
	}
}
