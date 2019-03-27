package com.stanzaliving.api.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.stanzaliving.api.model.LuggageTransactionStatus;

@Repository("luggageTransactionStatusDao")
public class LuggageTransactionStatusDaoImpl extends AbstractDao<Integer, LuggageTransactionStatus>
		implements LuggageTransactionStatusDao {

	@Override
	public void save(LuggageTransactionStatus luggageTransactionStatus) {
		persist(luggageTransactionStatus);
	}

	@Override
	public LuggageTransactionStatus findById(int id) {
		return getByKey(id);
	}

	@Override
	public List<LuggageTransactionStatus> findAllLuggageTransactionStatuses() {
		Criteria crit = createEntityCriteria();
		return (List<LuggageTransactionStatus>) crit.setResultTransformer(crit.DISTINCT_ROOT_ENTITY).list();
	}
}