package com.vcare.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vcare.api.dao.UserDao;
import com.vcare.api.model.User;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao dao;

	@Override
	public void save(User user) {
		dao.save(user);
	}

	@Override
	public User findById(int id) {
		return dao.findById(id);
	}

	@Override
	public List<User> findAllUsers() {
		return dao.findAllUsers();
	}
}
