package com.stanzaliving.api.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.stanzaliving.api.dto.UserDto;
import com.stanzaliving.api.model.LuggageTransactionStatus;
import com.stanzaliving.api.service.SpringRestClientService;

@Component
public class LuggageTransactionStatusUtil {

	@Autowired
	SpringRestClientService springRestClientService;

	public List<HashMap<String, Object>> createHashMapListForStatuses(HttpServletRequest request,
			List<LuggageTransactionStatus> luggageTransactionStatuses) {
		List<HashMap<String, Object>> hashMaps = new ArrayList<>();
		luggageTransactionStatuses.stream().forEach(entry -> {
			HashMap<String, Object> hashMap = new HashMap<>();
			UserDto userDto = springRestClientService.getUserDtoForOtherUserById(request,
					entry.getLuggageTransaction().getUserId());
			hashMap.put("luggageTransactionStatusId", entry.getId());
			hashMap.put("status", entry.getLuggageActivityStatus().getStatusName());
			if (entry.getLuggageActivityStatus().getStatusName().equalsIgnoreCase("Deposit")) {
				hashMap.put("storageRoom", entry.getLuggageTransaction().getLuggageStoreRoom().getRoomName());
			}
			hashMap.put("expectedDate", entry.getLuggageTransaction().getExpectedDate());
			hashMap.put("userId", userDto.getUserId());
			hashMap.put("userName", userDto.getUserName());
			hashMap.put("studentCode", userDto.getUserCode());
			hashMap.put("roomNo", userDto.getRoom());
			hashMap.put("imgUrl", userDto.getImage());
			hashMaps.add(hashMap);
		});
		return hashMaps;
	}

}
