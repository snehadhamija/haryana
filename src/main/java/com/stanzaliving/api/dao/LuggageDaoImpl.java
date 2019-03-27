package com.stanzaliving.api.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.stanzaliving.api.model.Luggage;

@Repository("luggageDao")
public class LuggageDaoImpl extends AbstractDao<Integer, Luggage> implements LuggageDao {

	@Override
	public void save(Luggage luggage) {
		persist(luggage);
	}

	@Override
	public Luggage findById(int id) {
		return getByKey(id);
	}

	@Override
	public List<Luggage> findAllLuggages() {
		Criteria crit = createEntityCriteria();
		return (List<Luggage>) crit.setResultTransformer(crit.DISTINCT_ROOT_ENTITY).list();
	}
}
