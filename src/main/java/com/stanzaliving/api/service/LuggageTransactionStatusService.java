package com.stanzaliving.api.service;

import java.util.Date;
import java.util.List;
import java.util.Set;

import com.stanzaliving.api.dto.UserDto;
import com.stanzaliving.api.model.LuggageActivityStatus;
import com.stanzaliving.api.model.LuggageTransaction;
import com.stanzaliving.api.model.LuggageTransactionStatus;

public interface LuggageTransactionStatusService {

	void save(LuggageTransactionStatus luggageTransactionStatus);

	void saveOrUpdateLuggageTransactionStatus(LuggageTransactionStatus luggageTransactionStatus);

	LuggageTransactionStatus saveLuggageTransactionStatus(UserDto userDto, LuggageActivityStatus luggageActivityStatus,
			LuggageTransaction luggageTransaction);

	LuggageTransactionStatus updateLuggageTransactionStatus(LuggageTransactionStatus luggageTransactionStatus,
			LuggageActivityStatus luggageActivityStatus, Set<LuggageTransaction> existingLuggageTransactions,
			LuggageTransaction luggageTransaction);

	LuggageTransactionStatus findById(int id);

	List<LuggageTransactionStatus> findAllLuggageTransactionStatuses();

	List<LuggageTransactionStatus> findAllLuggageTransactionStatusesForDate(Date expectedDate);

}
