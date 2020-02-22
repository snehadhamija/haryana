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

import com.haryanaindustries.api.service.MenuCatServiceService;

/**
 * @author nipunaggarwal
 *
 */
@RestController
@RequestMapping(value = {
		"/menuCatService",
		"/menucatService",
		"/menuCatservice",
		"/menucatservice"
})
public class MenuCatServiceController {

	@Autowired
	private MenuCatServiceService menuCatServiceService;

	// ----- get menu cat services -----
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<Object> getMenuCatServices(
			@RequestParam(value = "isActive", required = false) Boolean isActive,
			@RequestParam(value = "menuCategoryId", required = false) Integer menuCategoryId) {
		return new ResponseEntity<Object>(menuCatServiceService.findAllMenuCatServices(isActive, menuCategoryId), HttpStatus.OK);
	}
}
