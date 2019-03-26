package com.stanzaliving.api.dao;

import org.springframework.stereotype.Repository;

import com.stanzaliving.api.model.LuggageOtpDetail;

@Repository("luggageOtpDetailDao")
public class LuggageOtpDetailDaoImpl extends AbstractDao<Integer, LuggageOtpDetail> implements LuggageOtpDetailDao {

	@Override
	public void save(LuggageOtpDetail luggageOtpDetail) {
		persist(luggageOtpDetail);
	}

	@Override
	public LuggageOtpDetail findById(int id) {
		return getByKey(id);
	}
}
