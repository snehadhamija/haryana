package com.stanzaliving.api.dao;

import java.util.List;

import com.stanzaliving.api.model.LuggageLifecycle;
import com.stanzaliving.api.model.LuggageTransactionDetail;

public interface LuggageLifecycleDao {

	void save(LuggageLifecycle luggageLifecycle);

	LuggageLifecycle findById(int id);

	List<LuggageLifecycle> findAllLuggageLifecycles();

	List<LuggageLifecycle> findAllLuggageLifecyclesForLuggageId(String luggageId);

	LuggageLifecycle findLuggageLifecycleForLuggageTransactionDetail(LuggageTransactionDetail luggageTransactionDetail);
}
