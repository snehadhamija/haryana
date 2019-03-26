package com.stanzaliving.api.dao;

import com.stanzaliving.api.model.LuggageTransactionStatus;

public interface LuggageTransactionStatusDao {

	void save(LuggageTransactionStatus luggageTransactionStatus);

	LuggageTransactionStatus findById(int id);
}
