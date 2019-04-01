package com.stanzaliving.api.util;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.stanzaliving.api.dto.LuggageTransactionStatusDto;
import com.stanzaliving.api.model.LuggageCategory;
import com.stanzaliving.api.model.LuggageImage;
import com.stanzaliving.api.model.LuggageTransaction;
import com.stanzaliving.api.model.LuggageTransactionDetail;
import com.stanzaliving.api.service.LuggageCategoryService;
import com.stanzaliving.api.service.LuggageImageService;
import com.stanzaliving.api.service.LuggageTransactionDetailService;

@Component
public class LuggageTransactionDetailUtil {

	@Autowired
	LuggageCategoryService luggageCategoryService;

	@Autowired
	LuggageTransactionDetailService luggageTransactionDetailService;

	@Autowired
	LuggageImageService luggageImageService;

	public void saveLuggageTransactionDetailObject(LuggageTransactionStatusDto luggageTransactionStatusDto,
			LuggageTransaction luggageTransaction) {
		Integer activityId = luggageTransaction.getLuggageActivity().getId();
		for (HashMap<String, Object> entry : luggageTransactionStatusDto.getLuggageSummary()) {
			String weight = (String) entry.get("weight");
			String luggageId = (String) entry.get("luggageId");
			Integer luggageCategoryId = (Integer) entry.get("luggageCategory");
			LuggageCategory luggageCategory = luggageCategoryService.findById(luggageCategoryId);
			Integer luggageStatusId = getLuggageStatusId(activityId, entry);
			LuggageTransactionDetail luggageTransactionDetail = luggageTransactionDetailService
					.saveLuggageTransactionDetail(luggageId, weight, luggageStatusId, luggageCategory,
							luggageTransaction);
			List<String> luggageImages = (List<String>) entry.get("luggageImages");
			luggageImages.forEach(imageUrl -> {
				LuggageImage luggageImage = luggageImageService.saveLuggageImage(luggageTransactionDetail, imageUrl);
			});
		}
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
