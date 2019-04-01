package com.stanzaliving.api.util;

import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.stanzaliving.api.dto.LuggageTransactionStatusDto;
import com.stanzaliving.api.model.LuggageActivity;
import com.stanzaliving.api.model.LuggagePaymentMode;
import com.stanzaliving.api.model.LuggageStoreRoom;
import com.stanzaliving.api.model.LuggageTransaction;
import com.stanzaliving.api.service.LuggageActivityService;
import com.stanzaliving.api.service.LuggagePaymentModeService;
import com.stanzaliving.api.service.LuggageStoreRoomService;
import com.stanzaliving.api.service.LuggageTransactionService;

@Component
public class LuggageTransactionUtil {

	@Autowired
	LuggageStoreRoomService luggageStoreRoomService;

	@Autowired
	LuggageActivityService luggageActivityService;

	@Autowired
	LuggagePaymentModeService luggagePaymentModeService;

	@Autowired
	LuggageTransactionService luggageTransactionService;

	public LuggageTransaction saveLuggageTransactionObject(LuggageTransactionStatusDto luggageTransactionStatusDto) {
		Double totalWeightDouble = 0.00;
		for (HashMap<String, Object> entry : luggageTransactionStatusDto.getLuggageSummary()) {
			String weight = (String) entry.get("weight");
			Double weightDouble = Double.valueOf(weight);
			totalWeightDouble = totalWeightDouble + weightDouble;
		}
		String totalWeight = totalWeightDouble.toString();
		// save entries in transaction table
		Integer totalBoxes = luggageTransactionStatusDto.getTotalBoxes();
		Integer luggageStorageRoomId = luggageTransactionStatusDto.getLuggageStorageRoomId();
		LuggageStoreRoom luggageStoreRoom = luggageStoreRoomService.findById(luggageStorageRoomId);
		Integer luggageActivityId = luggageTransactionStatusDto.getLuggageActivityId();
		LuggageActivity luggageActivity = luggageActivityService.findById(luggageActivityId);
		Date expectedDate = luggageTransactionStatusDto.getExpectedDate();
		LuggageTransaction luggageTransaction = luggageTransactionService.saveLuggageTransaction(luggageActivity,
				totalBoxes, totalWeight, expectedDate, luggageStoreRoom);
		return luggageTransaction;
	}
}
