package com.stanzaliving.api.service;

import java.util.List;

import com.stanzaliving.api.model.Disease;

public interface DiseaseService {

	void save(Disease disease);

	Disease findById(int id);

	List<Disease> findAllDiseases();

	List<Disease> findAllActiveDiseases(Boolean isActive);

	List<Disease> findAllDiseasesForSubDiseases(Boolean isActive, List<Integer> subDiseaseIds);

	List<Disease> findAllDiseasesForProductCategories(Boolean isActive, List<Integer> productCategoryIds);
}
