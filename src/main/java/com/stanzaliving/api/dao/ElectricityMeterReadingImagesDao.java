package com.stanzaliving.api.dao;

import java.util.List;

import com.stanzaliving.api.model.ElectricityMeterReadingImages;
import com.stanzaliving.api.model.ElectricityMeterReadings;

public interface ElectricityMeterReadingImagesDao {

	void save(ElectricityMeterReadingImages electricityMeterReadingImages);

	void deleteElectricityMeterReadingImages(ElectricityMeterReadingImages electricityMeterReadingImages);

	List<ElectricityMeterReadingImages> findElectricityMeterReadingImagesForElectricityMeterReadings(
			ElectricityMeterReadings electricityMeterReadings);

}
