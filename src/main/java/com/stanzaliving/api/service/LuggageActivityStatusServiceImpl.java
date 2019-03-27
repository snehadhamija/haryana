package com.stanzaliving.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stanzaliving.api.dao.LuggageActivityStatusDao;
import com.stanzaliving.api.model.LuggageActivityStatus;

@Service("luggageActivityStatusService")
@Transactional
public class LuggageActivityStatusServiceImpl implements LuggageActivityStatusService {

	@Autowired
	private LuggageActivityStatusDao dao;

	@Override
	public void save(LuggageActivityStatus luggageActivityStatus) {
		dao.save(luggageActivityStatus);
	}

	@Override
	public LuggageActivityStatus findById(int id) {
		return dao.findById(id);
	}

	@Override
	public List<LuggageActivityStatus> findAllLuggageActivityStatuses() {
		return dao.findAllLuggageActivityStatuses();
	}
}
