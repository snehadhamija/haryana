/**
 * @author nipunaggarwal
 *
 */
package com.stanzaliving.api.dto;

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
public class SubDiseaseResponseDTO {

	private Integer subDiseaseId;
	private String subDiseaseName;
	private Boolean isActive;
	private Integer sequenceId;
	private String imgurl;
	@JsonProperty(value = "disease", access = JsonProperty.Access.WRITE_ONLY)
	private DiseaseResponseDTO diseaseResponseDTO;

}
