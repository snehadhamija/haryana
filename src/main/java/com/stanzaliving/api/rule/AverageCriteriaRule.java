package com.stanzaliving.api.rule;

import java.text.DecimalFormat;
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
public class AverageCriteriaRule extends AbstractRule {

	@Autowired
	ElectricityMeterReadingsService electricityMeterReadingsService;

	@Autowired
	ElectricityMeterDetailsService electricityMeterDetailsService;

	private static Logger logger = LoggerFactory.getLogger(AverageCriteriaRule.class);

	private List<Integer> mainMeterCategories = Arrays.asList(1, 3);
	private List<Integer> roomMeterCategories = Arrays.asList(2, 4);

	public void run(HashMap<String, Object> entry) {
		Integer meterDetailsId = (Integer) entry.get("id");
		ElectricityMeterDetails electricityMeterDetails = electricityMeterDetailsService.findById(meterDetailsId);
		if (mainMeterCategories.contains(
				electricityMeterDetails.getElectricityMeterSubCategory().getElectricityMeterCategory().getId())) {
			if (mainMeterMultipleRule(electricityMeterDetails, entry)) {
				setPassed(true);
			} else {
				setPassed(false);
			}
		} else {
			setPassed(true);
		}
	}

	public boolean mainMeterMultipleRule(ElectricityMeterDetails electricityMeterDetails,
			HashMap<String, Object> entry) {
		List<ElectricityMeterReadings> electricityMeterReadingsForAskedNumber = electricityMeterReadingsService
				.findAskedNumberElectricityMeterReadingsForMeter(electricityMeterDetails,
						Constants.AVERAGE_CRITERIA_AVERAGE_VALUE);
		Double readingKwhDouble = null;
		Double readingKwahDouble = null;
		Double totalReadingKwhDouble = 0.00d;
		Double totalReadingKwahDouble = 0.00d;
		Double averageReadingKwhDouble = null;
		Double averageReadingKwahDouble = null;
		String readingKwh = (String) entry.get("readingKwh");
		if (readingKwh != null) {
			readingKwhDouble = Double.valueOf(readingKwh);
		}
		String readingKwah = (String) entry.get("readingKwah");
		if (readingKwah != null) {
			readingKwahDouble = Double.valueOf(readingKwah);
		}
		for (ElectricityMeterReadings reading : electricityMeterReadingsForAskedNumber) {
			if (reading.getReadingKwh() != null && reading.getReadingKwh() != "") {
				totalReadingKwhDouble = totalReadingKwhDouble + Double.valueOf(reading.getReadingKwh());
			}
			if (reading.getReadingKwah() != null && reading.getReadingKwah() != "") {
				totalReadingKwahDouble = totalReadingKwahDouble + Double.valueOf(reading.getReadingKwah());
			}
		}
		averageReadingKwhDouble = totalReadingKwhDouble / Integer.valueOf(Constants.AVERAGE_CRITERIA_AVERAGE_VALUE);
		averageReadingKwahDouble = totalReadingKwahDouble / Integer.valueOf(Constants.AVERAGE_CRITERIA_AVERAGE_VALUE);
		if (readingKwhDouble != null && averageReadingKwhDouble != null) {
			if (checkForAverageCriteria(readingKwhDouble, averageReadingKwhDouble)) {
				return false;
			}
		}
		if (readingKwahDouble != null && averageReadingKwahDouble != null) {
			if (checkForAverageCriteria(readingKwahDouble, averageReadingKwahDouble)) {
				return false;
			}
		}
		return true;
	}

	public boolean checkForAverageCriteria(Double newReading, Double averageReading) {
		Double multipliedValue = (averageReading * Integer.valueOf(Constants.AVERAGE_CRITERIA_PERCENTAGE_VALUE)) / 100;
		DecimalFormat decimalFormat = new DecimalFormat("#.##");
		Double multipliedValueRounded = Double.valueOf(decimalFormat.format(multipliedValue));
		if (Double.compare(newReading, multipliedValueRounded) > 0) {
			return true;
		}
		return false;
	}
}
