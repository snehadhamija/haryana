package com.stanzaliving.api.service;

import java.util.Date;
import java.util.List;

import com.stanzaliving.api.model.LuggageOtpDetail;
import com.stanzaliving.api.model.LuggageTransactionDetail;

public interface LuggageOtpDetailService {

	void save(LuggageOtpDetail luggageOtpDetail);

	LuggageOtpDetail saveOrUpdateLuggageOtpDetail(LuggageOtpDetail luggageOtpDetail,
			List<LuggageTransactionDetail> luggageTransactionDetails);

	LuggageOtpDetail saveLuggageOtpDetail(String base64Otp, Date validTill, String sentTo, String sentBy);

	LuggageOtpDetail validateLuggageOtpDetail(LuggageOtpDetail luggageOtpDetail);

	LuggageOtpDetail updateLuggageOtpDetail(LuggageOtpDetail luggageOtpDetail, String base64Otp, Date validTill);

	LuggageOtpDetail findById(int id);

	List<LuggageOtpDetail> findAllLuggageOtpDetails();

	LuggageOtpDetail checkIfOtpAlreadySent(String sentToUser, String sentByUser, Boolean isValidated);

	boolean checkIfOtpExpired(Integer luggageOtpDetailId);

	LuggageOtpDetail validateOtp(String sentTo, String otp, Integer luggageOtpDetailId);
}
