/**
 * @author nipunaggarwal
 *
 */
package com.stanzaliving.api.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amazonaws.util.CollectionUtils;
import com.stanzaliving.api.dto.DiseaseResponseDTO;
import com.stanzaliving.api.model.Disease;
import com.stanzaliving.api.service.DiseaseService;

/**
 * @author nipunaggarwal
 *
 */
@Component
public class DiseaseUtil {

	@Autowired
	private DiseaseService diseaseService;

	public Comparator<DiseaseResponseDTO> compareBySequenceId() {
		return (DiseaseResponseDTO dto1, DiseaseResponseDTO dto2) -> dto1.getSequenceId().compareTo(dto2.getSequenceId());
	}

	public DiseaseResponseDTO convertDiseaseEntityToDiseaseResponseDto(Disease disease) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setAmbiguityIgnored(true);
		return modelMapper.map(disease, DiseaseResponseDTO.class);
	}

	public List<DiseaseResponseDTO> getSortedDiseaseResponseDtoList(List<Disease> diseases) {
		List<DiseaseResponseDTO> diseaseResponseDtos =
				diseases
						.stream()
						.map(disease -> convertDiseaseEntityToDiseaseResponseDto(disease))
						.collect(Collectors.toList());
		Collections.sort(diseaseResponseDtos, compareBySequenceId());
		return diseaseResponseDtos;
	}

	public Disease getDisease(Integer diseaseId) {
		Disease disease = diseaseService.findById(diseaseId);
		return Objects.isNull(disease) ? null : disease;
	}

	public List<Disease> getDiseases(List<Integer> subDiseaseIds, List<Integer> productCategoryIds, boolean isActive) {
		List<Disease> diseases = new ArrayList<Disease>();
		if (!CollectionUtils.isNullOrEmpty(subDiseaseIds)) {
			diseases = diseaseService.findAllDiseasesForSubDiseases(isActive, subDiseaseIds);
		} else if (!CollectionUtils.isNullOrEmpty(productCategoryIds)) {
			diseases = diseaseService.findAllDiseasesForProductCategories(isActive, productCategoryIds);
		} else if (Objects.nonNull(isActive)) {
			diseases = diseaseService.findAllActiveDiseases(isActive);
		}
		return diseases;
	}

}
