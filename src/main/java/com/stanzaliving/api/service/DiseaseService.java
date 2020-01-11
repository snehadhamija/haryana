package com.stanzaliving.api.service;

import java.util.List;

import com.stanzaliving.api.model.Disease;

public interface DiseaseService {

	void save(Disease disease);

	Disease findById(int id);

	List<Disease> findAllDiseases();
}
