package com.stanzaliving.api.dao;

import java.util.List;

import com.stanzaliving.api.model.LuggageOtpDetail;

public interface LuggageOtpDetailDao {

	void save(LuggageOtpDetail luggageOtpDetail);

	void saveOrUpdateLuggageOtpDetail(LuggageOtpDetail luggageOtpDetail);

	LuggageOtpDetail findById(int id);

	List<LuggageOtpDetail> findAllLuggageOtpDetails();

	LuggageOtpDetail checkIfOtpAlreadySent(String sentToUserId, String sentByUserId, Boolean isValidated);

	LuggageOtpDetail validateOtp(String sentTo, String otp, Integer luggageOtpDetailId);
}
