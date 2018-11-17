package com.stanzaliving.api.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stanzaliving.api.model.ElectricityMeterCategory;
import com.stanzaliving.api.model.ElectricityMeterSubCategory;
import com.stanzaliving.api.service.ElectricityMeterCategoryService;
import com.stanzaliving.api.service.ElectricityMeterSubCategoryService;

@RestController
public class ElectricityMeterSubCategoryRestContoller {

	@Autowired
	ElectricityMeterSubCategoryService electricityMeterSubCategoryService;

	@Autowired
	ElectricityMeterCategoryService electricityMeterCategoryService;

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
	public ResponseEntity<List<ElectricityMeterSubCategory>> getElectricityMeterCategoriesForelectricityMeterCategory(
			@PathVariable("id") int id) {
		ElectricityMeterCategory electricityMeterCategory = electricityMeterCategoryService.findById(id);
		if (electricityMeterCategory == null) {
			System.out.println("electricityMeterCategory with id " + id + " not found");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		List<ElectricityMeterSubCategory> electricityMeterSubCategories = electricityMeterSubCategoryService
				.findAllElectricityMeterSubCategoriesForElectricityMeterCategory(electricityMeterCategory);
		return new ResponseEntity<List<ElectricityMeterSubCategory>>(electricityMeterSubCategories, HttpStatus.OK);
	}

}
