package com.vcare.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vcare.api.dao.AskAQueryDao;
import com.vcare.api.model.AskAQuery;

@Service("askAQueryService")
@Transactional
public class AskAQueryServiceImpl implements AskAQueryService {

	@Autowired
	private AskAQueryDao dao;

	@Override
	public void save(AskAQuery askAQuery) {
		dao.save(askAQuery);
	}

	@Override
	public AskAQuery findById(int id) {
		return dao.findById(id);
	}

	@Override
	public List<AskAQuery> findQueriesForMobileAndEmail(String mobileNumber, String email) {
		return dao.findQueriesForMobileAndEmail(mobileNumber, email);
	}
}
