package com.stanzaliving.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stanzaliving.api.dao.DiseaseDao;
import com.stanzaliving.api.model.Disease;

@Service("diseaseService")
@Transactional
public class DiseaseServiceImpl implements DiseaseService {

	@Autowired
	private DiseaseDao dao;

	@Override
	public void save(Disease disease) {
		dao.save(disease);
	}

	@Override
	public Disease findById(int id) {
		return dao.findById(id);
	}

	@Override
	public List<Disease> findAllDiseases() {
		return dao.findAllDiseases();
	}

	@Override
	public List<Disease> findAllActiveDiseases(Boolean isActive) {
		return dao.findAllActiveDiseases(isActive);
	}
}
