package com.stanzaliving.api.dao;

import java.util.List;

import com.stanzaliving.api.model.ElectricityMeterDetails;
import com.stanzaliving.api.model.ElectricityMeterSubCategory;
import com.stanzaliving.api.model.Hostel;

public interface ElectricityMeterDetailsDao {

	ElectricityMeterDetails findById(int id);

	List<ElectricityMeterDetails> findAllElectricityMeterDetails();

	List<ElectricityMeterDetails> findAllElectricityMeterDetailsForSubCategoryInHostel(
			ElectricityMeterSubCategory electricityMeterSubCategory, Hostel hostel);

}
