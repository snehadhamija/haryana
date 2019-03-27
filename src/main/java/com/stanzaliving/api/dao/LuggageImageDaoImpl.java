package com.stanzaliving.api.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.stanzaliving.api.model.LuggageImage;

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
}
