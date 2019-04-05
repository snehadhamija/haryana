package com.stanzaliving.api.util;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.stanzaliving.api.dto.LuggageTransactionStatusDto;
import com.stanzaliving.api.model.LuggageTransactionDetail;
import com.stanzaliving.api.service.SpringRestClientService;

@Component
public class LuggageTransactionStatusComplaintUtil {

	@Autowired
	SpringRestClientService springRestClientService;

	public void createComplaintForMissingItems(LuggageTransactionStatusDto luggageTransactionStatusDto,
			List<LuggageTransactionDetail> luggageTransactionDetails) {
		springRestClientService.createComplaintForMissingItems(luggageTransactionStatusDto, luggageTransactionDetails);
	}
}
