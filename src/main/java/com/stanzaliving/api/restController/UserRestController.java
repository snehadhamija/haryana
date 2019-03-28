package com.stanzaliving.api.restController;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.stanzaliving.api.dto.UserDto;
import com.stanzaliving.api.service.SpringRestClientService;

@RestController
public class UserRestController {

	@Autowired
	SpringRestClientService springRestClientService;

	// ----- Retrieve user info -----
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public ResponseEntity<Object> findUserByMobileNumber(HttpServletRequest httpRequest,
			@RequestParam(value = "mobileNumber", required = false) String mobileNumber) {
		UserDto userDto = null;
		if (mobileNumber == null) {
			userDto = springRestClientService.getUserDto(httpRequest);
		} else {
			userDto = springRestClientService.getUserDtoForOtherUser(httpRequest, mobileNumber);
		}
		if (userDto == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Object>(userDto, HttpStatus.OK);
	}
}
