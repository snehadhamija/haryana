package com.stanzaliving.api.dao;

import java.util.List;

import com.stanzaliving.api.model.SubDisease;

public interface SubDiseaseDao {

	void save(SubDisease subDisease);

	SubDisease findById(int id);

	List<SubDisease> findAllSubDiseases();

	List<SubDisease> findAllActiveSubDiseases(Boolean isActive);

	List<SubDisease> findAllSubDiseasesForDisease(Boolean isActive, Integer diseaseId);
}
