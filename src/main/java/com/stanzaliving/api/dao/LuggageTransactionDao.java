package com.stanzaliving.api.dao;

import java.util.List;

import com.stanzaliving.api.model.LuggageTransaction;

public interface LuggageTransactionDao {

	void save(LuggageTransaction luggageTransaction);

	LuggageTransaction findById(int id);

	List<LuggageTransaction> findAllLuggageTransactions();
}
