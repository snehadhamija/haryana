package com.stanzaliving.api.rule;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.stanzaliving.api.model.ElectricityMeterDetails;
import com.stanzaliving.api.model.ElectricityMeterReadings;
import com.stanzaliving.api.service.ElectricityMeterDetailsService;
import com.stanzaliving.api.service.ElectricityMeterReadingsService;

@Configuration
public class BasicRule extends AbstractRule {

	@Autowired
	ElectricityMeterReadingsService electricityMeterReadingsService;

	@Autowired
	ElectricityMeterDetailsService electricityMeterDetailsService;

	private static Logger logger = LoggerFactory.getLogger(BasicRule.class);

	private List<Integer> mainMeterCategories = Arrays.asList(1, 3);
	private List<Integer> roomMeterCategories = Arrays.asList(2, 4);

	public void run(HashMap<String, Object> entry) {
		Integer meterDetailsId = (Integer) entry.get("id");
		ElectricityMeterDetails electricityMeterDetails = electricityMeterDetailsService.findById(meterDetailsId);
		String meterName = electricityMeterDetails.getMaterName();
		if (mainMeterCategories.contains(
				electricityMeterDetails.getElectricityMeterSubCategory().getElectricityMeterCategory().getId())) {
			if (mainMeterBasicRule(electricityMeterDetails, entry)) {
				setPassed(true);
			} else {
				setPassed(false);
			}
		} else if (roomMeterCategories.contains(
				electricityMeterDetails.getElectricityMeterSubCategory().getElectricityMeterCategory().getId())) {
			if (roomMeterBasicRule(electricityMeterDetails, entry)) {
				setPassed(true);
			} else {
				setPassed(false);
			}
		}
	}

	public boolean mainMeterBasicRule(ElectricityMeterDetails electricityMeterDetails, HashMap<String, Object> entry) {
		Integer readingId = null;
		if (entry.containsKey("readingId")) {
			readingId = (Integer) entry.get("readingId");
		}
		// find last reading for this electricity meter
		ElectricityMeterReadings lastElectricityMeterReading = null;
		if (readingId != null) {
			lastElectricityMeterReading = electricityMeterReadingsService
					.findLastElectricityMeterReadingsForMeterWithInitialValue(electricityMeterDetails, readingId);
		} else {
			lastElectricityMeterReading = electricityMeterReadingsService
					.findLastElectricityMeterReadingsForMeter(electricityMeterDetails);
		}
		// compare respective kwah and kvh readings
		Double readingKwhInt = null;
		Double readingKwahInt = null;
		Double lastReadingKwhInt = null;
		Double lastReadingKwahInt = null;
		String readingKwh = (String) entry.get("readingKwh");
		if (readingKwh != null) {
			readingKwhInt = Double.valueOf(readingKwh);
		}
		String readingKwah = (String) entry.get("readingKwah");
		if (readingKwah != null) {
			readingKwahInt = Double.valueOf(readingKwah);
		}
		if (lastElectricityMeterReading.getReadingKwh() != null) {
			lastReadingKwhInt = Double.valueOf(lastElectricityMeterReading.getReadingKwh());
		}
		if (lastElectricityMeterReading.getReadingKwah() != null) {
			lastReadingKwahInt = Double.valueOf(lastElectricityMeterReading.getReadingKwah());
		}
		// if found greater fail
		if (readingKwhInt != null && lastReadingKwhInt != null) {
			if (checkGreaterThanLastReading(readingKwhInt, lastReadingKwhInt)) {
				return false;
			}
		}
		if (readingKwahInt != null && lastReadingKwahInt != null) {
			if (checkGreaterThanLastReading(readingKwahInt, lastReadingKwahInt)) {
				return false;
			}
		}
		return true;
	}

	public boolean roomMeterBasicRule(ElectricityMeterDetails electricityMeterDetails, HashMap<String, Object> entry) {
		// find last reading for this electricity meter
		Integer readingId = null;
		if (entry.containsKey("readingId")) {
			readingId = (Integer) entry.get("readingId");
		}
		// find last reading for this electricity meter
		ElectricityMeterReadings lastElectricityMeterReading = null;
		if (readingId != null) {
			lastElectricityMeterReading = electricityMeterReadingsService
					.findLastElectricityMeterReadingsForMeterWithInitialValue(electricityMeterDetails, readingId);
		} else {
			lastElectricityMeterReading = electricityMeterReadingsService
					.findLastElectricityMeterReadingsForMeter(electricityMeterDetails);
		}
		// compare respective meter readings
		Double meterReadingInt = null;
		Double lastMeterReadingInt = null;
		String meterReading = (String) entry.get("meterReading");
		if (meterReading != null) {
			meterReadingInt = Double.valueOf(meterReading);
		}
		if (lastElectricityMeterReading.getMeterReading() != null) {
			lastMeterReadingInt = Double.valueOf(lastElectricityMeterReading.getMeterReading());
		}
		// if found greater fail
		if (meterReadingInt != null && lastMeterReadingInt != null)
			if (checkGreaterThanLastReading(meterReadingInt, lastMeterReadingInt)) {
				return false;
			}
		return true;
	}

	public boolean checkGreaterThanLastReading(Double newReading, Double lastReading) {
		if (Double.compare(lastReading, newReading) > 0) {
			return true;
		}
		return false;
	}

}
