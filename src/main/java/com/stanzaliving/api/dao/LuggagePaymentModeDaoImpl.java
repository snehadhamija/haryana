package com.stanzaliving.api.dao;

import org.springframework.stereotype.Repository;

import com.stanzaliving.api.model.LuggagePaymentMode;

@Repository("luggagePaymentModeDao")
public class LuggagePaymentModeDaoImpl extends AbstractDao<Integer, LuggagePaymentMode>
		implements LuggagePaymentModeDao {

	@Override
	public void save(LuggagePaymentMode luggagePaymentMode) {
		persist(luggagePaymentMode);
	}

	@Override
	public LuggagePaymentMode findById(int id) {
		return getByKey(id);
	}
}
