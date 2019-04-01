package com.stanzaliving.api.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stanzaliving.api.dao.LuggageTransactionDao;
import com.stanzaliving.api.model.LuggageActivity;
import com.stanzaliving.api.model.LuggageStoreRoom;
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
	public LuggageTransaction saveLuggageTransaction(LuggageActivity luggageActivity, Integer totalBoxes,
			String totalWeight, Date expectedDate, LuggageStoreRoom luggageStoreRoom) {
		LuggageTransaction luggageTransaction = new LuggageTransaction();
		luggageTransaction.setExpectedDate(expectedDate);
		luggageTransaction.setLuggageActivity(luggageActivity);
		luggageTransaction.setLuggageStoreRoom(luggageStoreRoom);
		luggageTransaction.setNumberOfBags(totalBoxes);
		luggageTransaction.setTotalWeight(totalWeight);
		dao.save(luggageTransaction);
		return luggageTransaction;
	}

	@Override
	public LuggageTransaction findById(int id) {
		return dao.findById(id);
	}

	@Override
	public List<LuggageTransaction> findAllLuggageTransactions() {
		return dao.findAllLuggageTransactions();
	}

	@Override
	public List<LuggageTransaction> findAllLuggageTransactionsForDate(Date expectedDate) {
		return dao.findAllLuggageTransactionsForDate(expectedDate);
	}
}
