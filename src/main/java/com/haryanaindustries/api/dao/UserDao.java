package com.haryanaindustries.api.dao;

import java.util.List;

import com.haryanaindustries.api.model.User;

public interface UserDao {

	void save(User user);

	User findById(int id);

	List<User> findAllUsers();
}
