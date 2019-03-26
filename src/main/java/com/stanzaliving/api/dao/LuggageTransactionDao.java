package com.stanzaliving.api.dao;

import com.stanzaliving.api.model.LuggageTransaction;

public interface LuggageTransactionDao {

	void save(LuggageTransaction luggageTransaction);

	LuggageTransaction findById(int id);
}
