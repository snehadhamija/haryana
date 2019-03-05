package com.stanzaliving.api.dao;

import java.util.List;

import com.stanzaliving.api.model.ElectricityMeterDetails;
import com.stanzaliving.api.model.ElectricityMeterReadings;

public interface ElectricityMeterReadingsDao {

	void save(ElectricityMeterReadings electricityMeterReadings);

	void saveOrUpdateElectricityMeterReadings(ElectricityMeterReadings electricityMeterReadings);

	ElectricityMeterReadings findById(int id);

	List<ElectricityMeterReadings> findAllElectricityMeterReadings();

	List<ElectricityMeterReadings> findAllElectricityMeterReadingsForMeter(
			ElectricityMeterDetails electricityMeterDetails);

	List<ElectricityMeterReadings> findAskedNumberElectricityMeterReadingsForMeter(
			ElectricityMeterDetails electricityMeterDetails, String numberOfReadings);

	ElectricityMeterReadings findLastElectricityMeterReadingsForMeter(ElectricityMeterDetails electricityMeterDetails);

}
