package com.stanzaliving.api.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stanzaliving.api.model.LuggageCategory;
import com.stanzaliving.api.service.LuggageCategoryService;

@RestController
@RequestMapping("/luggageCategory")
public class LuggageCategoryRestController {

	@Autowired
	LuggageCategoryService luggageCategoryService;

	// ----- Retrieve all luggage Categories -----
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<Object> findAllLuggageCategories() {
		List<LuggageCategory> luggageCategories = luggageCategoryService.findAllLuggageCategories();
		if (luggageCategories.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Object>(luggageCategories, HttpStatus.OK);
	}

	// ----- Retrieve luggageCategory by id -----
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Object> findLuggageCategoryById(@PathVariable("id") int id) {
		LuggageCategory luggageCategory = luggageCategoryService.findById(id);
		if (luggageCategory == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Object>(luggageCategory, HttpStatus.OK);
	}

	// ----- Retrieve all active luggage Categories -----
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<Object> findAllActiveLuggageCategories() {
		List<LuggageCategory> activeLuggageCategories = luggageCategoryService.findAllActiveLuggageCategories();
		if (activeLuggageCategories.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Object>(activeLuggageCategories, HttpStatus.OK);
	}
}
