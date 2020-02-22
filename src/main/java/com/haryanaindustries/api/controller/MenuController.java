/**
 * @author nipunaggarwal
 *
 */
package com.haryanaindustries.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.haryanaindustries.api.service.MenuService;

/**
 * @author nipunaggarwal
 *
 */
@RestController
@RequestMapping(value = {
		"/menu"
})
public class MenuController {

	@Autowired
	MenuService menuService;

	// ----- get menu -----
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<Object> getMenus(
			@RequestParam(value = "isActive", required = false) Boolean isActive) {
		return new ResponseEntity<Object>(menuService.findAllMenus(isActive), HttpStatus.OK);
	}

}
