package com.stanzaliving.api.service;

import java.util.List;

import com.stanzaliving.api.model.ElectricityMeterDetails;
import com.stanzaliving.api.model.ElectricityMeterSubCategory;

public interface ElectricityMeterDetailsService {

	ElectricityMeterDetails findById(int id);

	List<ElectricityMeterDetails> findAllElectricityMeterDetails();

	List<ElectricityMeterDetails> findAllElectricityMeterDetailsForSubCategoryInHostel(
			ElectricityMeterSubCategory electricityMeterSubCategory, int hostelId);

}
