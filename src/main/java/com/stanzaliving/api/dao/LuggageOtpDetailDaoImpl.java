package com.stanzaliving.api.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.stanzaliving.api.model.LuggageOtpDetail;

@Repository("luggageOtpDetailDao")
public class LuggageOtpDetailDaoImpl extends AbstractDao<Integer, LuggageOtpDetail> implements LuggageOtpDetailDao {

	@Override
	public void save(LuggageOtpDetail luggageOtpDetail) {
		persist(luggageOtpDetail);
	}

	@Override
	public void saveOrUpdateLuggageOtpDetail(LuggageOtpDetail luggageOtpDetail) {
		saveOrUpdate(luggageOtpDetail);
	}

	@Override
	public LuggageOtpDetail findById(int id) {
		return getByKey(id);
	}

	@Override
	public List<LuggageOtpDetail> findAllLuggageOtpDetails() {
		Criteria crit = createEntityCriteria();
		return (List<LuggageOtpDetail>) crit.setResultTransformer(crit.DISTINCT_ROOT_ENTITY).list();
	}

	@Override
	public LuggageOtpDetail checkIfOtpAlreadySent(String sentToUser, String sentByUser, Boolean isValidated) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("sentTo", sentToUser));
		crit.add(Restrictions.eq("sentBy", sentByUser));
		crit.add(Restrictions.eq("isValidated", false));
		List list = crit.list();
		if (!list.isEmpty()) {
			return (LuggageOtpDetail) list.get(0);
		}
		return null;
	}

	@Override
	public LuggageOtpDetail validateOtp(String sentTo, String otp, Integer luggageOtpDetailId) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("id", luggageOtpDetailId));
		crit.add(Restrictions.eq("sentTo", sentTo));
		crit.add(Restrictions.eq("otp", otp));
		List list = crit.list();
		if (!list.isEmpty()) {
			return (LuggageOtpDetail) list.get(0);
		}
		return null;
	}
}
