package com.stanzaliving.api.dao;

import java.util.Date;
import java.util.List;

import com.stanzaliving.api.model.LuggageTransaction;

public interface LuggageTransactionDao {

	void save(LuggageTransaction luggageTransaction);

	LuggageTransaction findById(int id);

	List<LuggageTransaction> findAllLuggageTransactions();

	List<LuggageTransaction> findAllLuggageTransactionsForDate(Date expectedDate);
}
