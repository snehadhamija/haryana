package com.haryanaindustries.api.util;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class BaseUtil {

	// @Autowired
	// public static UserService userService;

	// ------------------- Get Current active user
	// --------------------------------------------------------
	public static String getPrincipal() {
		String userName = null;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			String currentUserName = authentication.getName();
			System.out.println(currentUserName);
		}
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		System.out.println(authentication.getName());
		System.out.println(authentication);
		System.out.println(principal);
		if (principal instanceof UserDetails) {
			System.out.println("11111");
			userName = ((UserDetails) principal).getUsername();
		} else {
			System.out.println("22222");
			userName = principal.toString();
		}
		System.out.println(userName);
		return userName; // in our project username is Mobile number
	}

}
