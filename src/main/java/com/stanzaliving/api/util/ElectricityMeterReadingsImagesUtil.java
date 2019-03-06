package com.stanzaliving.api.util;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.stanzaliving.api.model.ElectricityMeterReadingImages;
import com.stanzaliving.api.model.ElectricityMeterReadings;
import com.stanzaliving.api.service.ElectricityMeterReadingImagesService;

@Component
public class ElectricityMeterReadingsImagesUtil {

	@Autowired
	ElectricityMeterReadingImagesService electricityMeterReadingImagesService;

	public void deleteExistingImagesEntries(ElectricityMeterReadingImages electricityMeterReadingImages) {
		electricityMeterReadingImagesService.deleteElectricityMeterReadingImages(electricityMeterReadingImages);
	}

	public void createOrUpdateElectricityMeterReadingImages(List<String> imgUrls,
			ElectricityMeterReadings electricityMeterReadings) {
		List<ElectricityMeterReadingImages> electricityMeterReadingImages = electricityMeterReadingImagesService
				.findElectricityMeterReadingImagesForElectricityMeterReadings(electricityMeterReadings);
		if (!electricityMeterReadingImages.isEmpty()) {
			electricityMeterReadingImages.forEach(emri -> {
				deleteExistingImagesEntries(emri);
			});
		}
		electricityMeterReadingImagesService.save(electricityMeterReadings, imgUrls);
	}
}
