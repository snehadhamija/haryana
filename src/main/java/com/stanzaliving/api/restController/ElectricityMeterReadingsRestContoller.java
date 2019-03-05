package com.stanzaliving.api.restController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.stanzaliving.api.constants.Constants;
import com.stanzaliving.api.dto.UserDto;
import com.stanzaliving.api.factory.ElectricityReadingRuleFactory;
import com.stanzaliving.api.model.ElectricityMeterDetails;
import com.stanzaliving.api.model.ElectricityMeterReadings;
import com.stanzaliving.api.service.ElectricityMeterDetailsService;
import com.stanzaliving.api.service.ElectricityMeterReadingImagesService;
import com.stanzaliving.api.service.ElectricityMeterReadingsService;
import com.stanzaliving.api.service.ElectricityMeterSubCategoryService;
import com.stanzaliving.api.service.SpringRestClientService;

@RestController
public class ElectricityMeterReadingsRestContoller {

	@Autowired
	ElectricityMeterDetailsService electricityMeterDetailsService;

	@Autowired
	ElectricityMeterSubCategoryService electricityMeterSubCategoryService;

	@Autowired
	ElectricityMeterReadingsService electricityMeterReadingsService;

	@Autowired
	ElectricityMeterReadingImagesService electricityMeterReadingImagesService;

	@Autowired
	SpringRestClientService springRestClientService;

	@Autowired
	ElectricityReadingRuleFactory electricityReadingRuleFactory;

	// -------------------Retrieve All electricityMeterDetails
	@RequestMapping(value = { "/electricityMeterReadings" }, method = RequestMethod.GET)
	public ResponseEntity<List<ElectricityMeterReadings>> getElectricityMeterReadings() {
		List<ElectricityMeterReadings> electricityMeterReadings = electricityMeterReadingsService
				.findAllElectricityMeterReadings();
		return new ResponseEntity<List<ElectricityMeterReadings>>(electricityMeterReadings, HttpStatus.OK);
	}

	// -------------------Retrieve all
	// electricityMeterReadings for a
	// Meter
	@RequestMapping(value = "/electricityMeterReadings/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ElectricityMeterReadings>> getElectricityMeterReadingsForMeter(
			@PathVariable("id") int id,
			@RequestParam(value = "last", required = false, defaultValue = "false") boolean last,
			@RequestParam(value = "numberOfReadings", required = false, defaultValue = "1") String numberOfReadings) {
		ElectricityMeterDetails electricityMeterDetails = electricityMeterDetailsService.findById(id);
		if (electricityMeterDetails == null) {
			System.out.println("electricityMeterDetails with id " + id + " not found");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		List<ElectricityMeterReadings> electricityMeterReadings = electricityMeterReadingsService
				.findAllElectricityMeterReadingsForMeter(electricityMeterDetails);
		if (electricityMeterReadings.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		if (!numberOfReadings.equals("1")) {
			List<ElectricityMeterReadings> electricityMeterReadingsForAskedNumber = electricityMeterReadingsService
					.findAskedNumberElectricityMeterReadingsForMeter(electricityMeterDetails, numberOfReadings);
			return new ResponseEntity<List<ElectricityMeterReadings>>(electricityMeterReadingsForAskedNumber,
					HttpStatus.OK);
		}
		if (last) {
			List<ElectricityMeterReadings> emr = new ArrayList<ElectricityMeterReadings>();
			emr.add(electricityMeterReadings.get(electricityMeterReadings.size() - 1));
			return new ResponseEntity<List<ElectricityMeterReadings>>(emr, HttpStatus.OK);
		}
		return new ResponseEntity<List<ElectricityMeterReadings>>(electricityMeterReadings, HttpStatus.OK);
	}

	// -------------------Save
	// electricityMeterReadings for a
	// Meter
	// Sample Request Payload
	// [
	// {
	// "id":7,
	// "imgUrls":[
	// "https://stanza-website.s3.ap-south-1.amazonaws.com/2DB40E68-0185-437A-9767-0564B3AD1693.jpg",
	// "https://stanza-website.s3.ap-south-1.amazonaws.com/33A49592-22EA-4DC8-ABE9-4E617258679B.jpg"
	// ],
	// "readingKwah":"1",
	// "readingKwh":"2",
	// "readingDate":"2018-11-23 09:38:43"
	// }
	// ]
	@RequestMapping(value = "/electricityMeterReadings", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Object> saveElectricityMeterReadingsForMeter(
			@RequestBody List<HashMap<String, Object>> request, HttpServletRequest req) {
		UserDto userDto = springRestClientService.getUserDto(req);
		List<ElectricityMeterReadings> electricityMeterReadingsList = new ArrayList<>();
		for (HashMap<String, Object> entry : request) {
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
			if (electricityMeterDetails != null) {
				ElectricityMeterReadings electricityMeterReadings = null;
				if (readingId != null) {
					electricityMeterReadings = electricityMeterReadingsService.saveOrUpdateElectricityMeterReadings(
							readingId, electricityMeterDetails, userDto.getUserId(), readingKwah, readingKwh,
							meterReading, unitBalance, readingDate);
				} else {
					electricityMeterReadings = electricityMeterReadingsService.save(electricityMeterDetails,
							userDto.getUserId(), readingKwah, readingKwh, meterReading, unitBalance, readingDate);
				}
				electricityMeterReadingsList.add(electricityMeterReadings);
				if (imgUrls != null && !imgUrls.isEmpty()) {
					electricityMeterReadingImagesService.save(electricityMeterReadings, imgUrls);
				}
			}
		}
		return new ResponseEntity<Object>(electricityMeterReadingsList, HttpStatus.OK);
	}

	@RequestMapping(value = "/demoCall", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Object> demoCall(@RequestBody List<HashMap<String, Object>> request, HttpServletRequest req) {
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
		return new ResponseEntity<Object>(ruleStatus, HttpStatus.OK);
	}

}
