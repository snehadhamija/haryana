/**
 * @author nipunaggarwal
 *
 */
package com.haryanaindustries.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.haryanaindustries.api.Enum.ContactMode;

/**
 * @author nipunaggarwal
 *
 */
@RestController
@RequestMapping(value = {
		"/contactMode",
		"/contactmode"
})
public class ContactModeContoller {

	// ----- Get contact mode values -----
	@GetMapping(value = "")
	public ResponseEntity<Object> findContactModes() {
		return new ResponseEntity<Object>(ContactMode.values(), HttpStatus.OK);
	}

}
