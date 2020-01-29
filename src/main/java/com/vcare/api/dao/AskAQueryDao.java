package com.vcare.api.dao;

import java.util.List;

import com.vcare.api.model.AskAQuery;

public interface AskAQueryDao {

	void save(AskAQuery askAQuery);

	AskAQuery findById(int id);

	List<AskAQuery> findQueriesForMobileAndEmail(String mobileNumber, String email);

}
