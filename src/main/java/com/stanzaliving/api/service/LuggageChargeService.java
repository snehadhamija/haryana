package com.stanzaliving.api.service;

import java.util.List;

import com.stanzaliving.api.model.LuggageCharge;

public interface LuggageChargeService {

	void save(LuggageCharge luggageCharge);

	LuggageCharge findById(int id);

	List<LuggageCharge> findAllLuggageCharges();
}
