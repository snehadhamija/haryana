package com.stanzaliving.api.service;

import javax.servlet.http.HttpServletRequest;

import com.stanzaliving.api.dto.LuggageTransactionStatusDto;

public interface LuggageTransactionObjectService {

	Object checkExistanceOfLuggageIdInSystem(LuggageTransactionStatusDto luggageTransactionStatusDto);

	void saveOrUpdateLuggageTransactionStatusObject(LuggageTransactionStatusDto luggageTransactionStatusDto,
			HttpServletRequest httpRequest);

	void sendLuggageTransactionStatusEmail(LuggageTransactionStatusDto luggageTransactionStatusDto,
			HttpServletRequest httpRequest);
}
