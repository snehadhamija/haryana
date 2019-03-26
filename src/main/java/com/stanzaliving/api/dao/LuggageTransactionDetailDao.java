package com.stanzaliving.api.dao;

import com.stanzaliving.api.model.LuggageTransactionDetail;

public interface LuggageTransactionDetailDao {

	void save(LuggageTransactionDetail luggageTransactionDetail);

	LuggageTransactionDetail findById(int id);
}
