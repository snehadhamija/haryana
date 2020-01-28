package com.vcare.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vcare.api.dao.DiseaseDao;
import com.vcare.api.model.Disease;

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

	@Override
	public List<Disease> findAllDiseasesForSubDiseases(Boolean isActive, List<Integer> subDiseaseIds) {
		return dao.findAllDiseasesForSubDiseases(isActive, subDiseaseIds);
	}

	@Override
	public List<Disease> findAllDiseasesForProductCategories(Boolean isActive, List<Integer> productCategoryIds) {
		return dao.findAllDiseasesForProductCategories(isActive, productCategoryIds);
	}
}
