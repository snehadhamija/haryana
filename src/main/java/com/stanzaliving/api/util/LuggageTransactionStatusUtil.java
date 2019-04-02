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

	public Object createHashMapForStatus(HttpServletRequest request,
			LuggageTransactionStatus luggageTransactionStatus) {
		UserDto userDto = springRestClientService.getUserDtoForOtherUser(request,
				luggageTransactionStatus.getUserMobile());
		List<HashMap<String, Object>> statusHashMaps = new ArrayList<>();
		for (LuggageTransaction luggageTransaction : luggageTransactionStatus.getLuggageTransactions()) {
			HashMap<String, Object> statusHashMap = new HashMap<>();
			statusHashMap.put("luggageActivity", luggageTransaction.getLuggageActivity().getActivityName());
			statusHashMap.put("expectedDate", luggageTransaction.getExpectedDate());
			statusHashMap.put("totalBoxes", luggageTransaction.getNumberOfBags());
			LuggageCharge luggageCharge = luggageChargeService
					.findLuggageChargeForLuggageTransaction(luggageTransaction);
			if (luggageCharge != null && luggageCharge.getCharge() != null && luggageCharge.getCharge() != "") {
				statusHashMap.put("amount", luggageCharge.getCharge());
			}
			statusHashMap.put("luggageStorageRoom", luggageTransaction.getLuggageStoreRoom().getId());
			statusHashMap.put("user", createUserHashMap(userDto));
			statusHashMap.put("luggageSummary", createLuggageSummaryHashMap(luggageTransaction));
			statusHashMaps.add(statusHashMap);
		}
		return statusHashMaps;
	}

	public Object createHashMapListForStatuses(HttpServletRequest request,
			List<LuggageTransactionStatus> luggageTransactionStatuses) {
		UserDto currentUserDto = springRestClientService.getUserDto(request);
		Integer currentUserHostel = currentUserDto.getHostelID();
		List<HashMap<String, Object>> listHashMaps = new ArrayList<>();
		luggageTransactionStatuses.stream().forEach(entry -> {
			UserDto userDto = springRestClientService.getUserDtoForOtherUser(request, entry.getUserMobile());
			Integer userHostel = userDto.getHostelID();
			if (userHostel == currentUserHostel) {
				for (LuggageTransaction luggageTransaction : entry.getLuggageTransactions()) {
					HashMap<String, Object> hashMap = new HashMap<>();
					hashMap.put("luggageTransactionStatusId", entry.getId());
					hashMap.put("status", entry.getLuggageActivityStatus().getStatusName());
					if (entry.getLuggageActivityStatus().getStatusName().equalsIgnoreCase("Deposit")) {
						hashMap.put("luggageStorageRoom", luggageTransaction.getLuggageStoreRoom().getRoomName());
					}
					hashMap.put("expectedDate", luggageTransaction.getExpectedDate());
					hashMap.put("user", createUserHashMap(userDto));
					listHashMaps.add(hashMap);
					break;
				}
			}
		});
		return listHashMaps;
	}

	public HashMap<String, Object> createUserHashMap(UserDto userDto) {
		HashMap<String, Object> userHashMap = new HashMap<>();
		userHashMap.put("id", userDto.getUserId());
		userHashMap.put("userName", userDto.getUserName());
		userHashMap.put("studentCode", userDto.getUserCode());
		userHashMap.put("roomNo", userDto.getRoom());
		userHashMap.put("imgUrl", userDto.getImage());
		userHashMap.put("mobileNo", userDto.getMobileNo());
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
