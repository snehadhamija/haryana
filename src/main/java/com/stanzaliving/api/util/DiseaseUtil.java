/**
 * @author nipunaggarwal
 *
 */
package com.stanzaliving.api.util;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.stanzaliving.api.dto.DiseaseResponseDTO;
import com.stanzaliving.api.model.Disease;

/**
 * @author nipunaggarwal
 *
 */
@Component
public class DiseaseUtil {

	public DiseaseResponseDTO convertDiseaseEntityToDiseaseResponseDto(Disease disease) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setAmbiguityIgnored(true);
		return modelMapper.map(disease, DiseaseResponseDTO.class);
	}

}
