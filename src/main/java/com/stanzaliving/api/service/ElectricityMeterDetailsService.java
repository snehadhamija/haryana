package com.stanzaliving.api.service;

import java.util.List;

import com.stanzaliving.api.model.ElectricityMeterDetails;
import com.stanzaliving.api.model.ElectricityMeterSubCategory;
import com.stanzaliving.api.model.Hostel;

public interface ElectricityMeterDetailsService {

	ElectricityMeterDetails findById(int id);

	List<ElectricityMeterDetails> findAllElectricityMeterDetails();

	List<ElectricityMeterDetails> findAllElectricityMeterDetailsForSubCategoryInHostel(
			ElectricityMeterSubCategory electricityMeterSubCategory, Hostel hostel);

}
