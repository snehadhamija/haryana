package com.stanzaliving.api.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.stanzaliving.api.model.LuggagePaymentMode;

@Repository("luggagePaymentModeDao")
public class LuggagePaymentModeDaoImpl extends AbstractDao<Integer, LuggagePaymentMode>
		implements LuggagePaymentModeDao {

	@Override
	public void save(LuggagePaymentMode luggagePaymentMode) {
		persist(luggagePaymentMode);
	}

	@Override
	public LuggagePaymentMode findById(int id) {
		return getByKey(id);
	}

	@Override
	public List<LuggagePaymentMode> findAllLuggagePaymentModes() {
		Criteria crit = createEntityCriteria();
		return (List<LuggagePaymentMode>) crit.setResultTransformer(crit.DISTINCT_ROOT_ENTITY).list();
	}

	@Override
	public List<LuggagePaymentMode> findAllActiveLuggagePaymentModes() {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("isActive", true));
		return (List<LuggagePaymentMode>) crit.setResultTransformer(crit.DISTINCT_ROOT_ENTITY).list();
	}
}
