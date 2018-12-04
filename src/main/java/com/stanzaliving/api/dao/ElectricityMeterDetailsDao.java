package com.stanzaliving.api.dao;

import java.util.List;

import com.stanzaliving.api.model.ElectricityMeterDetails;
import com.stanzaliving.api.model.ElectricityMeterSubCategory;

public interface ElectricityMeterDetailsDao {

	ElectricityMeterDetails findById(int id);

	List<ElectricityMeterDetails> findAllElectricityMeterDetails();

	List<ElectricityMeterDetails> findAllElectricityMeterDetailsForSubCategoryInHostel(
			ElectricityMeterSubCategory electricityMeterSubCategory, int hostelId);

}
