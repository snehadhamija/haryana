package com.stanzaliving.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stanzaliving.api.dao.LuggagePaymentModeDao;
import com.stanzaliving.api.model.LuggagePaymentMode;

@Service("luggagePaymentModeService")
@Transactional
public class LuggagePaymentModeServiceImpl implements LuggagePaymentModeService {

	@Autowired
	private LuggagePaymentModeDao dao;

	@Override
	public void save(LuggagePaymentMode luggagePaymentMode) {
		dao.save(luggagePaymentMode);
	}

	@Override
	public LuggagePaymentMode findById(int id) {
		return dao.findById(id);
	}

	@Override
	public List<LuggagePaymentMode> findAllLuggagePaymentModes() {
		return dao.findAllLuggagePaymentModes();
	}

	@Override
	public List<LuggagePaymentMode> findAllActiveLuggagePaymentModes() {
		return dao.findAllActiveLuggagePaymentModes();
	}
}
