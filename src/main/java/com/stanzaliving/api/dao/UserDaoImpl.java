package com.stanzaliving.api.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.stanzaliving.api.model.User;

@Repository("userDao")
public class UserDaoImpl extends AbstractDao<Integer, User> implements UserDao {

	/*
	 * (non-Javadoc)
	 * @see com.stanzaliving.api.dao.UserDao#save(com.stanzaliving.api.model.User)
	 */
	@Override
	public void save(User user) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * @see com.stanzaliving.api.dao.UserDao#findById(int)
	 */
	@Override
	public User findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see com.stanzaliving.api.dao.UserDao#findAllUsers()
	 */
	@Override
	public List<User> findAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}
}
