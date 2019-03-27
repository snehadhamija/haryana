package com.stanzaliving.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stanzaliving.api.dao.LuggageTransactionStatusDao;
import com.stanzaliving.api.model.LuggageTransactionStatus;

@Service("luggageTransactionStatusService")
@Transactional
public class LuggageTransactionStatusServiceImpl implements LuggageTransactionStatusService {

	@Autowired
	private LuggageTransactionStatusDao dao;

	@Override
	public void save(LuggageTransactionStatus luggageTransactionStatus) {
		dao.save(luggageTransactionStatus);
	}

	@Override
	public LuggageTransactionStatus findById(int id) {
		return dao.findById(id);
	}

	@Override
	public List<LuggageTransactionStatus> findAllLuggageTransactionStatuses() {
		return dao.findAllLuggageTransactionStatuses();
	}
}
