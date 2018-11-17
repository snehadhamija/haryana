package com.stanzaliving.api.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stanzaliving.api.model.ElectricityMeterCategory;
import com.stanzaliving.api.service.ElectricityMeterCategoryService;

@RestController
public class ElectricityMeterCategoryRestContoller {

	@Autowired
	ElectricityMeterCategoryService electricityMeterCategoryService;

	@RequestMapping(value = { "/electricityMeterCategory" }, method = RequestMethod.GET)
	public ResponseEntity<List<ElectricityMeterCategory>> getElectricityMeterCategories() {
		List<ElectricityMeterCategory> electricityMeterCategories = electricityMeterCategoryService
				.findAllElectricityMeterCategories();
		return new ResponseEntity<List<ElectricityMeterCategory>>(electricityMeterCategories, HttpStatus.OK);
	}

}
