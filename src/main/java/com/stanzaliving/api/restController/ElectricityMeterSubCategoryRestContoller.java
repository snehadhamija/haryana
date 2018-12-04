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

import com.stanzaliving.api.dto.ElectricityMeterSubCategoryDto;
import com.stanzaliving.api.dto.UserDto;
import com.stanzaliving.api.model.ElectricityMeterCategory;
import com.stanzaliving.api.model.ElectricityMeterDetails;
import com.stanzaliving.api.model.ElectricityMeterSubCategory;
import com.stanzaliving.api.service.ElectricityMeterCategoryService;
import com.stanzaliving.api.service.ElectricityMeterDetailsService;
import com.stanzaliving.api.service.ElectricityMeterSubCategoryService;
import com.stanzaliving.api.service.SpringRestClientService;

@RestController
public class ElectricityMeterSubCategoryRestContoller {

	@Autowired
	ElectricityMeterSubCategoryService electricityMeterSubCategoryService;

	@Autowired
	ElectricityMeterCategoryService electricityMeterCategoryService;

	@Autowired
	ElectricityMeterDetailsService electricityMeterDetailsService;

	@Autowired
	SpringRestClientService springRestClientService;

	// -------------------Retrieve All electricityMeterSubCategories
	@RequestMapping(value = { "/electricityMeterSubCategory" }, method = RequestMethod.GET)
	public ResponseEntity<List<ElectricityMeterSubCategory>> getElectricityMeterCategories() {
		List<ElectricityMeterSubCategory> electricityMeterSubCategories = electricityMeterSubCategoryService
				.findAllElectricityMeterSubCategories();
		return new ResponseEntity<List<ElectricityMeterSubCategory>>(electricityMeterSubCategories, HttpStatus.OK);
	}

	// -------------------Retrieve all
	// electricityMeterSubCategories for an
	// electricityMeterCategory--------------------------------------------------------
	@RequestMapping(value = "/electricityMeterSubCategory/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ElectricityMeterSubCategoryDto>> getElectricityMeterCategoriesForelectricityMeterCategory(
			@PathVariable("id") int id, HttpServletRequest request) {
		ElectricityMeterCategory electricityMeterCategory = electricityMeterCategoryService.findById(id);
		if (electricityMeterCategory == null) {
			System.out.println("electricityMeterCategory with id " + id + " not found");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		List<ElectricityMeterSubCategory> electricityMeterSubCategories = electricityMeterSubCategoryService
				.findAllElectricityMeterSubCategoriesForElectricityMeterCategory(electricityMeterCategory);
		List<ElectricityMeterSubCategoryDto> electricityMeterSubCategoryDtos = new ArrayList<>();
		if (!electricityMeterSubCategories.isEmpty()) {
			UserDto userDto = springRestClientService.getUserDto(request);
			for (ElectricityMeterSubCategory electricityMeterSubCategory : electricityMeterSubCategories) {
				List<ElectricityMeterDetails> electricityMeterDetails = electricityMeterDetailsService
						.findAllElectricityMeterDetailsForSubCategoryInHostel(electricityMeterSubCategory,
								userDto.getHostelID());
				ElectricityMeterSubCategoryDto electricityMeterSubCategoryDto = new ElectricityMeterSubCategoryDto();
				electricityMeterSubCategoryDto.setElectricityMeterSubCategory(electricityMeterSubCategory);
				electricityMeterSubCategoryDto.setCount(electricityMeterDetails.size());
				electricityMeterSubCategoryDtos.add(electricityMeterSubCategoryDto);
			}
		}
		return new ResponseEntity<List<ElectricityMeterSubCategoryDto>>(electricityMeterSubCategoryDtos, HttpStatus.OK);
	}

}
