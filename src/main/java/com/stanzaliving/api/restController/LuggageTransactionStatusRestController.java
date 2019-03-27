package com.stanzaliving.api.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stanzaliving.api.model.LuggageTransactionStatus;
import com.stanzaliving.api.service.LuggageTransactionStatusService;

@RestController
public class LuggageTransactionStatusRestController {

	@Autowired
	LuggageTransactionStatusService luggageTransactionStatusService;

	// ----- Retrieve all luggage transaction statuses -----
	@RequestMapping(value = "/luggageTransactionStatus", method = RequestMethod.GET)
	public ResponseEntity<Object> findAllLuggageTransactionStatuses() {
		List<LuggageTransactionStatus> luggageTransactionStatuses = luggageTransactionStatusService
				.findAllLuggageTransactionStatuses();
		if (luggageTransactionStatuses.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Object>(luggageTransactionStatuses, HttpStatus.OK);
	}

	// ----- Retrieve luggageTransactionStatus by id -----
	@RequestMapping(value = "/luggageTransactionStatus/{id}", method = RequestMethod.GET)
	public ResponseEntity<Object> findLuggageTransactionStatusById(@PathVariable("id") int id) {
		LuggageTransactionStatus luggageTransactionStatus = luggageTransactionStatusService.findById(id);
		if (luggageTransactionStatus == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Object>(luggageTransactionStatus, HttpStatus.OK);
	}
}
