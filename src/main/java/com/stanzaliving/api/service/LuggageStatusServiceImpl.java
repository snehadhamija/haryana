package com.stanzaliving.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stanzaliving.api.dao.LuggageStatusDao;
import com.stanzaliving.api.model.LuggageStatus;

@Service("luggageStatusService")
@Transactional
public class LuggageStatusServiceImpl implements LuggageStatusService {

	@Autowired
	private LuggageStatusDao dao;

	@Override
	public void save(LuggageStatus luggageStatus) {
		dao.save(luggageStatus);
	}

	@Override
	public LuggageStatus findById(int id) {
		return dao.findById(id);
	}

	@Override
	public List<LuggageStatus> findAllLuggageStatuses() {
		return dao.findAllLuggageStatuses();
	}

}
