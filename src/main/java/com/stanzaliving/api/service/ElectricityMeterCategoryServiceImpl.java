package com.stanzaliving.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stanzaliving.api.dao.ElectricityMeterCategoryDao;
import com.stanzaliving.api.model.ElectricityMeterCategory;

@Service("electricityMeterCategoryService")
@Transactional
public class ElectricityMeterCategoryServiceImpl implements ElectricityMeterCategoryService {

	@Autowired
	private ElectricityMeterCategoryDao dao;

	public ElectricityMeterCategory findById(int id) {
		return dao.findById(id);
	}

	public List<ElectricityMeterCategory> findAllElectricityMeterCategories() {
		return dao.findAllElectricityMeterCategories();
	}

}
