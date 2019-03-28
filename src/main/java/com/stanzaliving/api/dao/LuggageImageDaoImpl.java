package com.stanzaliving.api.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
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
	public List<Object> findLuggageImageForLuggageTransactionDetail(LuggageTransactionDetail luggageTransactionDetail) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("luggageTransactionDetail", luggageTransactionDetail));
		ProjectionList projList = Projections.projectionList();
		projList.add(Projections.property("imageUrl"));
		crit.setProjection(projList);
		List<Object> luggageImages = crit.setResultTransformer(crit.DISTINCT_ROOT_ENTITY).list();
		return luggageImages;
	}
}
