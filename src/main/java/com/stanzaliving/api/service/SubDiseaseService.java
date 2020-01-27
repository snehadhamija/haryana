package com.stanzaliving.api.service;

import java.util.List;

import com.stanzaliving.api.model.SubDisease;

public interface SubDiseaseService {

	void save(SubDisease subDisease);

	SubDisease findById(int id);

	List<SubDisease> findAllSubDiseases();

	List<SubDisease> findAllActiveSubDiseases(Boolean isActive);

	List<SubDisease> findAllSubDiseasesForDisease(Boolean isActive, Integer diseaseId);
}
