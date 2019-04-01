package com.stanzaliving.api.service;

import java.util.List;

import com.stanzaliving.api.model.LuggageCharge;
import com.stanzaliving.api.model.LuggagePaymentMode;
import com.stanzaliving.api.model.LuggageTransaction;

public interface LuggageChargeService {

	void save(LuggageCharge luggageCharge);

	LuggageCharge saveLuggageCharge(String amount, LuggagePaymentMode luggagePaymentMode,
			LuggageTransaction luggageTransaction);

	LuggageCharge findById(int id);

	List<LuggageCharge> findAllLuggageCharges();

	LuggageCharge findLuggageChargeForLuggageTransaction(LuggageTransaction luggageTransaction);
}
