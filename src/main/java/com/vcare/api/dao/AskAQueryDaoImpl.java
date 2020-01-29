package com.vcare.api.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.amazonaws.util.StringUtils;
import com.vcare.api.model.AskAQuery;

@Repository("askAQueryDao")
public class AskAQueryDaoImpl extends AbstractDao<Integer, AskAQuery> implements AskAQueryDao {

	@Override
	public void save(AskAQuery askAQuery) {
		persist(askAQuery);
	}

	@Override
	public AskAQuery findById(int id) {
		return getByKey(id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AskAQuery> findQueriesForMobileAndEmail(String mobileNumber, String email) {
		Criteria crit = createEntityCriteria();
		crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		crit.setFetchMode("faq", FetchMode.JOIN);
		if (!StringUtils.isNullOrEmpty(mobileNumber)) {
			crit.add(Restrictions.eq("mobileNumber", mobileNumber));
		}
		if (!StringUtils.isNullOrEmpty(email)) {
			crit.add(Restrictions.eq("email", email));
		}
		return (List<AskAQuery>) crit.list();
	}
}
