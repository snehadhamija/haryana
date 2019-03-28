package com.stanzaliving.api.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.stanzaliving.api.dto.UserDto;
import com.stanzaliving.api.model.LuggageCharge;
import com.stanzaliving.api.model.LuggageTransaction;
import com.stanzaliving.api.model.LuggageTransactionDetail;
import com.stanzaliving.api.model.LuggageTransactionStatus;
import com.stanzaliving.api.service.LuggageChargeService;
import com.stanzaliving.api.service.LuggageImageService;
import com.stanzaliving.api.service.LuggageTransactionDetailService;
import com.stanzaliving.api.service.SpringRestClientService;

@Component
public class LuggageTransactionStatusUtil {

	@Autowired
	SpringRestClientService springRestClientService;

	@Autowired
	LuggageChargeService luggageChargeService;

	@Autowired
	LuggageTransactionDetailService luggageTransactionDetailService;

	@Autowired
	LuggageImageService luggageImageService;

	public HashMap<String, Object> createHashMapForStatus(HttpServletRequest request,
			LuggageTransactionStatus luggageTransactionStatus) {
		HashMap<String, Object> statusHashMap = new HashMap<>();
		UserDto userDto = springRestClientService.getUserDtoForOtherUser(request,
				luggageTransactionStatus.getLuggageTransaction().getUserMobile());
		statusHashMap.put("expectedDate", luggageTransactionStatus.getLuggageTransaction().getExpectedDate());
		statusHashMap.put("totalBoxes", luggageTransactionStatus.getLuggageTransaction().getNumberOfBags());
		LuggageCharge luggageCharge = luggageChargeService
				.findLuggageChargeForLuggageTransaction(luggageTransactionStatus.getLuggageTransaction());
		if (luggageCharge != null && luggageCharge.getCharge() != null && luggageCharge.getCharge() != "") {
			statusHashMap.put("amountPaid", luggageCharge.getCharge());
		}
		statusHashMap.put("user", createUserHashMap(userDto));
		statusHashMap.put("luggageSummary",
				createLuggageSummaryHashMap(luggageTransactionStatus.getLuggageTransaction()));
		return statusHashMap;
	}

	public List<HashMap<String, Object>> createHashMapListForStatuses(HttpServletRequest request,
			List<LuggageTransactionStatus> luggageTransactionStatuses) {
		List<HashMap<String, Object>> hashMaps = new ArrayList<>();
		luggageTransactionStatuses.stream().forEach(entry -> {
			HashMap<String, Object> hashMap = new HashMap<>();
			UserDto userDto = springRestClientService.getUserDtoForOtherUser(request,
					entry.getLuggageTransaction().getUserMobile());
			hashMap.put("luggageTransactionStatusId", entry.getId());
			hashMap.put("status", entry.getLuggageActivityStatus().getStatusName());
			if (entry.getLuggageActivityStatus().getStatusName().equalsIgnoreCase("Deposit")) {
				hashMap.put("storageRoom", entry.getLuggageTransaction().getLuggageStoreRoom().getRoomName());
			}
			hashMap.put("expectedDate", entry.getLuggageTransaction().getExpectedDate());
			hashMap.put("user", createUserHashMap(userDto));
			hashMaps.add(hashMap);
		});
		return hashMaps;
	}

	public HashMap<String, Object> createUserHashMap(UserDto userDto) {
		HashMap<String, Object> userHashMap = new HashMap<>();
		userHashMap.put("id", userDto.getUserId());
		userHashMap.put("userName", userDto.getUserName());
		userHashMap.put("studentCode", userDto.getUserCode());
		userHashMap.put("roomNo", userDto.getRoom());
		userHashMap.put("imgUrl", userDto.getImage());
		return userHashMap;
	}

	public List<HashMap<String, Object>> createLuggageSummaryHashMap(LuggageTransaction luggageTransaction) {
		List<HashMap<String, Object>> luggageSummarHashMaps = new ArrayList<>();
		List<LuggageTransactionDetail> luggageTransactionDetails = luggageTransactionDetailService
				.findAllLuggageTransactionDetailsForTransaction(luggageTransaction);
		luggageTransactionDetails.forEach(luggageTransactionDetail -> {
			HashMap<String, Object> luggageSummaryHashMap = new HashMap<>();
			List<Object> luggageImages = luggageImageService
					.findLuggageImageForLuggageTransactionDetail(luggageTransactionDetail);
			luggageSummaryHashMap.put("luggageId", luggageTransactionDetail.getLuggageId());
			luggageSummaryHashMap.put("luggageCategory",
					luggageTransactionDetail.getLuggageCategory().getCategoryName());
			luggageSummaryHashMap.put("weight", luggageTransactionDetail.getWeight());
			luggageSummaryHashMap.put("luggageImages", luggageImages);
			luggageSummarHashMaps.add(luggageSummaryHashMap);
		});
		return luggageSummarHashMaps;
	}
}
