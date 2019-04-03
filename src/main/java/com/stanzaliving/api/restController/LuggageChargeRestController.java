package com.stanzaliving.api.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stanzaliving.api.model.LuggageCharge;
import com.stanzaliving.api.service.LuggageChargeService;

@RestController
@RequestMapping("/luggageCharge")
public class LuggageChargeRestController {

	@Autowired
	LuggageChargeService luggageChargeService;

	// ----- Retrieve all luggage charges -----
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<Object> findAllLuggageCharges() {
		List<LuggageCharge> luggageCharges = luggageChargeService.findAllLuggageCharges();
		if (luggageCharges.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Object>(luggageCharges, HttpStatus.OK);
	}

	// ----- Retrieve luggageCharge by id -----
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Object> findLuggageChargeById(@PathVariable("id") int id) {
		LuggageCharge luggageCharge = luggageChargeService.findById(id);
		if (luggageCharge == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Object>(luggageCharge, HttpStatus.OK);
	}
}
