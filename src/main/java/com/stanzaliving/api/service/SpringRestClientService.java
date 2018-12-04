package com.stanzaliving.api.service;

import javax.servlet.http.HttpServletRequest;

import com.stanzaliving.api.model.User;

public interface SpringRestClientService {

	User getUser(HttpServletRequest request);

}
