package com.stanzaliving.api.service;

import java.util.List;

import com.stanzaliving.api.model.LuggageTransactionDetail;

public interface LuggageTransactionDetailService {

	void save(LuggageTransactionDetail luggageTransactionDetail);

	LuggageTransactionDetail findById(int id);

	List<LuggageTransactionDetail> findAllLuggageTransactionDetails();
}
