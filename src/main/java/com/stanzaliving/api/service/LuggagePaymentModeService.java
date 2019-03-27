package com.stanzaliving.api.service;

import java.util.List;

import com.stanzaliving.api.model.LuggagePaymentMode;

public interface LuggagePaymentModeService {

	void save(LuggagePaymentMode luggagePaymentMode);

	LuggagePaymentMode findById(int id);

	List<LuggagePaymentMode> findAllLuggagePaymentModes();

	List<LuggagePaymentMode> findAllActiveLuggagePaymentModes();
}
