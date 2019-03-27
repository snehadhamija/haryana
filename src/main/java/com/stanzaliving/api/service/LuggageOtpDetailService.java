package com.stanzaliving.api.service;

import java.util.List;

import com.stanzaliving.api.model.LuggageOtpDetail;

public interface LuggageOtpDetailService {

	void save(LuggageOtpDetail luggageOtpDetail);

	LuggageOtpDetail findById(int id);

	List<LuggageOtpDetail> findAllLuggageOtpDetails();
}
