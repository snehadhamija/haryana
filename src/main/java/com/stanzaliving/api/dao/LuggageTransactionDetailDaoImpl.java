package com.stanzaliving.api.dao;

import org.springframework.stereotype.Repository;

import com.stanzaliving.api.model.LuggageTransactionDetail;

@Repository("luggageTransactionDetailDao")
public class LuggageTransactionDetailDaoImpl extends AbstractDao<Integer, LuggageTransactionDetail>
		implements LuggageTransactionDetailDao {

	@Override
	public void save(LuggageTransactionDetail luggageTransactionDetail) {
		persist(luggageTransactionDetail);
	}

	@Override
	public LuggageTransactionDetail findById(int id) {
		return getByKey(id);
	}
}
