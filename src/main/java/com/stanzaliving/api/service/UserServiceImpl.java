//package com.stanzaliving.api.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.stanzaliving.api.dao.UserDao;
//import com.stanzaliving.api.model.User;
//
//@Service("userService")
//@Transactional
//public class UserServiceImpl implements UserService {
//
//	@Autowired
//	private UserDao dao;
//
//	public void save(User user) {
//		dao.save(user);
//	}
//
//	public User findByMobileNumber(String mobileNumber) {
//		User user = dao.findByMobileNumber(mobileNumber);
//		return user;
//	}
//
//}
