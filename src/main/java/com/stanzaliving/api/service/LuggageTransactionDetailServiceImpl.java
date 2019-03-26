package com.stanzaliving.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stanzaliving.api.dao.LuggageTransactionDetailDao;
import com.stanzaliving.api.model.LuggageTransactionDetail;

@Service("luggageTransactionDetailService")
@Transactional
public class LuggageTransactionDetailServiceImpl implements LuggageTransactionDetailService {

	@Autowired
	private LuggageTransactionDetailDao dao;

	@Override
	public void save(LuggageTransactionDetail luggageTransactionDetail) {
		dao.save(luggageTransactionDetail);
	}

	@Override
	public LuggageTransactionDetail findById(int id) {
		return dao.findById(id);
	}
}
