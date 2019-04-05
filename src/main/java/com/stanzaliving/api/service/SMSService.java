package com.stanzaliving.api.service;

import com.stanzaliving.api.dto.LuggageTransactionStatusDto;

public interface SMSService {

	void sendMessage(String mobileNumber, LuggageTransactionStatusDto luggageTransactionStatusDto);

}
