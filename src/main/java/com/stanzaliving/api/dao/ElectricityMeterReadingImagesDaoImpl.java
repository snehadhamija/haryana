package com.stanzaliving.api.dao;

import org.springframework.stereotype.Repository;

import com.stanzaliving.api.model.ElectricityMeterReadingImages;

@Repository("electricityMeterReadingImagesDao")
public class ElectricityMeterReadingImagesDaoImpl extends AbstractDao<Integer, ElectricityMeterReadingImages>
		implements ElectricityMeterReadingImagesDao {

	@Override
	public void save(ElectricityMeterReadingImages electricityMeterReadingImages) {
		persist(electricityMeterReadingImages);
	}

}
