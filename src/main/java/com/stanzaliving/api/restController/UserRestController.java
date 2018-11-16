package com.stanzaliving.api.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stanzaliving.api.service.UserService;
import com.stanzaliving.api.util.BaseUtil;

@RestController
public class UserRestController {

	@Autowired
	UserService userService;

	@PreAuthorize("hasAnyRole('ADMIN', 'SUPERADMIN')")
	@RequestMapping(value = { "/feedback/login" }, method = RequestMethod.POST)
	public ResponseEntity feedbackHomePage(ModelMap model) {
		return new ResponseEntity(HttpStatus.OK);
	}

	@RequestMapping(value = { "/getCurrentUser" }, method = RequestMethod.GET)
	public ResponseEntity<String> getCurrentUser() {
		String currentUser = BaseUtil.getPrincipal();
		return new ResponseEntity(currentUser, HttpStatus.OK);
	}

}
