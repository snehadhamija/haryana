package com.stanzaliving.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stanzaliving.api.dao.LuggageImageDao;
import com.stanzaliving.api.model.LuggageImage;

@Service("luggageImageService")
@Transactional
public class LuggageImageServiceImpl implements LuggageImageService {

	@Autowired
	private LuggageImageDao dao;

	@Override
	public void save(LuggageImage luggageImage) {
		dao.save(luggageImage);
	}

	@Override
	public LuggageImage findById(int id) {
		return dao.findById(id);
	}
}
