package com.stanzaliving.api.util;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import com.stanzaliving.api.constants.Constants;
import com.stanzaliving.api.dto.UserDto;
import com.stanzaliving.api.factory.ElectricityReadingRuleFactory;
import com.stanzaliving.api.model.ElectricityMeterDetails;
import com.stanzaliving.api.model.ElectricityMeterReadings;
import com.stanzaliving.api.service.ElectricityMeterDetailsService;
import com.stanzaliving.api.service.ElectricityMeterReadingsService;

@Component
public class ElectricityMeterReadingsUtil {

	@Autowired
	ElectricityReadingRuleFactory electricityReadingRuleFactory;

	@Autowired
	ElectricityMeterDetailsService electricityMeterDetailsService;

	@Autowired
	ElectricityMeterReadingsService electricityMeterReadingsService;

	@Autowired
	ElectricityMeterReadingsImagesUtil electricityMeterReadingsImagesUtil;

	public Object demoCall(@RequestBody List<HashMap<String, Object>> request, HttpServletRequest req) {
		String ruleStatus = "";
		boolean rulesPassed = true;
		for (HashMap<String, Object> entry : request) {
			System.out.println(entry);
			for (String rule : Constants.ELECTRICITY_READING_RULES) {
				boolean isRulePassed = electricityReadingRuleFactory.runRule(rule, entry);
				if (isRulePassed) {
					ruleStatus += "Rule " + rule + " pass.\n";
				} else {
					ruleStatus += "Rule " + rule + " fail.\n";
					rulesPassed = false;
					break;
				}
			}
		}
		System.out.println(ruleStatus);
		return ruleStatus;
	}

	public HashMap<String, Object> validateElectricityReadingRules(List<HashMap<String, Object>> request) {
		String ruleStatus = "";
		boolean rulesPassed = true;
		outerLoop: for (HashMap<String, Object> entry : request) {
			for (String rule : Constants.ELECTRICITY_READING_RULES) {
				boolean isRulePassed = electricityReadingRuleFactory.runRule(rule, entry);
				if (isRulePassed) {
					// ruleStatus += "Rule " + rule + " passed .\n";
					System.out.println("Rule " + rule + " passed .\n");
				} else {
					ruleStatus += "Rule " + rule + " failed.\n";
					rulesPassed = false;
					break outerLoop;
				}
			}
		}
		HashMap<String, Object> ruleStatusHashMap = new HashMap<>();
		ruleStatusHashMap.put("ruleStatus", ruleStatus);
		ruleStatusHashMap.put("rulesPassed", rulesPassed);
		return ruleStatusHashMap;
	}

	public ElectricityMeterReadings saveOrUpdateElectricityMeterReadings(HashMap<String, Object> entry,
			UserDto userDto) {
		Integer readingId = null;
		if (entry.containsKey("readingId")) {
			readingId = (Integer) entry.get("readingId");
		}
		Integer meterDetailsId = (Integer) entry.get("id");
		String readingKwh = (String) entry.get("readingKwh");
		String readingKwah = (String) entry.get("readingKwah");
		String meterReading = (String) entry.get("meterReading");
		String unitBalance = (String) entry.get("unitBalance");
		String readingDate = (String) entry.get("readingDate");
		List<String> imgUrls = (List<String>) entry.get("imgUrls");
		ElectricityMeterDetails electricityMeterDetails = electricityMeterDetailsService.findById(meterDetailsId);
		ElectricityMeterReadings electricityMeterReadings = null;
		if (electricityMeterDetails != null) {
			if (readingId != null) {
				electricityMeterReadings = electricityMeterReadingsService.saveOrUpdateElectricityMeterReadings(
						readingId, electricityMeterDetails, userDto.getUserId(), readingKwah, readingKwh, meterReading,
						unitBalance, readingDate);
			} else {
				electricityMeterReadings = electricityMeterReadingsService.save(electricityMeterDetails,
						userDto.getUserId(), readingKwah, readingKwh, meterReading, unitBalance, readingDate);
			}
			if (imgUrls != null && !imgUrls.isEmpty()) {
				electricityMeterReadingsImagesUtil.createOrUpdateElectricityMeterReadingImages(imgUrls,
						electricityMeterReadings);
			}
		}
		return electricityMeterReadings;
	}

}
