package com.stanzaliving.api.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stanzaliving.api.model.LuggagePaymentMode;
import com.stanzaliving.api.service.LuggagePaymentModeService;

@RestController
@RequestMapping("/luggagePaymentMode")
public class LuggagePaymentModeRestController {

	@Autowired
	LuggagePaymentModeService luggagePaymentModeService;

	// ----- Retrieve all luggage payment modes -----
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<Object> findAllLuggagePaymentModes() {
		List<LuggagePaymentMode> luggagePaymentModes = luggagePaymentModeService.findAllLuggagePaymentModes();
		if (luggagePaymentModes.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Object>(luggagePaymentModes, HttpStatus.OK);
	}

	// ----- Retrieve luggagePaymentMode by id -----
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Object> findLuggagePaymentModeById(@PathVariable("id") int id) {
		LuggagePaymentMode luggagePaymentMode = luggagePaymentModeService.findById(id);
		if (luggagePaymentMode == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Object>(luggagePaymentMode, HttpStatus.OK);
	}

	// ----- Retrieve all active luggage payment modes -----
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<Object> findAllActiveLuggagePaymentModes() {
		List<LuggagePaymentMode> activeLuggagePaymentModes = luggagePaymentModeService
				.findAllActiveLuggagePaymentModes();
		if (activeLuggagePaymentModes.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Object>(activeLuggagePaymentModes, HttpStatus.OK);
	}
}
