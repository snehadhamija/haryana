package com.stanzaliving.api.restController;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.stanzaliving.api.model.LuggageHostel;
import com.stanzaliving.api.service.LuggageHostelService;
import com.stanzaliving.api.util.LuggageActivityUtil;

@RestController
@RequestMapping("/luggageHostel")
public class LuggageHostelRestController {
	private static final Logger logger = LoggerFactory.getLogger(LuggageHostelRestController.class);

	@Autowired
	LuggageHostelService luggageHostelService;

	@Autowired
	LuggageActivityUtil luggageActivityUtil;

	// ----- Retrieve all luggage hostels -----
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<Object> findAllLuggageHostels() {
		List<LuggageHostel> luggageHostels = luggageHostelService.findAllLuggageHostels();
		if (luggageHostels.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Object>(luggageHostels, HttpStatus.OK);
	}

	// ----- Retrieve luggageHostel by id -----
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Object> findLuggageHostelById(@PathVariable("id") int id) {
		LuggageHostel luggageHostel = luggageHostelService.findById(id);
		if (luggageHostel == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Object>(luggageHostel, HttpStatus.OK);
	}

	// ----- Check is luggage activated for current hostel -----
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<Object> findIfLuggageModuleActivatedForCurrentHostel(HttpServletRequest httpRequest) {
		if (luggageActivityUtil.checkIfLuggageActivatedForHostel(httpRequest)) {
			return new ResponseEntity<>("Activated !", HttpStatus.OK);
		}
		return new ResponseEntity<>("Not Activated !", HttpStatus.NOT_FOUND);
	}

	// ----- Check is luggage activated for a given hostel -----
	@RequestMapping(value = "/hostel", method = RequestMethod.GET)
	public ResponseEntity<Object> findIfLuggageModuleActivatedForGivenHostel(HttpServletRequest httpRequest,
			@RequestParam(value = "hostelId", required = true) Integer hostelId) {
		boolean isHostelActivated = luggageHostelService.findIfLuggageModuleActivatedForCurrentHostel(hostelId);
		if (isHostelActivated) {
			return new ResponseEntity<>("Activated !", HttpStatus.OK);
		}
		return new ResponseEntity<>("Not Activated !", HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = "/hostel/{hostelId}", method = RequestMethod.POST)
	public ResponseEntity<LuggageHostel> addLuggageHostelMap (@PathVariable("hostelId") int hostelId) {
		try {
			if (luggageHostelService.isLuggageActivatedForHostel(hostelId)) {
				return new ResponseEntity<LuggageHostel>(HttpStatus.CONFLICT);
			}
			LuggageHostel luggageHostel = new LuggageHostel();
			luggageHostel.setHostelId(hostelId);
			luggageHostel.setIsActivated(true);
			luggageHostelService.save(luggageHostel);
			return new ResponseEntity<LuggageHostel>(luggageHostel, HttpStatus.CREATED);
		} catch (Exception e) {
			logger.info(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<LuggageHostel>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
