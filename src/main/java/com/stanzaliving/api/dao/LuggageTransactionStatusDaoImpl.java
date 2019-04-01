package com.stanzaliving.api.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.stanzaliving.api.model.LuggageTransactionStatus;

@Repository("luggageTransactionStatusDao")
public class LuggageTransactionStatusDaoImpl extends AbstractDao<Integer, LuggageTransactionStatus>
		implements LuggageTransactionStatusDao {

	@Override
	public void save(LuggageTransactionStatus luggageTransactionStatus) {
		persist(luggageTransactionStatus);
	}

	@Override
	public void saveOrUpdateLuggageTransactionStatus(LuggageTransactionStatus luggageTransactionStatus) {
		saveOrUpdate(luggageTransactionStatus);
	}

	@Override
	public LuggageTransactionStatus findById(int id) {
		return getByKey(id);
	}

	@Override
	public List<LuggageTransactionStatus> findAllLuggageTransactionStatuses() {
		Criteria crit = createEntityCriteria();
		return (List<LuggageTransactionStatus>) crit.setResultTransformer(crit.DISTINCT_ROOT_ENTITY).list();
	}

	@Override
	public List<LuggageTransactionStatus> findAllLuggageTransactionStatusesForDate(Date expectedDate) {
		Criteria crit = createEntityCriteria();
		crit.createAlias("luggageTransaction", "lt");
		crit.add(Restrictions.eq("lt.expectedDate", expectedDate));
		return (List<LuggageTransactionStatus>) crit.setResultTransformer(crit.DISTINCT_ROOT_ENTITY).list();
	}
}
