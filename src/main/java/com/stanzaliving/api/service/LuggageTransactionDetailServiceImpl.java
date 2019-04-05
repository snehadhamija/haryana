package com.stanzaliving.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stanzaliving.api.dao.LuggageTransactionDetailDao;
import com.stanzaliving.api.model.LuggageCategory;
import com.stanzaliving.api.model.LuggageTransaction;
import com.stanzaliving.api.model.LuggageTransactionDetail;

@Service("luggageTransactionDetailService")
@Transactional
public class LuggageTransactionDetailServiceImpl implements LuggageTransactionDetailService {

	@Autowired
	private LuggageTransactionDetailDao dao;

	@Autowired
	LuggageStatusService luggageStatusService;

	@Override
	public void save(LuggageTransactionDetail luggageTransactionDetail) {
		dao.save(luggageTransactionDetail);
	}

	@Override
	public LuggageTransactionDetail saveLuggageTransactionDetail(String luggageId, String weight,
			Integer luggageStatusId, LuggageCategory luggageCategory, LuggageTransaction luggageTransaction) {
		LuggageTransactionDetail luggageTransactionDetail = new LuggageTransactionDetail();
		luggageTransactionDetail.setLuggageCategory(luggageCategory);
		luggageTransactionDetail.setLuggageId(luggageId);
		luggageTransactionDetail.setLuggageTransaction(luggageTransaction);
		luggageTransactionDetail.setWeight(weight);
		dao.save(luggageTransactionDetail);
		return luggageTransactionDetail;
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
