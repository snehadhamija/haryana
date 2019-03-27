package com.stanzaliving.api.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.stanzaliving.api.dto.UserDto;

public interface SpringRestClientService {

	Map<Object, Object> getUserMap(HttpServletRequest request);

	UserDto getUserDto(HttpServletRequest request);

	UserDto getUserDtoForOtherUser(HttpServletRequest request, String mobileNumber);

	UserDto getUserDtoUsingDefaultHeaders(String mobileNumber);

	String postCampaignDataUsingDefaultHeaders(Integer complaintId, String rating);

	List<HashMap<String, Object>> getAllUserDto(HttpServletRequest request);

	UserDto getUserDtoForOtherUserById(HttpServletRequest request, Integer userId);
}
