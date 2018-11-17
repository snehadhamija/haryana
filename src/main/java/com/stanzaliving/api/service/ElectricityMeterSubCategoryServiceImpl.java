package com.stanzaliving.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stanzaliving.api.dao.ElectricityMeterSubCategoryDao;
import com.stanzaliving.api.model.ElectricityMeterCategory;
import com.stanzaliving.api.model.ElectricityMeterSubCategory;

@Service("electricityMeterSubCategoryService")
@Transactional
public class ElectricityMeterSubCategoryServiceImpl implements ElectricityMeterSubCategoryService {

	@Autowired
	private ElectricityMeterSubCategoryDao dao;

	public ElectricityMeterSubCategory findById(int id) {
		return dao.findById(id);
	}

	public List<ElectricityMeterSubCategory> findAllElectricityMeterSubCategories() {
		return dao.findAllElectricityMeterSubCategories();
	}

	@Override
	public List<ElectricityMeterSubCategory> findAllElectricityMeterSubCategoriesForElectricityMeterCategory(
			ElectricityMeterCategory electricityMeterCategory) {
		return dao.findAllElectricityMeterSubCategoriesForElectricityMeterCategory(electricityMeterCategory);
	}

}
