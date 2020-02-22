package com.haryanaindustries.api.service;

import java.util.List;

import com.haryanaindustries.api.model.User;

public interface UserService {

	void save(User user);

	User findById(int id);

	List<User> findAllUsers();
}
