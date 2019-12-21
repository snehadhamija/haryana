package com.stanzaliving.api.service;

import java.util.List;

import com.stanzaliving.api.model.User;

public interface UserService {

	void save(User user);

	User findById(int id);

	List<User> findAllUsers();
}
