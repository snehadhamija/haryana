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
@RequestMapping("/user")
public class UserRestController {

	@Autowired
	SpringRestClientService springRestClientService;

	// ----- Retrieve user info -----
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<Object> findUserByMobileNumber(HttpServletRequest httpRequest,
			@RequestParam(value = "mobileNumber", required = true) String mobileNumber) {
		UserDto currentUserDto = springRestClientService.getUserDto(httpRequest);
		UserDto userDto = springRestClientService.getUserDtoForOtherUser(httpRequest, mobileNumber);
		if (userDto == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		if (userDto.getHostelID() != currentUserDto.getHostelID()) {
			return new ResponseEntity<Object>(
					"User with mobile number " + mobileNumber + " doesn't belong to current hostel!",
					HttpStatus.CONFLICT);
		}
		return new ResponseEntity<Object>(userDto, HttpStatus.OK);
	}
}
