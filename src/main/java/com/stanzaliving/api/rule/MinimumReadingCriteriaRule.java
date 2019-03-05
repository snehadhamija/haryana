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
public class MinimumReadingCriteriaRule extends AbstractRule {

	@Autowired
	ElectricityMeterReadingsService electricityMeterReadingsService;

	@Autowired
	ElectricityMeterDetailsService electricityMeterDetailsService;

	private static Logger logger = LoggerFactory.getLogger(MinimumReadingCriteriaRule.class);

	private List<Integer> mainMeterCategories = Arrays.asList(1, 3);
	private List<Integer> roomMeterCategories = Arrays.asList(2, 4);

	public void run(HashMap<String, Object> entry) {
		Integer meterDetailsId = (Integer) entry.get("id");
		ElectricityMeterDetails electricityMeterDetails = electricityMeterDetailsService.findById(meterDetailsId);
		if (mainMeterCategories.contains(
				electricityMeterDetails.getElectricityMeterSubCategory().getElectricityMeterCategory().getId())) {
			if (mainMeterMinimumReadingCriteriaRule(electricityMeterDetails, entry)) {
				setPassed(true);
			} else {
				setPassed(false);
			}
		} else {
			setPassed(true);
		}
	}

	public boolean mainMeterMinimumReadingCriteriaRule(ElectricityMeterDetails electricityMeterDetails,
			HashMap<String, Object> entry) {
		List<ElectricityMeterReadings> electricityMeterReadingsForAskedNumber = electricityMeterReadingsService
				.findAskedNumberElectricityMeterReadingsForMeter(electricityMeterDetails,
						Constants.MINIMUM_READING_CRITERIA_VALUE);
		if (electricityMeterReadingsForAskedNumber.size() >= Integer
				.valueOf(Constants.MINIMUM_READING_CRITERIA_VALUE)) {
			return true;
		}
		return false;
	}
}
