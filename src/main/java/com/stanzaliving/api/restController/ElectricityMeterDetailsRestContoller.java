package com.stanzaliving.api.restController;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stanzaliving.api.dto.ElectricityMeterReadingsDto;
import com.stanzaliving.api.dto.UserDto;
import com.stanzaliving.api.model.ElectricityMeterDetails;
import com.stanzaliving.api.model.ElectricityMeterSubCategory;
import com.stanzaliving.api.service.ElectricityMeterDetailsService;
import com.stanzaliving.api.service.ElectricityMeterSubCategoryService;
import com.stanzaliving.api.service.SpringRestClientService;
import com.stanzaliving.api.util.ElectricityMeterDetailsUtil;

@RestController
public class ElectricityMeterDetailsRestContoller {

	@Autowired
	ElectricityMeterDetailsService electricityMeterDetailsService;

	@Autowired
	ElectricityMeterSubCategoryService electricityMeterSubCategoryService;

	@Autowired
	SpringRestClientService springRestClientService;

	@Autowired
	ElectricityMeterDetailsUtil electricityMeterDetailsUtil;

	// -------------------Retrieve All electricityMeterDetails
	@RequestMapping(value = { "/electricityMeterDetails" }, method = RequestMethod.GET)
	public ResponseEntity<List<ElectricityMeterDetails>> getElectricityMeterDetails() {
		List<ElectricityMeterDetails> electricityMeterDetails = electricityMeterDetailsService
				.findAllElectricityMeterDetails();
		return new ResponseEntity<List<ElectricityMeterDetails>>(electricityMeterDetails, HttpStatus.OK);
	}

	// Retrieve all electricityMeterDetails
	// for an electricityMeterSubCategory
	// in a Hostel--------
	@RequestMapping(value = "/electricityMeterDetails/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ElectricityMeterReadingsDto>> getElectricityMeterDetailsForelectricityMeterSubCategoryInHostel(
			@PathVariable("id") int id, HttpServletRequest request) {
		UserDto userDto = springRestClientService.getUserDto(request);
		ElectricityMeterSubCategory electricityMeterSubCategory = electricityMeterSubCategoryService.findById(id);
		if (electricityMeterSubCategory == null) {
			System.out.println("electricityMeterSubCategory with id " + id + " not found");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		List<ElectricityMeterDetails> electricityMeterDetails = electricityMeterDetailsService
				.findAllElectricityMeterDetailsForSubCategoryInHostel(electricityMeterSubCategory,
						userDto.getHostelID());
		List<ElectricityMeterReadingsDto> electricityMeterReadingsDtos = new ArrayList<>();
		if (!electricityMeterDetails.isEmpty()) {
			electricityMeterReadingsDtos = electricityMeterDetailsUtil
					.createElectricityMeterReadingsDtoList(electricityMeterDetails);
		}
		return new ResponseEntity<List<ElectricityMeterReadingsDto>>(electricityMeterReadingsDtos, HttpStatus.OK);
	}

}
