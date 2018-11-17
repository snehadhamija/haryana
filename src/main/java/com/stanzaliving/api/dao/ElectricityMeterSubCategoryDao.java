package com.stanzaliving.api.dao;

import java.util.List;

import com.stanzaliving.api.model.ElectricityMeterCategory;
import com.stanzaliving.api.model.ElectricityMeterSubCategory;

public interface ElectricityMeterSubCategoryDao {

	ElectricityMeterSubCategory findById(int id);

	List<ElectricityMeterSubCategory> findAllElectricityMeterSubCategories();

	List<ElectricityMeterSubCategory> findAllElectricityMeterSubCategoriesForElectricityMeterCategory(
			ElectricityMeterCategory electricityMeterCategory);
}
