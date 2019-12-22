package com.stanzaliving.api.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stanzaliving.api.service.UserService;

@RestController
@RequestMapping("/user")
public class UserRestController {

	@Autowired
	UserService userService;

	// ----- Find all users -----
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<Object> findAllUsers() {
		return new ResponseEntity<Object>(userService.findAllUsers(), HttpStatus.OK);
	}
}
