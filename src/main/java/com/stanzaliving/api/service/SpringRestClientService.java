package com.stanzaliving.api.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.stanzaliving.api.dto.UserDto;
import com.stanzaliving.api.model.User;

public interface SpringRestClientService {

	User getUser(HttpServletRequest request);

	Map<Object, Object> getUserMap(HttpServletRequest request);

	UserDto getUserDto(HttpServletRequest request);

}
