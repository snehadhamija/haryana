/**
 * @author nipunaggarwal
 *
 */
package com.stanzaliving.api.dto;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author nipunaggarwal
 *
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class FAQResponseDTO {

	private Integer faqId;
	private String question;
	private Boolean isActive;
	private Integer sequenceId;
	@JsonProperty(value = "subDiseases", access = JsonProperty.Access.WRITE_ONLY)
	private Set<SubDiseaseResponseDTO> subDiseaseResponseDTOs;

}
