package com.stanzaliving.api.service;

import java.util.List;

import com.stanzaliving.api.model.ElectricityMeterCategory;

public interface ElectricityMeterCategoryService {

	ElectricityMeterCategory findById(int id);

	List<ElectricityMeterCategory> findAllElectricityMeterCategories();
}
