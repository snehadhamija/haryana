package com.stanzaliving.api.service;

import java.util.List;

import com.stanzaliving.api.model.FAQ;

public interface FAQService {

	void save(FAQ faq);

	FAQ findById(int id);

	List<FAQ> findAllFAQs();

	List<FAQ> findAllActiveFAQs(Boolean isActive);

	List<FAQ> findAllFAQsForSubDisease(Boolean isActive, Integer subDiseaseId);
}
