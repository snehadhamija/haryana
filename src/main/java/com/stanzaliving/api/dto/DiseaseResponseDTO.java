/**
 * @author nipunaggarwal
 *
 */
package com.stanzaliving.api.dto;

import java.util.HashSet;
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
public class DiseaseResponseDTO {

	private Integer diseaseId;
	private String diseaseName;
	private Boolean isActive;
	private Integer sequenceId;
	private String imgurl;
	@JsonProperty(value = "productCategories", access = JsonProperty.Access.WRITE_ONLY)
	private Set<ProductCategoryResponseDTO> productCategoryResponseDTOs = new HashSet<ProductCategoryResponseDTO>();

}
