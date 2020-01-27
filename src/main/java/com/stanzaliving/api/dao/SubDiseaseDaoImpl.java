package com.stanzaliving.api.dao;

import java.util.List;
import java.util.Objects;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.stanzaliving.api.model.SubDisease;

@Repository("subDiseaseDao")
public class SubDiseaseDaoImpl extends AbstractDao<Integer, SubDisease> implements SubDiseaseDao {

	@Override
	public void save(SubDisease subDisease) {
		persist(subDisease);
	}

	@Override
	public SubDisease findById(int id) {
		return getByKey(id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SubDisease> findAllSubDiseases() {
		Criteria crit = createEntityCriteria();
		crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		crit.addOrder(Order.asc("sequenceId"));
		return (List<SubDisease>) crit.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SubDisease> findAllActiveSubDiseases(Boolean isActive) {
		Criteria crit = createEntityCriteria();
		if (Objects.nonNull(isActive)) {
			crit.add(Restrictions.eq("isActive", isActive));
		}
		crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		crit.addOrder(Order.asc("sequenceId"));
		return (List<SubDisease>) crit.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SubDisease> findAllSubDiseasesForDisease(Boolean isActive, Integer diseaseId) {
		Criteria crit = createEntityCriteria();
		if (Objects.nonNull(isActive)) {
			crit.add(Restrictions.eq("isActive", isActive));
		}
		if (Objects.nonNull(diseaseId)) {
			crit.add(Restrictions.eq("disease.diseaseId", diseaseId));
		}
		crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		crit.addOrder(Order.asc("sequenceId"));
		return (List<SubDisease>) crit.list();
	}
}
