package com.stanzaliving.api.service;

import java.util.List;

import com.stanzaliving.api.model.LuggageLifecycle;
import com.stanzaliving.api.model.LuggageStatus;
import com.stanzaliving.api.model.LuggageTransactionDetail;

public interface LuggageLifecycleService {

	void save(LuggageLifecycle luggageLifecycle);

	LuggageLifecycle saveLuggageLifecycle(LuggageStatus luggageStatus,
			LuggageTransactionDetail luggageTransactionDetail);

	LuggageLifecycle findById(int id);

	List<LuggageLifecycle> findAllLuggageLifecycles();

	List<LuggageLifecycle> findAllLuggageLifecyclesForLuggageId(String luggageId);

	LuggageLifecycle findLuggageLifecycleForLuggageTransactionDetail(LuggageTransactionDetail luggageTransactionDetail);
}
