package com.stanzaliving.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stanzaliving.api.dao.LuggageOtpDetailDao;
import com.stanzaliving.api.model.LuggageOtpDetail;

@Service("luggageOtpDetailService")
@Transactional
public class LuggageOtpDetailServiceImpl implements LuggageOtpDetailService {

	@Autowired
	private LuggageOtpDetailDao dao;

	@Override
	public void save(LuggageOtpDetail luggageOtpDetail) {
		dao.save(luggageOtpDetail);
	}

	@Override
	public LuggageOtpDetail findById(int id) {
		return dao.findById(id);
	}
}
