package com.stanzaliving.api.dao;

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
}
