/**
 * @author nipunaggarwal
 *
 */
package com.vcare.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.util.CollectionUtils;
import com.vcare.api.model.Disease;
import com.vcare.api.util.DiseaseUtil;

/**
 * @author nipunaggarwal
 *
 */
@RestController
@RequestMapping(value = {
		"/disease"
})
public class DiseaseController {

	@Autowired
	private DiseaseUtil diseaseUtil;

	// ----- Get diseases -----
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<Object> findAllDiseases(
			@RequestParam(name = "subDiseaseIds", required = false) List<Integer> subDiseaseIds,
			@RequestParam(name = "productCategoryIds", required = false) List<Integer> productCategoryIds,
			@RequestParam(name = "isActive", required = false, defaultValue = "true") boolean isActive) {
		List<Disease> diseases = diseaseUtil.getDiseases(subDiseaseIds, productCategoryIds, isActive);
		return !CollectionUtils.isNullOrEmpty(diseases)
				? new ResponseEntity<Object>(diseaseUtil.getSortedDiseaseResponseDtoList(diseases), HttpStatus.OK)
				: new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
	}

}
