package com.stanzaliving.api.service;

import java.util.Date;
import java.util.List;

import com.stanzaliving.api.model.LuggageTransactionStatus;

public interface LuggageTransactionStatusService {

	void save(LuggageTransactionStatus luggageTransactionStatus);

	LuggageTransactionStatus findById(int id);

	List<LuggageTransactionStatus> findAllLuggageTransactionStatuses();

	List<LuggageTransactionStatus> findAllLuggageTransactionStatusesForDate(Date expectedDate);
}
