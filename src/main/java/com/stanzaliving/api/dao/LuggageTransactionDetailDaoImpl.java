package com.stanzaliving.api.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.stanzaliving.api.model.LuggageTransactionDetail;

@Repository("luggageTransactionDetailDao")
public class LuggageTransactionDetailDaoImpl extends AbstractDao<Integer, LuggageTransactionDetail>
		implements LuggageTransactionDetailDao {

	@Override
	public void save(LuggageTransactionDetail luggageTransactionDetail) {
		persist(luggageTransactionDetail);
	}

	@Override
	public LuggageTransactionDetail findById(int id) {
		return getByKey(id);
	}

	@Override
	public List<LuggageTransactionDetail> findAllLuggageTransactionDetails() {
		Criteria crit = createEntityCriteria();
		return (List<LuggageTransactionDetail>) crit.setResultTransformer(crit.DISTINCT_ROOT_ENTITY).list();
	}
}
