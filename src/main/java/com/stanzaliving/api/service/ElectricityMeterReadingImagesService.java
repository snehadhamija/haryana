package com.stanzaliving.api.service;

import java.util.List;

import com.stanzaliving.api.model.ElectricityMeterReadings;

public interface ElectricityMeterReadingImagesService {

	void save(ElectricityMeterReadings electricityMeterReadings, List<String> imgUrls);
}
