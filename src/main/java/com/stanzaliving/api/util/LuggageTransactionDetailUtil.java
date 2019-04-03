package com.stanzaliving.api.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.stanzaliving.api.dto.LuggageTransactionStatusDto;
import com.stanzaliving.api.model.LuggageCategory;
import com.stanzaliving.api.model.LuggageImage;
import com.stanzaliving.api.model.LuggageStatus;
import com.stanzaliving.api.model.LuggageTransaction;
import com.stanzaliving.api.model.LuggageTransactionDetail;
import com.stanzaliving.api.service.LuggageCategoryService;
import com.stanzaliving.api.service.LuggageImageService;
import com.stanzaliving.api.service.LuggageLifecycleService;
import com.stanzaliving.api.service.LuggageStatusService;
import com.stanzaliving.api.service.LuggageTransactionDetailService;

@Component
public class LuggageTransactionDetailUtil {

	@Autowired
	LuggageCategoryService luggageCategoryService;

	@Autowired
	LuggageTransactionDetailService luggageTransactionDetailService;

	@Autowired
	LuggageImageService luggageImageService;

	@Autowired
	LuggageLifecycleService luggageLifecycleService;

	@Autowired
	LuggageStatusService luggageStatusService;

	public List<LuggageTransactionDetail> saveLuggageTransactionDetailObject(
			LuggageTransactionStatusDto luggageTransactionStatusDto, LuggageTransaction luggageTransaction) {
		Integer activityId = luggageTransaction.getLuggageActivity().getId();
		List<LuggageTransactionDetail> luggageTransactionDetails = new ArrayList<>();
		for (HashMap<String, Object> entry : luggageTransactionStatusDto.getLuggageSummary()) {
			String weight = (String) entry.get("weight");
			String luggageId = (String) entry.get("luggageId");
			Integer luggageCategoryId = (Integer) entry.get("luggageCategory");
			LuggageCategory luggageCategory = luggageCategoryService.findById(luggageCategoryId);
			Integer luggageStatusId = getLuggageStatusId(activityId, entry);
			LuggageTransactionDetail luggageTransactionDetail = luggageTransactionDetailService
					.saveLuggageTransactionDetail(luggageId, weight, luggageStatusId, luggageCategory,
							luggageTransaction);
			luggageTransactionDetails.add(luggageTransactionDetail);
			LuggageStatus luggageStatus = luggageStatusService.findById(luggageStatusId);
			luggageLifecycleService.saveLuggageLifecycle(luggageStatus, luggageTransactionDetail);
			List<String> luggageImages = (List<String>) entry.get("luggageImages");
			luggageImages.forEach(imageUrl -> {
				LuggageImage luggageImage = luggageImageService.saveLuggageImage(luggageTransactionDetail, imageUrl);
			});
		}
		return luggageTransactionDetails;
	}

	public Integer getLuggageStatusId(Integer activityId, HashMap<String, Object> entry) {
		Integer luggageStatusId = 0;
		if (activityId == 1) {
			luggageStatusId = 1;
		} else {
			if (entry.containsKey("isMissing")) {
				luggageStatusId = 2;
			} else {
				luggageStatusId = 4;
			}
		}
		return luggageStatusId;
	}
}
