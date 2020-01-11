package com.stanzaliving.api.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import com.stanzaliving.api.model.Disease;

@Repository("diseaseDao")
public class DiseaseDaoImpl extends AbstractDao<Integer, Disease> implements DiseaseDao {

	@Override
	public void save(Disease disease) {
		persist(disease);
	}

	@Override
	public Disease findById(int id) {
		return getByKey(id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Disease> findAllDiseases() {
		Criteria crit = createEntityCriteria();
		crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		crit.addOrder(Order.asc("sequenceId"));
		return (List<Disease>) crit.list();
	}
}
