package com.stanzaliving.api.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stanzaliving.api.model.LuggageTransactionDetail;
import com.stanzaliving.api.service.LuggageTransactionDetailService;

@RestController
@RequestMapping("/luggageTransactionDetail")
public class LuggageTransactionDetailRestController {

	@Autowired
	LuggageTransactionDetailService luggageTransactionDetailService;

	// ----- Retrieve all luggage transaction details -----
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<Object> findAllLuggageTransactionDetails() {
		List<LuggageTransactionDetail> luggageTransactionDetails = luggageTransactionDetailService
				.findAllLuggageTransactionDetails();
		if (luggageTransactionDetails.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Object>(luggageTransactionDetails, HttpStatus.OK);
	}

	// ----- Retrieve luggageTransactionDetail by id -----
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Object> findLuggageTransactionDetailById(@PathVariable("id") int id) {
		LuggageTransactionDetail luggageTransactionDetail = luggageTransactionDetailService.findById(id);
		if (luggageTransactionDetail == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Object>(luggageTransactionDetail, HttpStatus.OK);
	}
}
