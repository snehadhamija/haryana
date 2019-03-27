package com.stanzaliving.api.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stanzaliving.api.model.LuggageHostel;
import com.stanzaliving.api.service.LuggageHostelService;

@RestController
public class LuggageHostelRestController {

	@Autowired
	LuggageHostelService luggageHostelService;

	// ----- Retrieve all luggage hostels -----
	@RequestMapping(value = "/luggageHostel", method = RequestMethod.GET)
	public ResponseEntity<Object> findAllLuggageHostels() {
		List<LuggageHostel> luggageHostels = luggageHostelService.findAllLuggageHostels();
		if (luggageHostels.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Object>(luggageHostels, HttpStatus.OK);
	}

	// ----- Retrieve luggageHostel by id -----
	@RequestMapping(value = "/luggageHostel/{id}", method = RequestMethod.GET)
	public ResponseEntity<Object> findLuggageHostelById(@PathVariable("id") int id) {
		LuggageHostel luggageHostel = luggageHostelService.findById(id);
		if (luggageHostel == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Object>(luggageHostel, HttpStatus.OK);
	}
}
