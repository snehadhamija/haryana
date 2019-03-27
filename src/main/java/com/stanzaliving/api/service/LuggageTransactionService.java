package com.stanzaliving.api.service;

import java.util.List;

import com.stanzaliving.api.model.LuggageTransaction;

public interface LuggageTransactionService {

	void save(LuggageTransaction luggageTransaction);

	LuggageTransaction findById(int id);

	List<LuggageTransaction> findAllLuggageTransactions();
}
