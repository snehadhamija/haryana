package com.stanzaliving.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stanzaliving.api.dao.LuggageDao;
import com.stanzaliving.api.model.Luggage;

@Service("luggageService")
@Transactional
public class LuggageServiceImpl implements LuggageService {

	@Autowired
	private LuggageDao dao;

	@Override
	public void save(Luggage luggage) {
		dao.save(luggage);
	}

	@Override
	public Luggage findById(int id) {
		return dao.findById(id);
	}

	@Override
	public List<Luggage> findAllLuggages() {
		return dao.findAllLuggages();
	}
}
