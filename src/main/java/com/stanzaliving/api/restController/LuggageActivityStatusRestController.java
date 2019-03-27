package com.stanzaliving.api.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stanzaliving.api.model.LuggageActivityStatus;
import com.stanzaliving.api.service.LuggageActivityStatusService;

@RestController
public class LuggageActivityStatusRestController {

	@Autowired
	LuggageActivityStatusService luggageActivityStatusService;

	// ----- Retrieve all luggage activity statuses -----
	@RequestMapping(value = "/luggageActivityStatus", method = RequestMethod.GET)
	public ResponseEntity<Object> findAllLuggageActivityStatuses() {
		List<LuggageActivityStatus> luggageActivityStatuses = luggageActivityStatusService
				.findAllLuggageActivityStatuses();
		if (luggageActivityStatuses.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Object>(luggageActivityStatuses, HttpStatus.OK);
	}

	// ----- Retrieve luggageActivityStatus by id -----
	@RequestMapping(value = "/luggageActivityStatus/{id}", method = RequestMethod.GET)
	public ResponseEntity<Object> findLuggageActivityStatusById(@PathVariable("id") int id) {
		LuggageActivityStatus luggageActivityStatus = luggageActivityStatusService.findById(id);
		if (luggageActivityStatus == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Object>(luggageActivityStatus, HttpStatus.OK);
	}
}
