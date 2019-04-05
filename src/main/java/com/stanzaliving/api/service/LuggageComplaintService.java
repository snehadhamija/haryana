package com.stanzaliving.api.service;

import java.util.HashMap;
import java.util.List;

import com.stanzaliving.api.dto.LuggageTransactionStatusDto;
import com.stanzaliving.api.model.LuggageTransactionDetail;

public interface LuggageComplaintService {

	HashMap<String, Object> createComplaintObject(LuggageTransactionStatusDto luggageTransactionStatusDto,
			List<LuggageTransactionDetail> luggageTransactionDetails);
}
