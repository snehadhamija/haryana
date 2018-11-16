package com.stanzaliving.api.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.stanzaliving.api.model.User;

@Repository("userDao")
public class UserDaoImpl extends AbstractDao<Integer, User> implements UserDao {

	public void save(User user) {
		persist(user);
	}

	public User findByMobileNumber(String mobileNumber) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("mobileNumber", mobileNumber));
		User user = (User) crit.uniqueResult();
		return user;
	}
}
