package com.stanzaliving.api.dao;

import java.util.List;

import com.stanzaliving.api.model.ElectricityMeterCategory;

public interface ElectricityMeterCategoryDao {

	ElectricityMeterCategory findById(int id);

	List<ElectricityMeterCategory> findAllElectricityMeterCategories();
}
