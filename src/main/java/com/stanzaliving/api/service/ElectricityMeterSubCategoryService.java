package com.stanzaliving.api.service;

import java.util.List;

import com.stanzaliving.api.model.ElectricityMeterCategory;
import com.stanzaliving.api.model.ElectricityMeterSubCategory;

public interface ElectricityMeterSubCategoryService {

	ElectricityMeterSubCategory findById(int id);

	List<ElectricityMeterSubCategory> findAllElectricityMeterSubCategories();

	List<ElectricityMeterSubCategory> findAllElectricityMeterSubCategoriesForElectricityMeterCategory(
			ElectricityMeterCategory electricityMeterCategory);
}
