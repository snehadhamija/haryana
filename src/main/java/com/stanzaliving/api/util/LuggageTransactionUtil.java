package com.stanzaliving.api.util;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.stanzaliving.api.dto.LuggageTransactionStatusDto;
import com.stanzaliving.api.model.LuggageActivity;
import com.stanzaliving.api.model.LuggageLifecycle;
import com.stanzaliving.api.model.LuggageStoreRoom;
import com.stanzaliving.api.model.LuggageTransaction;
import com.stanzaliving.api.service.LuggageActivityService;
import com.stanzaliving.api.service.LuggageLifecycleService;
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

	@Autowired
	LuggageLifecycleService luggageLifecycleService;

	public Object areDuplicateLuggageIdsPresent(LuggageTransactionStatusDto luggageTransactionStatusDto) {
		for (HashMap<String, Object> entry : luggageTransactionStatusDto.getLuggageSummary()) {
			String luggageId = (String) entry.get("luggageId");
			List<LuggageLifecycle> luggageLifecycle = luggageLifecycleService
					.findAllLuggageLifecyclesForLuggageId(luggageId);
			if (!luggageLifecycle.isEmpty()) {
				return luggageId;
			}
		}
		return null;
	}

	public boolean isDepositActivity(LuggageTransactionStatusDto luggageTransactionStatusDto) {
		Integer luggageActivityId = luggageTransactionStatusDto.getLuggageActivityId();
		if (luggageActivityId == 1) {
			return true;
		}
		return false;
	}

	public Object checkExistanceOfLuggageIdInSystem(LuggageTransactionStatusDto luggageTransactionStatusDto) {
		if (isDepositActivity(luggageTransactionStatusDto)
				&& areDuplicateLuggageIdsPresent(luggageTransactionStatusDto) != null) {
			return areDuplicateLuggageIdsPresent(luggageTransactionStatusDto);
		}
		return null;
	}

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
