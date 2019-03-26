package com.stanzaliving.api.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stanzaliving.api.model.Luggage;
import com.stanzaliving.api.service.LuggageService;

@RestController
public class LuggageRestController {

	@Autowired
	LuggageService luggageService;

	// ----- Retrieve luggage by id -----
	@RequestMapping(value = "/luggage/{id}", method = RequestMethod.GET)
	public ResponseEntity<Object> findLuggageById(@PathVariable("id") int id) {
		Luggage luggage = luggageService.findById(id);
		if (luggage == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Object>(luggage, HttpStatus.OK);
	}
}
