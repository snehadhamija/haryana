package com.vcare.api.service;

import java.util.List;

import com.vcare.api.model.AskAQuery;

public interface AskAQueryService {

	void save(AskAQuery askAQuery);

	AskAQuery findById(int id);

	List<AskAQuery> findQueriesForMobileAndEmail(String mobileNumber, String email);
}
