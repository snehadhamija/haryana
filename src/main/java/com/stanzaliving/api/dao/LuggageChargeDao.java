package com.stanzaliving.api.dao;

import java.util.List;

import com.stanzaliving.api.model.LuggageCharge;

public interface LuggageChargeDao {

	void save(LuggageCharge luggageCharge);

	LuggageCharge findById(int id);

	List<LuggageCharge> findAllLuggageCharges();
}
