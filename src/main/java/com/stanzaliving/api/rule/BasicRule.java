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
		if (mainMeterCategories.contains(
				electricityMeterDetails.getElectricityMeterSubCategory().getElectricityMeterCategory().getId())) {
			System.out.println("Found meter with category id: "
					+ electricityMeterDetails.getElectricityMeterSubCategory().getElectricityMeterCategory().getId());
			if (mainMeterBasicRule(electricityMeterDetails, entry)) {
				setPassed(true);
			} else {
				setPassed(false);
			}
		} else if (roomMeterCategories.contains(
				electricityMeterDetails.getElectricityMeterSubCategory().getElectricityMeterCategory().getId())) {
			System.out.println("Found meter with category id: "
					+ electricityMeterDetails.getElectricityMeterSubCategory().getElectricityMeterCategory().getId());
			if (roomMeterBasicRule(electricityMeterDetails, entry)) {
				setPassed(true);
			} else {
				setPassed(false);
			}
		}
	}

	public boolean mainMeterBasicRule(ElectricityMeterDetails electricityMeterDetails, HashMap<String, Object> entry) {
		// find last reading for this electricity meter
		ElectricityMeterReadings lastElectricityMeterReading = electricityMeterReadingsService
				.findLastElectricityMeterReadingsForMeter(electricityMeterDetails);
		// compare respective kwah and kvh readings
		Integer readingKwhInt = null;
		Integer readingKwahInt = null;
		Integer lastReadingKwhInt = null;
		Integer lastReadingKwahInt = null;
		String readingKwh = (String) entry.get("readingKwh");
		if (readingKwh != null) {
			readingKwhInt = Integer.valueOf(readingKwh);
		}
		String readingKwah = (String) entry.get("readingKwah");
		if (readingKwah != null) {
			readingKwahInt = Integer.valueOf(readingKwah);
		}
		if (lastElectricityMeterReading.getReadingKwh() != null) {
			lastReadingKwhInt = Integer.valueOf(lastElectricityMeterReading.getReadingKwh());
		}
		if (lastElectricityMeterReading.getReadingKwah() != null) {
			lastReadingKwahInt = Integer.valueOf(lastElectricityMeterReading.getReadingKwah());
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
		ElectricityMeterReadings lastElectricityMeterReading = electricityMeterReadingsService
				.findLastElectricityMeterReadingsForMeter(electricityMeterDetails);
		// compare respective meter readings
		Integer meterReadingInt = null;
		Integer lastMeterReadingInt = null;
		String meterReading = (String) entry.get("meterReading");
		if (meterReading != null) {
			meterReadingInt = Integer.valueOf(meterReading);
		}
		if (lastElectricityMeterReading.getMeterReading() != null) {
			lastMeterReadingInt = Integer.valueOf(lastElectricityMeterReading.getMeterReading());
		}
		// if found greater fail
		if (meterReadingInt != null && lastMeterReadingInt != null)
			if (checkGreaterThanLastReading(meterReadingInt, lastMeterReadingInt)) {
				return false;
			}
		return true;
	}

	public boolean checkGreaterThanLastReading(Integer newReading, Integer lastReading) {
		if (lastReading > newReading) {
			return true;
		}
		return false;
	}

}
