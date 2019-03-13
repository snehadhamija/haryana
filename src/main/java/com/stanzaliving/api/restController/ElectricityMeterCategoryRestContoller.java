package com.stanzaliving.api.restController;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stanzaliving.api.model.ElectricityMeterCategory;
import com.stanzaliving.api.model.ElectricityMeterDetails;
import com.stanzaliving.api.service.ElectricityMeterCategoryService;
import com.stanzaliving.api.util.ElectricityMeterCategoryUtil;
import com.stanzaliving.api.util.ElectricityMeterDetailsUtil;

@RestController
public class ElectricityMeterCategoryRestContoller {

	@Autowired
	ElectricityMeterCategoryService electricityMeterCategoryService;

	@Autowired
	ElectricityMeterCategoryUtil electricityMeterCategoryUtil;

	@Autowired
	ElectricityMeterDetailsUtil electricityMeterDetailsUtil;

	@RequestMapping(value = { "/electricityMeterCategory" }, method = RequestMethod.GET)
	public ResponseEntity<List<ElectricityMeterCategory>> getElectricityMeterCategories() {
		List<ElectricityMeterCategory> electricityMeterCategories = electricityMeterCategoryService
				.findAllElectricityMeterCategories();
		return new ResponseEntity<List<ElectricityMeterCategory>>(electricityMeterCategories, HttpStatus.OK);
	}

	@RequestMapping(value = { "/electricityMeterCategory/hostel" }, method = RequestMethod.GET)
	public ResponseEntity<Object> getElectricityMeterCategoriesForHostel(HttpServletRequest request) {
		List<ElectricityMeterDetails> electricityMeterDetails = electricityMeterDetailsUtil
				.getElectricityMeterDetailsInHostel(request);
		if (electricityMeterDetails == null) {
			return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
		}
		List<ElectricityMeterCategory> electricityMeterCategories = electricityMeterCategoryUtil
				.createElectricityMeterCategoryList(electricityMeterDetails);
		return new ResponseEntity<Object>(electricityMeterCategories, HttpStatus.OK);
	}
}
