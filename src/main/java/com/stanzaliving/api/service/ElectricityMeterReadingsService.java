package com.stanzaliving.api.service;

import java.util.List;

import com.stanzaliving.api.model.ElectricityMeterDetails;
import com.stanzaliving.api.model.ElectricityMeterReadings;

public interface ElectricityMeterReadingsService {

	ElectricityMeterReadings save(ElectricityMeterDetails electricityMeterDetails, int recordedBy, String readingKwah,
			String readingKwh, String meterReading, String unitBalance, String readingDate);

	ElectricityMeterReadings saveOrUpdateElectricityMeterReadings(Integer readingId,
			ElectricityMeterDetails electricityMeterDetails, int recordedBy, String readingKwah, String readingKwh,
			String meterReading, String unitBalance, String readingDate);

	ElectricityMeterReadings findById(int id);

	List<ElectricityMeterReadings> findAllElectricityMeterReadings();

	List<ElectricityMeterReadings> findAllElectricityMeterReadingsForMeter(
			ElectricityMeterDetails electricityMeterDetails);

	List<ElectricityMeterReadings> findAskedNumberElectricityMeterReadingsForMeter(
			ElectricityMeterDetails electricityMeterDetails, String numberOfReadings);

	List<ElectricityMeterReadings> findAskedNumberElectricityMeterReadingsForMeterWithInitialValue(
			ElectricityMeterDetails electricityMeterDetails, String numberOfReadings, Integer readingId);

	ElectricityMeterReadings findLastElectricityMeterReadingsForMeter(ElectricityMeterDetails electricityMeterDetails);

	ElectricityMeterReadings findLastElectricityMeterReadingsForMeterWithInitialValue(
			ElectricityMeterDetails electricityMeterDetails, Integer readingId);
}
