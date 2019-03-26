package com.stanzaliving.api.dao;

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
}
