package com.stanzaliving.api.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.stanzaliving.api.model.LuggageOtpDetail;

@Repository("luggageOtpDetailDao")
public class LuggageOtpDetailDaoImpl extends AbstractDao<Integer, LuggageOtpDetail> implements LuggageOtpDetailDao {

	@Override
	public void save(LuggageOtpDetail luggageOtpDetail) {
		persist(luggageOtpDetail);
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
}
