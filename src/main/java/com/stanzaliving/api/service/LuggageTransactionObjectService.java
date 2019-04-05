package com.stanzaliving.api.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.stanzaliving.api.dto.LuggageTransactionStatusDto;
import com.stanzaliving.api.model.LuggageTransactionDetail;

public interface LuggageTransactionObjectService {

	Object checkExistanceOfLuggageIdInSystem(LuggageTransactionStatusDto luggageTransactionStatusDto);

	List<LuggageTransactionDetail> saveOrUpdateLuggageTransactionStatusObject(
			LuggageTransactionStatusDto luggageTransactionStatusDto, HttpServletRequest httpRequest);

	void sendLuggageTransactionStatusEmailAndSms(LuggageTransactionStatusDto luggageTransactionStatusDto,
			HttpServletRequest httpRequest);

	boolean shouldCreateComplaint(List<LuggageTransactionDetail> luggageTransactionDetails);

	void createComplaintForMissingItems(LuggageTransactionStatusDto luggageTransactionStatusDto,
			List<LuggageTransactionDetail> luggageTransactionDetails);
}
