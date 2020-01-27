package com.stanzaliving.api.dao;

import java.util.List;
import java.util.Objects;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.stanzaliving.api.model.FAQ;

@Repository("fAQDao")
public class FAQDaoImpl extends AbstractDao<Integer, FAQ> implements FAQDao {

	@Override
	public void save(FAQ fAQ) {
		persist(fAQ);
	}

	@Override
	public FAQ findById(int id) {
		return getByKey(id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<FAQ> findAllFAQs() {
		Criteria crit = createEntityCriteria();
		crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		crit.addOrder(Order.asc("sequenceId"));
		return (List<FAQ>) crit.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<FAQ> findAllActiveFAQs(Boolean isActive) {
		Criteria crit = createEntityCriteria();
		if (Objects.nonNull(isActive)) {
			crit.add(Restrictions.eq("isActive", isActive));
		}
		crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		crit.addOrder(Order.asc("sequenceId"));
		return (List<FAQ>) crit.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<FAQ> findAllFAQsForSubDisease(Boolean isActive, Integer subDiseaseId) {
		Criteria crit = createEntityCriteria();
		if (Objects.nonNull(isActive)) {
			crit.add(Restrictions.eq("isActive", isActive));
		}
		if (Objects.nonNull(subDiseaseId)) {
			crit.createAlias("subDiseases", "subDiseases");
			crit.add(Restrictions.eq("subDiseases.subDiseaseId", subDiseaseId));
		}
		crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		crit.addOrder(Order.asc("sequenceId"));
		return (List<FAQ>) crit.list();
	}
}
