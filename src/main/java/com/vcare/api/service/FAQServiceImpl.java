package com.vcare.api.service;

import java.util.List;
import java.util.Objects;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vcare.api.dao.FAQDao;
import com.vcare.api.model.FAQ;

@Service("faqService")
@Transactional
public class FAQServiceImpl implements FAQService {

	@Autowired
	private FAQDao dao;

	@Override
	public void save(FAQ faq) {
		dao.save(faq);
	}

	@Override
	public FAQ findById(int id) {
		FAQ faq = dao.findById(id);
		if (Objects.nonNull(faq)) {
			Hibernate.initialize(faq.getSubDiseases());
			return faq;
		}
		return null;
	}

	@Override
	public List<FAQ> findAllFAQs() {
		return dao.findAllFAQs();
	}

	@Override
	public List<FAQ> findAllActiveFAQs(Boolean isActive) {
		return dao.findAllActiveFAQs(isActive);
	}

	@Override
	public List<FAQ> findAllFAQsForSubDisease(Boolean isActive, Integer subDiseaseId) {
		return dao.findAllFAQsForSubDisease(isActive, subDiseaseId);
	}
}