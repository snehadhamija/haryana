package com.stanzaliving.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stanzaliving.api.dao.LuggageActivityDao;
import com.stanzaliving.api.model.LuggageActivity;

@Service("luggageActivityService")
@Transactional
public class LuggageActivityServiceImpl implements LuggageActivityService {

	@Autowired
	private LuggageActivityDao dao;

	@Override
	public void save(LuggageActivity luggageActivity) {
		dao.save(luggageActivity);
	}

	@Override
	public LuggageActivity findById(int id) {
		return dao.findById(id);
	}
}
