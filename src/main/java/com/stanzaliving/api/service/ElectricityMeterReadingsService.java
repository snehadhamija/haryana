package com.stanzaliving.api.service;

import java.util.List;

import com.stanzaliving.api.model.ElectricityMeterDetails;
import com.stanzaliving.api.model.ElectricityMeterReadings;

public interface ElectricityMeterReadingsService {

	ElectricityMeterReadings findById(int id);

	List<ElectricityMeterReadings> findAllElectricityMeterReadings();

	List<ElectricityMeterReadings> findAllElectricityMeterReadingsForMeter(
			ElectricityMeterDetails electricityMeterDetails);

	ElectricityMeterReadings findLastElectricityMeterReadingsForMeter(ElectricityMeterDetails electricityMeterDetails);
}
