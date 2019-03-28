package com.stanzaliving.api.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.stanzaliving.api.model.LuggageCharge;
import com.stanzaliving.api.model.LuggageTransaction;

@Repository("luggageChargeDao")
public class LuggageChargeDaoImpl extends AbstractDao<Integer, LuggageCharge> implements LuggageChargeDao {

	@Override
	public void save(LuggageCharge luggageCharge) {
		persist(luggageCharge);
	}

	@Override
	public LuggageCharge findById(int id) {
		return getByKey(id);
	}

	@Override
	public List<LuggageCharge> findAllLuggageCharges() {
		Criteria crit = createEntityCriteria();
		return (List<LuggageCharge>) crit.setResultTransformer(crit.DISTINCT_ROOT_ENTITY).list();
	}

	@Override
	public LuggageCharge findLuggageChargeForLuggageTransaction(LuggageTransaction luggageTransaction) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("luggageTransaction", luggageTransaction));
		List<LuggageCharge> luggageCharges = crit.setResultTransformer(crit.DISTINCT_ROOT_ENTITY).list();
		if (!luggageCharges.isEmpty()) {
			return luggageCharges.get(luggageCharges.size() - 1);
		}
		return null;
	}
}
