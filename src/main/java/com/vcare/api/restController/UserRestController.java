package com.vcare.api.restController;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vcare.api.service.UserService;

@RestController
@RequestMapping("/user")
public class UserRestController {

	@Autowired
	UserService userService;

	// ----- Find all users -----
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<Object> findAllUsers(HttpServletRequest request, HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();
		if (Objects.nonNull(cookies)) {
			System.out.println(Arrays.stream(cookies)
					.map(c -> c.getName() + "=" + c.getValue()).collect(Collectors.joining(", ")));
		}
//		} else {
//			Cookie cookie = new Cookie("username", "foo");
//			response.addCookie(cookie);
//		}
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
}
