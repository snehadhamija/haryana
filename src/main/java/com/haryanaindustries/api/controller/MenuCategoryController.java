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

import com.haryanaindustries.api.service.MenuCategoryService;

/**
 * @author nipunaggarwal
 *
 */
@RestController
@RequestMapping(value = {
		"/menuCategory",
		"/menucategory"
})
public class MenuCategoryController {

	@Autowired
	private MenuCategoryService menuCategoryService;

	// ----- get menu categories -----
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<Object> getMenuCategories(
			@RequestParam(value = "isActive", required = false) Boolean isActive,
			@RequestParam(value = "menuId", required = false) Integer menuId) {
		return new ResponseEntity<Object>(menuCategoryService.findAllMenuCategories(isActive, menuId), HttpStatus.OK);
	}

}
