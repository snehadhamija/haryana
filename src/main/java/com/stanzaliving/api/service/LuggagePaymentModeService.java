package com.stanzaliving.api.service;

import com.stanzaliving.api.model.LuggagePaymentMode;

public interface LuggagePaymentModeService {

	void save(LuggagePaymentMode luggagePaymentMode);

	LuggagePaymentMode findById(int id);
}
