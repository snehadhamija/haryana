package com.stanzaliving.api.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stanzaliving.api.model.LuggageOtpDetail;
import com.stanzaliving.api.service.LuggageOtpDetailService;

@RestController
public class LuggageOtpDetailRestController {

	@Autowired
	LuggageOtpDetailService luggageOtpDetailService;

	// ----- Retrieve all luggage otp details -----
	@RequestMapping(value = "/luggageOtpDetail", method = RequestMethod.GET)
	public ResponseEntity<Object> findAllLuggageOtpDetails() {
		List<LuggageOtpDetail> luggageOtpDetails = luggageOtpDetailService.findAllLuggageOtpDetails();
		if (luggageOtpDetails.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Object>(luggageOtpDetails, HttpStatus.OK);
	}

	// ----- Retrieve luggageOtpDetail by id -----
	@RequestMapping(value = "/luggageOtpDetail/{id}", method = RequestMethod.GET)
	public ResponseEntity<Object> findLuggageOtpDetailById(@PathVariable("id") int id) {
		LuggageOtpDetail luggageOtpDetail = luggageOtpDetailService.findById(id);
		if (luggageOtpDetail == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Object>(luggageOtpDetail, HttpStatus.OK);
	}
}
