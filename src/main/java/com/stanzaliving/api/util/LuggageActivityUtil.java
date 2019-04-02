package com.stanzaliving.api.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.stanzaliving.api.dto.UserDto;
import com.stanzaliving.api.service.LuggageHostelService;
import com.stanzaliving.api.service.SpringRestClientService;

@Component
public class LuggageActivityUtil {

	@Autowired
	SpringRestClientService springRestClientService;

	@Autowired
	LuggageHostelService luggageHostelService;

	public boolean checkIfLuggageActivatedForHostel(HttpServletRequest httpRequest) {
		UserDto userDto = springRestClientService.getUserDto(httpRequest);
		Integer hostelId = userDto.getHostelID();
		boolean isHostelActivated = luggageHostelService.findIfLuggageModuleActivatedForCurrentHostel(hostelId);
		if (!isHostelActivated) {
			return false;
		}
		return true;
	}
}
