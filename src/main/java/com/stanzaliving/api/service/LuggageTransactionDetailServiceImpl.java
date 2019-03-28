package com.stanzaliving.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stanzaliving.api.dao.LuggageTransactionDetailDao;
import com.stanzaliving.api.model.LuggageTransaction;
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

	@Override
	public List<LuggageTransactionDetail> findAllLuggageTransactionDetails() {
		return dao.findAllLuggageTransactionDetails();
	}

	@Override
	public List<LuggageTransactionDetail> findAllLuggageTransactionDetailsForTransaction(
			LuggageTransaction luggageTransaction) {
		return dao.findAllLuggageTransactionDetailsForTransaction(luggageTransaction);
	}
}
