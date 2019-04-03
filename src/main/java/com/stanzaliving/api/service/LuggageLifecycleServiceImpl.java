package com.stanzaliving.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stanzaliving.api.dao.LuggageLifecycleDao;
import com.stanzaliving.api.model.LuggageLifecycle;
import com.stanzaliving.api.model.LuggageStatus;
import com.stanzaliving.api.model.LuggageTransactionDetail;

@Service("luggageLifecycleService")
@Transactional
public class LuggageLifecycleServiceImpl implements LuggageLifecycleService {

	@Autowired
	private LuggageLifecycleDao dao;

	@Override
	public void save(LuggageLifecycle luggageLifecycle) {
		dao.save(luggageLifecycle);
	}

	@Override
	public LuggageLifecycle saveLuggageLifecycle(LuggageStatus luggageStatus,
			LuggageTransactionDetail luggageTransactionDetail) {
		LuggageLifecycle luggageLifecycle = new LuggageLifecycle();
		luggageLifecycle.setLuggageStatus(luggageStatus);
		luggageLifecycle.setLuggageTransactionDetail(luggageTransactionDetail);
		dao.save(luggageLifecycle);
		return luggageLifecycle;
	}

	@Override
	public LuggageLifecycle findById(int id) {
		return dao.findById(id);
	}

	@Override
	public List<LuggageLifecycle> findAllLuggageLifecycles() {
		return dao.findAllLuggageLifecycles();
	}

	@Override
	public List<LuggageLifecycle> findAllLuggageLifecyclesForLuggageId(String luggageId) {
		return dao.findAllLuggageLifecyclesForLuggageId(luggageId);
	}

	@Override
	public LuggageLifecycle findLuggageLifecycleForLuggageTransactionDetail(
			LuggageTransactionDetail luggageTransactionDetail) {
		return dao.findLuggageLifecycleForLuggageTransactionDetail(luggageTransactionDetail);
	}
}
