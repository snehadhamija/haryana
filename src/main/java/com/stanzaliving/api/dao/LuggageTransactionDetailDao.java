package com.stanzaliving.api.dao;

import java.util.List;

import com.stanzaliving.api.model.LuggageTransaction;
import com.stanzaliving.api.model.LuggageTransactionDetail;

public interface LuggageTransactionDetailDao {

	void save(LuggageTransactionDetail luggageTransactionDetail);

	LuggageTransactionDetail findById(int id);

	List<LuggageTransactionDetail> findAllLuggageTransactionDetails();

	List<LuggageTransactionDetail> findAllLuggageTransactionDetailsForTransaction(
			LuggageTransaction luggageTransaction);
}
