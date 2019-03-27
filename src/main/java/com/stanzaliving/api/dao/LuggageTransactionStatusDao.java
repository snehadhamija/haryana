package com.stanzaliving.api.dao;

import java.util.Date;
import java.util.List;

import com.stanzaliving.api.model.LuggageTransactionStatus;

public interface LuggageTransactionStatusDao {

	void save(LuggageTransactionStatus luggageTransactionStatus);

	LuggageTransactionStatus findById(int id);

	List<LuggageTransactionStatus> findAllLuggageTransactionStatuses();

	List<LuggageTransactionStatus> findAllLuggageTransactionStatusesForDate(Date expectedDate);
}
