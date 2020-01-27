package com.stanzaliving.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stanzaliving.api.dao.SubDiseaseDao;
import com.stanzaliving.api.model.SubDisease;

@Service("subDiseaseService")
@Transactional
public class SubDiseaseServiceImpl implements SubDiseaseService {

	@Autowired
	private SubDiseaseDao dao;

	@Override
	public void save(SubDisease subDisease) {
		dao.save(subDisease);
	}

	@Override
	public SubDisease findById(int id) {
		return dao.findById(id);
	}

	@Override
	public List<SubDisease> findAllSubDiseases() {
		return dao.findAllSubDiseases();
	}

	@Override
	public List<SubDisease> findAllActiveSubDiseases(Boolean isActive) {
		return dao.findAllActiveSubDiseases(isActive);
	}

	@Override
	public List<SubDisease> findAllSubDiseasesForDisease(Boolean isActive, Integer diseaseId) {
		return dao.findAllSubDiseasesForDisease(isActive, diseaseId);
	}
}
