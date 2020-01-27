/**
 * @author nipunaggarwal
 *
 */
package com.stanzaliving.api.util;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.stanzaliving.api.dto.SubDiseaseResponseDTO;
import com.stanzaliving.api.model.SubDisease;
import com.stanzaliving.api.service.SubDiseaseService;

/**
 * @author nipunaggarwal
 *
 */
@Component
public class SubDiseaseUtil {

	@Autowired
	private SubDiseaseService subDiseaseService;

	public Comparator<SubDiseaseResponseDTO> compareBySequenceId() {
		return (SubDiseaseResponseDTO dto1, SubDiseaseResponseDTO dto2) -> dto1.getSequenceId().compareTo(dto2.getSequenceId());
	}

	public SubDiseaseResponseDTO convertSubDiseaseEntityToSubDiseaseResponseDto(SubDisease subDisease) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setAmbiguityIgnored(true);
		return modelMapper.map(subDisease, SubDiseaseResponseDTO.class);
	}

	public List<SubDiseaseResponseDTO> getSortedSubDiseaseResponseDtoList(List<SubDisease> subDiseases) {
		List<SubDiseaseResponseDTO> subDiseaseResponseDtos =
				subDiseases
						.stream()
						.map(subDisease -> convertSubDiseaseEntityToSubDiseaseResponseDto(subDisease))
						.collect(Collectors.toList());
		Collections.sort(subDiseaseResponseDtos, compareBySequenceId());
		return subDiseaseResponseDtos;
	}

	public SubDisease getSubDisease(Integer subDiseaseId) {
		SubDisease subDisease = subDiseaseService.findById(subDiseaseId);
		return Objects.isNull(subDisease) ? null : subDisease;
	}

}
