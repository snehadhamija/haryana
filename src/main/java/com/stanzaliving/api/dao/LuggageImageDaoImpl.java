package com.stanzaliving.api.dao;

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
}
