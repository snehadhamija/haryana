/**
 * @author nipunaggarwal
 *
 */
package com.stanzaliving.api.restController;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author nipunaggarwal
 *
 */
@RestController
@RequestMapping(value = {
		"/cookie"
})
public class CookieRestController {

	// ----- Read cookie -----
	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public ResponseEntity<Object> findAllUsers(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if (Objects.nonNull(cookies)) {
			System.out.println(Arrays.stream(cookies)
					.map(c -> c.getName() + "=" + c.getValue()).collect(Collectors.joining(", ")));
		}
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

}
