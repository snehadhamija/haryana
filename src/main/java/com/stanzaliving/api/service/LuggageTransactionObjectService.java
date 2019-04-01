package com.stanzaliving.api.service;

import javax.servlet.http.HttpServletRequest;

import com.stanzaliving.api.dto.LuggageTransactionStatusDto;

public interface LuggageTransactionObjectService {

	void saveOrUpdateLuggageTransactionStatusObject(LuggageTransactionStatusDto luggageTransactionStatusDto,
			HttpServletRequest httpRequest);
}
