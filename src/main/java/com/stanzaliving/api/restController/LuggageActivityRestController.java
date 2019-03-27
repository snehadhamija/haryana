package com.stanzaliving.api.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stanzaliving.api.model.LuggageActivity;
import com.stanzaliving.api.service.LuggageActivityService;

@RestController
public class LuggageActivityRestController {

	@Autowired
	LuggageActivityService luggageActivityService;

	// ----- Retrieve all luggage activities -----
	@RequestMapping(value = "/luggageActivity", method = RequestMethod.GET)
	public ResponseEntity<Object> findAllLuggageActivities() {
		List<LuggageActivity> luggageActivities = luggageActivityService.findAllLuggageActivities();
		if (luggageActivities.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Object>(luggageActivities, HttpStatus.OK);
	}

	// ----- Retrieve luggageActivity by id -----
	@RequestMapping(value = "/luggageActivity/{id}", method = RequestMethod.GET)
	public ResponseEntity<Object> findLuggageActivityById(@PathVariable("id") int id) {
		LuggageActivity luggageActivity = luggageActivityService.findById(id);
		if (luggageActivity == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Object>(luggageActivity, HttpStatus.OK);
	}

	// ----- Retrieve all active luggage activities -----
	@RequestMapping(value = "/luggageActivity/", method = RequestMethod.GET)
	public ResponseEntity<Object> findAllActiveLuggageActivities() {
		List<LuggageActivity> activeLuggageActivities = luggageActivityService.findAllActiveLuggageActivities();
		if (activeLuggageActivities.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Object>(activeLuggageActivities, HttpStatus.OK);
	}
}
