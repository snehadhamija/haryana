package com.stanzaliving.api.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.stanzaliving.api.dto.UserDto;

public interface SpringRestClientService {

	Map<Object, Object> getUserMap(HttpServletRequest request);

	UserDto getUserDto(HttpServletRequest request);

	UserDto getUserDtoUsingDefaultHeaders(String mobileNumber);
}
