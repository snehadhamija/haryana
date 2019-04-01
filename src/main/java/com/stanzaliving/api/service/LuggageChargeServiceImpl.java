package com.stanzaliving.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stanzaliving.api.dao.LuggageChargeDao;
import com.stanzaliving.api.model.LuggageCharge;
import com.stanzaliving.api.model.LuggagePaymentMode;
import com.stanzaliving.api.model.LuggageTransaction;

@Service("luggageChargeService")
@Transactional
public class LuggageChargeServiceImpl implements LuggageChargeService {

	@Autowired
	private LuggageChargeDao dao;

	@Override
	public void save(LuggageCharge luggageCharge) {
		dao.save(luggageCharge);
	}

	@Override
	public LuggageCharge saveLuggageCharge(String amount, LuggagePaymentMode luggagePaymentMode,
			LuggageTransaction luggageTransaction) {
		LuggageCharge luggageCharge = new LuggageCharge();
		luggageCharge.setCharge(amount);
		luggageCharge.setIsPaid(true);
		luggageCharge.setLuggagePaymentMode(luggagePaymentMode);
		luggageCharge.setLuggageTransaction(luggageTransaction);
		dao.save(luggageCharge);
		return luggageCharge;
	}

	@Override
	public LuggageCharge findById(int id) {
		return dao.findById(id);
	}

	@Override
	public List<LuggageCharge> findAllLuggageCharges() {
		return dao.findAllLuggageCharges();
	}

	@Override
	public LuggageCharge findLuggageChargeForLuggageTransaction(LuggageTransaction luggageTransaction) {
		return dao.findLuggageChargeForLuggageTransaction(luggageTransaction);
	}
}
