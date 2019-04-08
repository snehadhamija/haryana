package com.stanzaliving.api.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stanzaliving.api.dto.LuggageTransactionStatusDto;
import com.stanzaliving.api.model.LuggageLifecycle;
import com.stanzaliving.api.model.LuggageTransactionDetail;

@Service("luggageComplaintService")
@Transactional
public class LuggageComplaintServiceImpl implements LuggageComplaintService {

	@Autowired
	LuggageLifecycleService luggageLifecycleService;

	@Override
	public HashMap<String, Object> createComplaintObject(LuggageTransactionStatusDto luggageTransactionStatusDto,
			List<LuggageTransactionDetail> luggageTransactionDetails) {
		HashMap<String, Object> map = new HashMap<>();
		HashMap<String, Object> categoryMap = new HashMap<>();
		HashMap<String, Object> subCategoryMap = new HashMap<>();
		HashMap<String, Object> userHashMap = new HashMap<>();
		userHashMap.put("mobileNumber", luggageTransactionStatusDto.getUser().get("mobileNo"));
		map.put("user", userHashMap);
		categoryMap.put("id", 12);
		map.put("complainType", categoryMap);
		subCategoryMap.put("id", 51);
		map.put("complainTypeCategory", subCategoryMap);
		List<Object> list = new ArrayList<>();
		map.put("complaintTypeSubCategories", list);
		HashMap<String, Object> complaintTypeSubCategories = new HashMap<>();
		complaintTypeSubCategories.put("id", 107);
		complaintTypeSubCategories.put("name", "Missing Items");
		list.add(complaintTypeSubCategories);
		map.put("description", createLuggageComplaintDescription(luggageTransactionDetails));
		map.put("status", "0");
		map.put("complaintImages", getMissingLuggageImages(luggageTransactionStatusDto));
		return map;
	}

	public String createLuggageComplaintDescription(List<LuggageTransactionDetail> luggageTransactionDetails) {
		String description = null;
		String luggageIdsCommaSperated = getMissingLuggageIds(luggageTransactionDetails).toString().replace("[", "")
				.replace("]", "").replace(", ", ",");
		description = "LuggageIds missing: " + luggageIdsCommaSperated;
		return description;
	}

	public List<String> getMissingLuggageIds(List<LuggageTransactionDetail> luggageTransactionDetails) {
		List<String> missingLugaggeIds = new ArrayList<>();
		for (LuggageTransactionDetail luggageTransactionDetail : luggageTransactionDetails) {
			LuggageLifecycle luggageLifecycle = luggageLifecycleService
					.findLuggageLifecycleForLuggageTransactionDetail(luggageTransactionDetail);
			if (luggageLifecycle.getLuggageStatus().getId() == 2) {
				missingLugaggeIds.add(luggageTransactionDetail.getLuggageId());
			}
		}
		return missingLugaggeIds;
	}

	public List<HashMap<String, Object>> getMissingLuggageImages(
			LuggageTransactionStatusDto luggageTransactionStatusDto) {
		List<HashMap<String, Object>> imageHashMapList = new ArrayList<>();
		for (HashMap<String, Object> luggageSummary : luggageTransactionStatusDto.getLuggageSummary()) {
			if (luggageSummary.containsKey("isMissing") && luggageSummary.containsKey("luggageImages")) {
				List<String> images = (List<String>) luggageSummary.get("luggageImages");
				if (!images.isEmpty()) {
					images.forEach(i -> {
						HashMap<String, Object> imageHashMap = new HashMap<>();
						imageHashMap.put("imageURL", i);
						imageHashMapList.add(imageHashMap);
					});
				}
			}
		}
		return imageHashMapList;
	}
}
