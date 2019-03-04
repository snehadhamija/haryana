package com.stanzaliving.api.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stanzaliving.api.dao.ElectricityMeterReadingsDao;
import com.stanzaliving.api.model.ElectricityMeterDetails;
import com.stanzaliving.api.model.ElectricityMeterReadings;
import com.stanzaliving.api.util.DateUtil;

@Service("electricityMeterReadingsService")
@Transactional
public class ElectricityMeterReadingsServiceImpl implements ElectricityMeterReadingsService {

	@Autowired
	private ElectricityMeterReadingsDao dao;

	@Override
	public ElectricityMeterReadings save(ElectricityMeterDetails electricityMeterDetails, int recordedBy,
			String readingKwah, String readingKwh, String meterReading, String unitBalance, String readingDate) {
		ElectricityMeterReadings electricityMeterReadings = new ElectricityMeterReadings();
		electricityMeterReadings.setElectricityMeterDetails(electricityMeterDetails);
		Date readingDateFormatted = DateUtil.returnStringInDateFormat(readingDate);
		electricityMeterReadings.setReadingDate(readingDateFormatted);
		electricityMeterReadings.setReadingKwah(readingKwah);
		electricityMeterReadings.setReadingKwh(readingKwh);
		electricityMeterReadings.setMeterReading(meterReading);
		electricityMeterReadings.setUnitBalance(unitBalance);
		electricityMeterReadings.setRecordedBy(recordedBy);
		dao.save(electricityMeterReadings);
		return electricityMeterReadings;
	}

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

	@Override
	public ElectricityMeterReadings findLastElectricityMeterReadingsForMeter(
			ElectricityMeterDetails electricityMeterDetails) {
		return dao.findLastElectricityMeterReadingsForMeter(electricityMeterDetails);
	}

	@Override
	public List<ElectricityMeterReadings> findAskedNumberElectricityMeterReadingsForMeter(
			ElectricityMeterDetails electricityMeterDetails, String numberOfReadings) {
		return dao.findAskedNumberElectricityMeterReadingsForMeter(electricityMeterDetails, numberOfReadings);
	}

}
