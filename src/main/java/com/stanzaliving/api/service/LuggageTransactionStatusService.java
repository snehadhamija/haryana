package com.stanzaliving.api.service;

import java.util.List;

import com.stanzaliving.api.model.LuggageTransactionStatus;

public interface LuggageTransactionStatusService {

	void save(LuggageTransactionStatus luggageTransactionStatus);

	LuggageTransactionStatus findById(int id);

	List<LuggageTransactionStatus> findAllLuggageTransactionStatuses();
}
