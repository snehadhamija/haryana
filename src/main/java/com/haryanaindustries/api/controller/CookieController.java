/**
 * @author nipunaggarwal
 *
 */
package com.haryanaindustries.api.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.haryanaindustries.api.util.CookieUtil;

/**
 * @author nipunaggarwal
 *
 */
@RestController
@RequestMapping(value = {
		"/cookie"
})
public class CookieController {

	@Autowired
	private CookieUtil cookieUtil;

	// ----- Read cookie -----
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<Object> findCookies() {
		String cookieList = cookieUtil.getCookieListFromFilter();
		return Objects.nonNull(cookieList)
				? new ResponseEntity<Object>(cookieList, HttpStatus.OK)
				: new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
	}

}
