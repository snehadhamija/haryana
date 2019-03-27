package com.stanzaliving.api.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.stanzaliving.api.model.LuggageTransaction;

@Repository("luggageTransactionDao")
public class LuggageTransactionDaoImpl extends AbstractDao<Integer, LuggageTransaction>
		implements LuggageTransactionDao {

	@Override
	public void save(LuggageTransaction luggageTransaction) {
		persist(luggageTransaction);
	}

	@Override
	public LuggageTransaction findById(int id) {
		return getByKey(id);
	}

	@Override
	public List<LuggageTransaction> findAllLuggageTransactions() {
		Criteria crit = createEntityCriteria();
		return (List<LuggageTransaction>) crit.setResultTransformer(crit.DISTINCT_ROOT_ENTITY).list();
	}
}
