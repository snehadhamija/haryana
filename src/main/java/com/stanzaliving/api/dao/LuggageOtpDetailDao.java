package com.stanzaliving.api.dao;

import com.stanzaliving.api.model.LuggageOtpDetail;

public interface LuggageOtpDetailDao {

	void save(LuggageOtpDetail luggageOtpDetail);

	LuggageOtpDetail findById(int id);
}
