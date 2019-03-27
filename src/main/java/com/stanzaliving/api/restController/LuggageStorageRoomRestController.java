package com.stanzaliving.api.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stanzaliving.api.model.LuggageStoreRoom;
import com.stanzaliving.api.service.LuggageStoreRoomService;

@RestController
public class LuggageStorageRoomRestController {

	@Autowired
	LuggageStoreRoomService luggageStoreRoomService;

	// ----- Retrieve all luggage storage rooms -----
	@RequestMapping(value = "/luggageStoreRoom", method = RequestMethod.GET)
	public ResponseEntity<Object> findAllLuggageStoreRooms() {
		List<LuggageStoreRoom> luggageStoreRooms = luggageStoreRoomService.findAllLuggageStoreRooms();
		if (luggageStoreRooms.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Object>(luggageStoreRooms, HttpStatus.OK);
	}

	// ----- Retrieve luggageStoreRoom by id -----
	@RequestMapping(value = "/luggageStoreRoom/{id}", method = RequestMethod.GET)
	public ResponseEntity<Object> findLuggageStoreRoomById(@PathVariable("id") int id) {
		LuggageStoreRoom luggageStoreRoom = luggageStoreRoomService.findById(id);
		if (luggageStoreRoom == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Object>(luggageStoreRoom, HttpStatus.OK);
	}
}
