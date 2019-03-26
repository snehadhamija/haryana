package com.stanzaliving.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stanzaliving.api.dao.LuggageTransactionDao;
import com.stanzaliving.api.model.LuggageTransaction;

@Service("luggageTransactionService")
@Transactional
public class LuggageTransactionServiceImpl implements LuggageTransactionService {

	@Autowired
	private LuggageTransactionDao dao;

	@Override
	public void save(LuggageTransaction luggageTransaction) {
		dao.save(luggageTransaction);
	}

	@Override
	public LuggageTransaction findById(int id) {
		return dao.findById(id);
	}
}
