package com.stanzaliving.api.restController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

import com.stanzaliving.api.model.ElectricityMeterDetails;
import com.stanzaliving.api.model.ElectricityMeterReadings;
import com.stanzaliving.api.service.ElectricityMeterDetailsService;
import com.stanzaliving.api.service.ElectricityMeterReadingImagesService;
import com.stanzaliving.api.service.ElectricityMeterReadingsService;
import com.stanzaliving.api.service.ElectricityMeterSubCategoryService;
import com.stanzaliving.api.service.UserService;

@RestController
public class ElectricityMeterReadingsRestContoller {

	@Autowired
	ElectricityMeterDetailsService electricityMeterDetailsService;

	@Autowired
	ElectricityMeterSubCategoryService electricityMeterSubCategoryService;

	@Autowired
	UserService userService;

	@Autowired
	ElectricityMeterReadingsService electricityMeterReadingsService;

	@Autowired
	ElectricityMeterReadingImagesService electricityMeterReadingImagesService;

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
			@RequestParam(value = "last", required = false, defaultValue = "false") boolean last) {
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
	@RequestMapping(value = "/electricityMeterReadings", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<ElectricityMeterReadings>> saveElectricityMeterReadingsForMeter(
			@RequestBody List<HashMap<String, Object>> request) {
		List<ElectricityMeterReadings> electricityMeterReadingsList = new ArrayList<>();
		for (HashMap<String, Object> entry : request) {
			System.out.println(entry);
			Integer meterDetailsId = (Integer) entry.get("id");
			String readingKwh = (String) entry.get("readingKwh");
			String readingKwah = (String) entry.get("readingKwah");
			String readingDate = (String) entry.get("readingDate");
			List<String> imgUrls = (List<String>) entry.get("imgUrls");
			ElectricityMeterDetails electricityMeterDetails = electricityMeterDetailsService.findById(meterDetailsId);
			if (electricityMeterDetails != null) {
				ElectricityMeterReadings electricityMeterReadings = electricityMeterReadingsService
						.save(electricityMeterDetails, readingKwah, readingKwh, readingDate);
				electricityMeterReadingsList.add(electricityMeterReadings);
				if (imgUrls != null && !imgUrls.isEmpty()) {
					electricityMeterReadingImagesService.save(electricityMeterReadings, imgUrls);
				}
			}

		}
		return new ResponseEntity<List<ElectricityMeterReadings>>(electricityMeterReadingsList, HttpStatus.OK);
	}

}
