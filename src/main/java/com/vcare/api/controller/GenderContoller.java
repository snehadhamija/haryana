/**
 * @author nipunaggarwal
 *
 */
package com.vcare.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vcare.api.Enum.Gender;

/**
 * @author nipunaggarwal
 *
 */
@RestController
@RequestMapping(value = {
		"/gender"
})
public class GenderContoller {

	// ----- Get gender values -----
	@GetMapping(value = "")
	public ResponseEntity<Object> findAskedQueries() {
		return new ResponseEntity<Object>(Gender.values(), HttpStatus.OK);
	}
}
