package com.stanzaliving.api.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.stanzaliving.api.model.LuggageImage;
import com.stanzaliving.api.model.LuggageTransactionDetail;

@Repository("luggageImageDao")
public class LuggageImageDaoImpl extends AbstractDao<Integer, LuggageImage> implements LuggageImageDao {

	@Override
	public void save(LuggageImage luggageImage) {
		persist(luggageImage);
	}

	@Override
	public LuggageImage findById(int id) {
		return getByKey(id);
	}

	@Override
	public List<LuggageImage> findAllLuggageImages() {
		Criteria crit = createEntityCriteria();
		return (List<LuggageImage>) crit.setResultTransformer(crit.DISTINCT_ROOT_ENTITY).list();
	}

	@Override
	public List<LuggageImage> findLuggageImageForLuggageTransactionDetail(
			LuggageTransactionDetail luggageTransactionDetail) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("luggageTransactionDetail", luggageTransactionDetail));
		List<LuggageImage> luggageImages = crit.setResultTransformer(crit.DISTINCT_ROOT_ENTITY).list();
		return luggageImages;
	}
}
