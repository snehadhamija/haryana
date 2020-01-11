/**
 * @author nipunaggarwal
 *
 */
package com.stanzaliving.api.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.util.CollectionUtils;
import com.stanzaliving.api.model.Disease;
import com.stanzaliving.api.service.DiseaseService;
import com.stanzaliving.api.util.DiseaseUtil;

/**
 * @author nipunaggarwal
 *
 */
@RestController
@RequestMapping(value = {
		"/disease"
})
public class DiseaseRestController {

	@Autowired
	private DiseaseService diseaseService;

	@Autowired
	private DiseaseUtil diseaseUtil;

	// ----- Get diseases -----
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<Object> findAllConditions() {
		List<Disease> diseases = diseaseService.findAllDiseases();
		return !CollectionUtils.isNullOrEmpty(diseases)
				? new ResponseEntity<Object>(diseaseUtil.getSortedDiseaseResponseDtoList(diseases), HttpStatus.OK)
				: new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
	}

}
