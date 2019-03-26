package com.stanzaliving.api.dao;

import com.stanzaliving.api.model.LuggageCharge;

public interface LuggageChargeDao {

	void save(LuggageCharge luggageCharge);

	LuggageCharge findById(int id);
}
