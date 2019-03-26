package com.stanzaliving.api.dao;

import org.springframework.stereotype.Repository;

import com.stanzaliving.api.model.LuggageCharge;

@Repository("luggageChargeDao")
public class LuggageChargeDaoImpl extends AbstractDao<Integer, LuggageCharge> implements LuggageChargeDao {

	@Override
	public void save(LuggageCharge luggageCharge) {
		persist(luggageCharge);
	}

	@Override
	public LuggageCharge findById(int id) {
		return getByKey(id);
	}
}
