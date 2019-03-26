package com.stanzaliving.api.service;

import com.stanzaliving.api.model.LuggageTransactionDetail;

public interface LuggageTransactionDetailService {

	void save(LuggageTransactionDetail luggageTransactionDetail);

	LuggageTransactionDetail findById(int id);
}
