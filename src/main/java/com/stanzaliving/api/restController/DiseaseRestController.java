/**
 * @author nipunaggarwal
 *
 */
package com.stanzaliving.api.restController;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.util.CollectionUtils;
import com.stanzaliving.api.dto.DiseaseResponseDTO;
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
		if (!CollectionUtils.isNullOrEmpty(diseases)) {
			List<DiseaseResponseDTO> diseaseResponseDtos =
					diseases
							.stream()
							.map(disease -> diseaseUtil.convertDiseaseEntityToDiseaseResponseDto(disease))
							.collect(Collectors.toList());
			return new ResponseEntity<Object>(diseaseResponseDtos, HttpStatus.OK);
		}
		return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
	}

}
