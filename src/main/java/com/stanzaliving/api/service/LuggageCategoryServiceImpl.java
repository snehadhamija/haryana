package com.stanzaliving.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stanzaliving.api.dao.LuggageCategoryDao;
import com.stanzaliving.api.model.LuggageCategory;

@Service("luggageCategoryService")
@Transactional
public class LuggageCategoryServiceImpl implements LuggageCategoryService {

	@Autowired
	private LuggageCategoryDao dao;

	@Override
	public void save(LuggageCategory luggageCategory) {
		dao.save(luggageCategory);
	}

	@Override
	public LuggageCategory findById(int id) {
		return dao.findById(id);
	}

	@Override
	public List<LuggageCategory> findAllLuggageCategories() {
		return dao.findAllLuggageCategories();
	}
}
