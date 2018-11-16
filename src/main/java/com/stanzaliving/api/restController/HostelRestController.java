package com.stanzaliving.api.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stanzaliving.api.model.Hostel;
import com.stanzaliving.api.service.HostelService;

@RestController
public class HostelRestController {

	@Autowired
	HostelService hostelService;

	// -------------------Retrieve All
	// Hostels--------------------------------------------------------
	@RequestMapping(value = "/hostel", method = RequestMethod.GET)
	public ResponseEntity<List<Hostel>> listAllHostels() {
		List<Hostel> hostels = hostelService.findAllHostels();
		if (hostels.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Hostel>>(hostels, HttpStatus.OK);
	}

	// -------------------Retrieve Single
	// Hostel--------------------------------------------------------
	@RequestMapping(value = "/hostel/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Hostel> getHostel(@PathVariable("id") int id) {
		System.out.println("Fetching Hostel with id " + id);
		Hostel hostel = hostelService.findById(id);
		if (hostel == null) {
			System.out.println("Hostel with id " + id + " not found");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Hostel>(hostel, HttpStatus.OK);
	}

}
