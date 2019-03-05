package com.stanzaliving.api.rule;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.stanzaliving.api.constants.Constants;
import com.stanzaliving.api.model.ElectricityMeterDetails;
import com.stanzaliving.api.model.ElectricityMeterReadings;
import com.stanzaliving.api.service.ElectricityMeterDetailsService;
import com.stanzaliving.api.service.ElectricityMeterReadingsService;

@Configuration
public class MultipleCriteriaRule extends AbstractRule {

	@Autowired
	ElectricityMeterReadingsService electricityMeterReadingsService;

	@Autowired
	ElectricityMeterDetailsService electricityMeterDetailsService;

	@Autowired
	MinimumReadingCriteriaRule minimumReadingCriteriaRule;

	private static Logger logger = LoggerFactory.getLogger(MultipleCriteriaRule.class);

	private List<Integer> mainMeterCategories = Arrays.asList(1, 3);
	private List<Integer> roomMeterCategories = Arrays.asList(2, 4);

	public void run(HashMap<String, Object> entry) {
		Integer meterDetailsId = (Integer) entry.get("id");
		ElectricityMeterDetails electricityMeterDetails = electricityMeterDetailsService.findById(meterDetailsId);
		if (mainMeterCategories.contains(
				electricityMeterDetails.getElectricityMeterSubCategory().getElectricityMeterCategory().getId())) {
			boolean isEligibilityCriteriaMet = false;
			if (minimumReadingCriteriaRule.mainMeterMinimumReadingCriteriaRule(electricityMeterDetails)) {
				isEligibilityCriteriaMet = true;
			}
			if (isEligibilityCriteriaMet) {
				if (mainMeterMultipleRule(electricityMeterDetails, entry)) {
					setPassed(true);
				} else {
					setPassed(false);
				}
			} else {
				setPassed(true);
			}
		} else {
			setPassed(true);
		}
	}

	public boolean mainMeterMultipleRule(ElectricityMeterDetails electricityMeterDetails,
			HashMap<String, Object> entry) {
		ElectricityMeterReadings lastElectricityMeterReading = electricityMeterReadingsService
				.findLastElectricityMeterReadingsForMeter(electricityMeterDetails);
		Double readingKwhDouble = null;
		Double readingKwahDouble = null;
		Double lastReadingKwhDouble = null;
		Double lastReadingKwahDouble = null;
		String readingKwh = (String) entry.get("readingKwh");
		if (readingKwh != null) {
			readingKwhDouble = Double.valueOf(readingKwh);
		}
		String readingKwah = (String) entry.get("readingKwah");
		if (readingKwah != null) {
			readingKwahDouble = Double.valueOf(readingKwah);
		}
		if (lastElectricityMeterReading.getReadingKwh() != null) {
			lastReadingKwhDouble = Double.valueOf(lastElectricityMeterReading.getReadingKwh());
		}
		if (lastElectricityMeterReading.getReadingKwah() != null) {
			lastReadingKwahDouble = Double.valueOf(lastElectricityMeterReading.getReadingKwah());
		}
		if (readingKwhDouble != null && lastReadingKwhDouble != null) {
			if (checkForMultipleCriteria(readingKwhDouble, lastReadingKwhDouble)) {
				return false;
			}
		}
		if (readingKwahDouble != null && lastReadingKwahDouble != null) {
			if (checkForMultipleCriteria(readingKwahDouble, lastReadingKwahDouble)) {
				return false;
			}
		}
		return true;
	}

	public boolean checkForMultipleCriteria(Double newReading, Double lastReading) {
		Double multipliedValue = lastReading * Integer.valueOf(Constants.MULTIPLE_CRITERIA_VALUE);
		if (Double.compare(newReading, multipliedValue) > 0) {
			return true;
		}
		return false;
	}
}
