package com.stanzaliving.api.service;

import java.util.List;

import com.stanzaliving.api.model.ElectricityMeterDetails;
import com.stanzaliving.api.model.ElectricityMeterReadings;

public interface ElectricityMeterReadingsService {

	ElectricityMeterReadings save(ElectricityMeterDetails electricityMeterDetails, int recordedBy, String readingKwah,
			String readingKwh, String meterReading, String unitBalance, String readingDate);

	ElectricityMeterReadings findById(int id);

	List<ElectricityMeterReadings> findAllElectricityMeterReadings();

	List<ElectricityMeterReadings> findAllElectricityMeterReadingsForMeter(
			ElectricityMeterDetails electricityMeterDetails);

	List<ElectricityMeterReadings> findAskedNumberElectricityMeterReadingsForMeter(
			ElectricityMeterDetails electricityMeterDetails, String numberOfReadings);

	ElectricityMeterReadings findLastElectricityMeterReadingsForMeter(ElectricityMeterDetails electricityMeterDetails);
}
