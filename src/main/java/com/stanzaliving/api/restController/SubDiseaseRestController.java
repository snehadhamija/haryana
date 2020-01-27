/**
 * @author nipunaggarwal
 *
 */
package com.stanzaliving.api.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.util.CollectionUtils;
import com.stanzaliving.api.model.SubDisease;
import com.stanzaliving.api.service.SubDiseaseService;
import com.stanzaliving.api.util.SubDiseaseUtil;

/**
 * @author nipunaggarwal
 *
 */
@RestController
@RequestMapping(value = {
		"/subDisease",
		"/subdisease"
})
public class SubDiseaseRestController {

	@Autowired
	private SubDiseaseService subDiseaseService;

	@Autowired
	private SubDiseaseUtil subDiseaseUtil;

	// ----- Get sub diseases -----
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<Object> findAllSubDiseases(
			@RequestParam(name = "isActive", required = false, defaultValue = "true") boolean isActive) {
		List<SubDisease> subDiseases = subDiseaseService.findAllActiveSubDiseases(isActive);
		return !CollectionUtils.isNullOrEmpty(subDiseases)
				? new ResponseEntity<Object>(subDiseaseUtil.getSortedSubDiseaseResponseDtoList(subDiseases), HttpStatus.OK)
				: new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
	}

	// ----- Get sub diseases for disease -----
	@RequestMapping(value = "/{diseaseId}", method = RequestMethod.GET)
	public ResponseEntity<Object> findAllSubDiseasesForDisease(
			@PathVariable(name = "diseaseId", required = true) Integer diseaseId,
			@RequestParam(name = "isActive", required = false, defaultValue = "true") boolean isActive) {
		List<SubDisease> subDiseases = subDiseaseService.findAllSubDiseasesForDisease(isActive, diseaseId);
		return !CollectionUtils.isNullOrEmpty(subDiseases)
				? new ResponseEntity<Object>(subDiseaseUtil.getSortedSubDiseaseResponseDtoList(subDiseases), HttpStatus.OK)
				: new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
	}

}
