package com.stanzaliving.api.service;

import com.stanzaliving.api.model.LuggageTransaction;

public interface LuggageTransactionService {

	void save(LuggageTransaction luggageTransaction);

	LuggageTransaction findById(int id);
}
