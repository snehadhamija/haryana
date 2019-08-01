package com.stanzaliving.api.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.stanzaliving.api.model.LuggageHostel;

@Repository("luggageHostelDao")
public class LuggageHostelDaoImpl extends AbstractDao<Integer, LuggageHostel> implements LuggageHostelDao {

	@Override
	public void save(LuggageHostel luggageHostel) {
		persist(luggageHostel);
	}

	@Override
	public LuggageHostel findById(int id) {
		return getByKey(id);
	}

	@Override
	public List<LuggageHostel> findAllLuggageHostels() {
		Criteria crit = createEntityCriteria();
		return (List<LuggageHostel>) crit.setResultTransformer(crit.DISTINCT_ROOT_ENTITY).list();
	}

	@Override
	public boolean findIfLuggageModuleActivatedForCurrentHostel(Integer hostelId) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("hostelId", hostelId));
		crit.add(Restrictions.eq("isActivated", true));
		List<LuggageHostel> results = crit.setResultTransformer(crit.DISTINCT_ROOT_ENTITY).list();
		if (!results.isEmpty()) {
			return true;
		}
		return false;
	}

	@Override
	public LuggageHostel findByHostelId (int hostelId) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("hostelId", hostelId));
		LuggageHostel luggageHostel = (LuggageHostel) criteria.uniqueResult();
		return luggageHostel;
	}
}
