/**
 * @author nipunaggarwal
 *
 */
package com.haryanaindustries.api.dto;

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
public class ProductVariantResponseDTO {

	private Integer productVariantId;
	private String productVariantName;
	private Boolean isActive;
	private Integer sequenceId;
	private String imgurl;
	@JsonProperty(value = "products", access = JsonProperty.Access.WRITE_ONLY)
	private ProductResponseDTO productResponseDTO;

}
