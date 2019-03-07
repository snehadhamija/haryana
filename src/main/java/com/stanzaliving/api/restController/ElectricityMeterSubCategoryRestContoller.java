package com.stanzaliving.api.restController;

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
import com.stanzaliving.api.model.ElectricityMeterCategory;
import com.stanzaliving.api.model.ElectricityMeterSubCategory;
import com.stanzaliving.api.service.ElectricityMeterCategoryService;
import com.stanzaliving.api.service.ElectricityMeterSubCategoryService;
import com.stanzaliving.api.util.ElectricityMeterSubCategoryUtil;

@RestController
public class ElectricityMeterSubCategoryRestContoller {

	@Autowired
	ElectricityMeterSubCategoryService electricityMeterSubCategoryService;

	@Autowired
	ElectricityMeterCategoryService electricityMeterCategoryService;

	@Autowired
	ElectricityMeterSubCategoryUtil electricityMeterSubCategoryUtil;

	// --------Retrieve All electricityMeterSubCategories
	@RequestMapping(value = { "/electricityMeterSubCategory" }, method = RequestMethod.GET)
	public ResponseEntity<List<ElectricityMeterSubCategory>> getElectricityMeterCategories() {
		List<ElectricityMeterSubCategory> electricityMeterSubCategories = electricityMeterSubCategoryService
				.findAllElectricityMeterSubCategories();
		return new ResponseEntity<List<ElectricityMeterSubCategory>>(electricityMeterSubCategories, HttpStatus.OK);
	}

	// ------ Retrieve all
	// electricityMeterSubCategories for an
	// electricityMeterCategory -------
	@RequestMapping(value = "/electricityMeterSubCategory/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ElectricityMeterSubCategoryDto>> getElectricityMeterCategoriesForelectricityMeterCategory(
			@PathVariable("id") int id, HttpServletRequest request) {
		ElectricityMeterCategory electricityMeterCategory = electricityMeterCategoryService.findById(id);
		if (electricityMeterCategory == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		List<ElectricityMeterSubCategoryDto> electricityMeterSubCategoryDtos = electricityMeterSubCategoryUtil
				.createElectricityMeterSubCategoryDtoList(electricityMeterCategory, request);
		return new ResponseEntity<List<ElectricityMeterSubCategoryDto>>(electricityMeterSubCategoryDtos, HttpStatus.OK);
	}

}
