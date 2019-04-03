package com.stanzaliving.api.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.stanzaliving.api.model.LuggageLifecycle;
import com.stanzaliving.api.model.LuggageTransactionDetail;
import com.stanzaliving.api.service.LuggageLifecycleService;
import com.stanzaliving.api.service.LuggageTransactionDetailService;

@RestController
@RequestMapping("/luggageLifecycle")
public class LuggageLifecycleRestController {

	@Autowired
	LuggageLifecycleService luggageLifecycleService;

	@Autowired
	LuggageTransactionDetailService luggageTransactionDetailService;

	// ----- Retrieve luggageLifecycle by id -----
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Object> findLuggageLifecycleById(@PathVariable("id") int id) {
		LuggageLifecycle luggageLifecycle = luggageLifecycleService.findById(id);
		if (luggageLifecycle == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Object>(luggageLifecycle, HttpStatus.OK);
	}

	// ----- Retrieve luggage lifecyle entry for luggage transaction detail
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<Object> findLuggageLifecycleForLuggageTransactionDetail(
			@RequestParam(value = "luggageTransactionDetailId", required = false) Integer luggageTransactionDetailId,
			@RequestParam(value = "luggageId", required = false) String luggageId) {
		if (luggageTransactionDetailId != null) {
			LuggageTransactionDetail luggageTransactionDetail = luggageTransactionDetailService
					.findById(luggageTransactionDetailId);
			LuggageLifecycle luggageLifecycle = luggageLifecycleService
					.findLuggageLifecycleForLuggageTransactionDetail(luggageTransactionDetail);
			return new ResponseEntity<Object>(luggageLifecycle, HttpStatus.OK);
		} else if (luggageId != null) {
			List<LuggageLifecycle> luggageLifecycles = luggageLifecycleService
					.findAllLuggageLifecyclesForLuggageId(luggageId);
			return new ResponseEntity<Object>(luggageLifecycles, HttpStatus.OK);
		} else {
			List<LuggageLifecycle> luggageLifecycles = luggageLifecycleService.findAllLuggageLifecycles();
			return new ResponseEntity<Object>(luggageLifecycles, HttpStatus.OK);
		}
	}
}
