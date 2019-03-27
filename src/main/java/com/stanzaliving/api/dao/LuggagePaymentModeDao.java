package com.stanzaliving.api.dao;

import java.util.List;

import com.stanzaliving.api.model.LuggagePaymentMode;

public interface LuggagePaymentModeDao {

	void save(LuggagePaymentMode luggagePaymentMode);

	LuggagePaymentMode findById(int id);

	List<LuggagePaymentMode> findAllLuggagePaymentModes();

	List<LuggagePaymentMode> findAllActiveLuggagePaymentModes();
}
