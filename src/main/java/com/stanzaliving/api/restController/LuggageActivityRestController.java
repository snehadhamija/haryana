package com.stanzaliving.api.restController;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stanzaliving.api.dto.UserDto;
import com.stanzaliving.api.model.LuggageActivity;
import com.stanzaliving.api.service.LuggageActivityService;
import com.stanzaliving.api.service.LuggageHostelService;
import com.stanzaliving.api.service.SpringRestClientService;

@RestController
public class LuggageActivityRestController {

	@Autowired
	LuggageActivityService luggageActivityService;

	@Autowired
	SpringRestClientService springRestClientService;

	@Autowired
	LuggageHostelService luggageHostelService;

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
	public ResponseEntity<Object> findAllActiveLuggageActivities(HttpServletRequest httpRequest) {
		UserDto userDto = springRestClientService.getUserDto(httpRequest);
		Integer hostelId = userDto.getHostelID();
		boolean isHostelActivated = luggageHostelService.findIfLuggageModuleActivatedForCurrentHostel(hostelId);
		if (!isHostelActivated) {
			return new ResponseEntity<>("Luggage service not activated for hostel!", HttpStatus.CONFLICT);
		}
		List<LuggageActivity> activeLuggageActivities = luggageActivityService.findAllActiveLuggageActivities();
		if (activeLuggageActivities.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Object>(activeLuggageActivities, HttpStatus.OK);
	}
}
