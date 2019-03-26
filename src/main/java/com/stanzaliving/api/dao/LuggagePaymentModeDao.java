package com.stanzaliving.api.dao;

import com.stanzaliving.api.model.LuggagePaymentMode;

public interface LuggagePaymentModeDao {

	void save(LuggagePaymentMode luggagePaymentMode);

	LuggagePaymentMode findById(int id);
}
