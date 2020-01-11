package com.stanzaliving.api.dao;

import java.util.List;

import com.stanzaliving.api.model.Disease;

public interface DiseaseDao {

	void save(Disease disease);

	Disease findById(int id);

	List<Disease> findAllDiseases();
}
