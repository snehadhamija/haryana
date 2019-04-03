package com.stanzaliving.api.restController;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.stanzaliving.api.dto.UserDto;
import com.stanzaliving.api.model.LuggageStoreRoom;
import com.stanzaliving.api.service.LuggageStoreRoomService;
import com.stanzaliving.api.service.SpringRestClientService;

@RestController
@RequestMapping("/luggageStoreRoom")
public class LuggageStorageRoomRestController {

	@Autowired
	LuggageStoreRoomService luggageStoreRoomService;

	@Autowired
	SpringRestClientService springRestClientService;

	// ----- Retrieve all luggage storage rooms -----
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<Object> findAllLuggageStoreRooms(
			@RequestParam(value = "hostelId", required = false) Integer hostelId) {
		List<LuggageStoreRoom> luggageStoreRooms = new ArrayList<>();
		if (hostelId == null) {
			luggageStoreRooms = luggageStoreRoomService.findAllLuggageStoreRooms();
		} else {
			luggageStoreRooms = luggageStoreRoomService.findLuggageStoreRoomsForHostel(hostelId);
		}
		if (luggageStoreRooms.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Object>(luggageStoreRooms, HttpStatus.OK);
	}

	// ----- Retrieve luggageStoreRoom by id -----
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Object> findLuggageStoreRoomById(@PathVariable("id") int id) {
		LuggageStoreRoom luggageStoreRoom = luggageStoreRoomService.findById(id);
		if (luggageStoreRoom == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Object>(luggageStoreRoom, HttpStatus.OK);
	}

	// ----- Retrieve luggageStoreRoom for hostel -----
	@RequestMapping(value = "/hostel", method = RequestMethod.GET)
	public ResponseEntity<Object> findLuggageStoreRoomForHostel(HttpServletRequest request) {
		UserDto userDto = springRestClientService.getUserDto(request);
		List<LuggageStoreRoom> luggageStoreRooms = new ArrayList<>();
		luggageStoreRooms = luggageStoreRoomService.findLuggageStoreRoomsForHostel(userDto.getHostelID());
		if (luggageStoreRooms.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Object>(luggageStoreRooms, HttpStatus.OK);
	}
}
