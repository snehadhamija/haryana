package com.stanzaliving.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stanzaliving.api.dao.ElectricityMeterReadingsDao;
import com.stanzaliving.api.model.ElectricityMeterDetails;
import com.stanzaliving.api.model.ElectricityMeterReadings;

@Service("electricityMeterReadingsService")
@Transactional
public class ElectricityMeterReadingsServiceImpl implements ElectricityMeterReadingsService {

	@Autowired
	private ElectricityMeterReadingsDao dao;

	public ElectricityMeterReadings findById(int id) {
		return dao.findById(id);
	}

	public List<ElectricityMeterReadings> findAllElectricityMeterReadings() {
		return dao.findAllElectricityMeterReadings();
	}

	@Override
	public List<ElectricityMeterReadings> findAllElectricityMeterReadingsForMeter(
			ElectricityMeterDetails electricityMeterDetails) {
		return dao.findAllElectricityMeterReadingsForMeter(electricityMeterDetails);
	}

}
