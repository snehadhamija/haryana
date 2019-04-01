package com.stanzaliving.api.service;

import java.util.Date;
import java.util.List;

import com.stanzaliving.api.model.LuggageActivity;
import com.stanzaliving.api.model.LuggageStoreRoom;
import com.stanzaliving.api.model.LuggageTransaction;

public interface LuggageTransactionService {

	void save(LuggageTransaction luggageTransaction);

	LuggageTransaction saveLuggageTransaction(LuggageActivity luggageActivity, String userMobile, Integer totalBoxes,
			String totalWeight, Date expectedDate, LuggageStoreRoom luggageStoreRoom);

	LuggageTransaction findById(int id);

	List<LuggageTransaction> findAllLuggageTransactions();

	List<LuggageTransaction> findAllLuggageTransactionsForDate(Date expectedDate);
}
