package com.stanzaliving.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stanzaliving.api.dao.LuggageHostelDao;
import com.stanzaliving.api.model.LuggageHostel;

@Service("luggageHostelService")
@Transactional
public class LuggageHostelServiceImpl implements LuggageHostelService {

	@Autowired
	private LuggageHostelDao dao;

	@Override
	public void save(LuggageHostel luggageHostel) {
		dao.save(luggageHostel);
	}

	@Override
	public LuggageHostel findById(int id) {
		return dao.findById(id);
	}

	@Override
	public List<LuggageHostel> findAllLuggageHostels() {
		return dao.findAllLuggageHostels();
	}

	@Override
	public boolean findIfLuggageModuleActivatedForCurrentHostel(Integer hostelId) {
		return dao.findIfLuggageModuleActivatedForCurrentHostel(hostelId);
	}
}