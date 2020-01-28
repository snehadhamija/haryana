package com.vcare.api.service;

import java.util.List;

import com.vcare.api.model.User;

public interface UserService {

	void save(User user);

	User findById(int id);

	List<User> findAllUsers();
}
