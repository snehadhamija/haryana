package com.stanzaliving.api.restController;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stanzaliving.api.dto.ElectricityMeterReadingsDto;
import com.stanzaliving.api.model.ElectricityMeterDetails;
import com.stanzaliving.api.model.ElectricityMeterReadings;
import com.stanzaliving.api.model.ElectricityMeterSubCategory;
import com.stanzaliving.api.model.User;
import com.stanzaliving.api.service.ElectricityMeterDetailsService;
import com.stanzaliving.api.service.ElectricityMeterReadingsService;
import com.stanzaliving.api.service.ElectricityMeterSubCategoryService;
import com.stanzaliving.api.service.UserService;
import com.stanzaliving.api.util.BaseUtil;

@RestController
public class ElectricityMeterDetailsRestContoller {

	@Autowired
	ElectricityMeterDetailsService electricityMeterDetailsService;

	@Autowired
	ElectricityMeterSubCategoryService electricityMeterSubCategoryService;

	@Autowired
	UserService userService;

	@Autowired
	ElectricityMeterReadingsService electricityMeterReadingsService;

	// -------------------Retrieve All electricityMeterDetails
	@RequestMapping(value = { "/electricityMeterDetails" }, method = RequestMethod.GET)
	public ResponseEntity<List<ElectricityMeterDetails>> getElectricityMeterDetails() {
		List<ElectricityMeterDetails> electricityMeterDetails = electricityMeterDetailsService
				.findAllElectricityMeterDetails();
		return new ResponseEntity<List<ElectricityMeterDetails>>(electricityMeterDetails, HttpStatus.OK);
	}

	// -------------------Retrieve all
	// electricityMeterDetails for an
	// electricityMeterSubCategory in a
	// Hostel--------------------------------------------------------
	@RequestMapping(value = "/electricityMeterDetails/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ElectricityMeterReadingsDto>> getElectricityMeterDetailsForelectricityMeterSubCategoryInHostel(
			@PathVariable("id") int id) {
		ElectricityMeterSubCategory electricityMeterSubCategory = electricityMeterSubCategoryService.findById(id);
		String currentUser = BaseUtil.getPrincipal();
		User user = userService.findByMobileNumber(currentUser);
		if (electricityMeterSubCategory == null) {
			System.out.println("electricityMeterSubCategory with id " + id + " not found");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		List<ElectricityMeterDetails> electricityMeterDetails = electricityMeterDetailsService
				.findAllElectricityMeterDetailsForSubCategoryInHostel(electricityMeterSubCategory, user.getHostel());
		List<ElectricityMeterReadingsDto> electricityMeterReadingsDtos = new ArrayList<>();
		if (!electricityMeterDetails.isEmpty()) {
			for (ElectricityMeterDetails ed : electricityMeterDetails) {
				ElectricityMeterReadings electricityMeterReadings = electricityMeterReadingsService
						.findLastElectricityMeterReadingsForMeter(ed);
				if (electricityMeterReadings != null) {
					ElectricityMeterReadingsDto electricityMeterReadingsDto = new ElectricityMeterReadingsDto();
					electricityMeterReadingsDto.setElectricityMeterDetails(ed);
					electricityMeterReadingsDto.setLastReadingdate(electricityMeterReadings.getReadingDate());
					electricityMeterReadingsDto.setLastReadingKwah(electricityMeterReadings.getReadingKwah());
					electricityMeterReadingsDto.setLastReadingKwh(electricityMeterReadings.getReadingKwh());
					electricityMeterReadingsDtos.add(electricityMeterReadingsDto);
				}
			}
		}
		return new ResponseEntity<List<ElectricityMeterReadingsDto>>(electricityMeterReadingsDtos, HttpStatus.OK);
	}

}
