package com.stanzaliving.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stanzaliving.api.dao.ElectricityMeterReadingImagesDao;
import com.stanzaliving.api.model.ElectricityMeterReadingImages;
import com.stanzaliving.api.model.ElectricityMeterReadings;

@Service("electricityMeterReadingImagesService")
@Transactional
public class ElectricityMeterReadingImagesServiceImpl implements ElectricityMeterReadingImagesService {

	@Autowired
	private ElectricityMeterReadingImagesDao dao;

	@Override
	public void save(ElectricityMeterReadings electricityMeterReadings, List<String> imgUrls) {
		for (String imgUrl : imgUrls) {
			ElectricityMeterReadingImages electricityMeterReadingImages = new ElectricityMeterReadingImages();
			electricityMeterReadingImages.setElectricityMeterReadings(electricityMeterReadings);
			electricityMeterReadingImages.setImgUrl(imgUrl);
			dao.save(electricityMeterReadingImages);
		}
	}

}
