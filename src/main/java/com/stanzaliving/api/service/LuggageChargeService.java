package com.stanzaliving.api.service;

import com.stanzaliving.api.model.LuggageCharge;

public interface LuggageChargeService {

	void save(LuggageCharge luggageCharge);

	LuggageCharge findById(int id);
}
