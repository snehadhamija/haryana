package com.stanzaliving.api.dao;

import java.util.List;

import com.stanzaliving.api.model.LuggageOtpDetail;

public interface LuggageOtpDetailDao {

	void save(LuggageOtpDetail luggageOtpDetail);

	LuggageOtpDetail findById(int id);

	List<LuggageOtpDetail> findAllLuggageOtpDetails();
}
