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
public class ProductCategoryResponseDTO {

	private Integer productCategoryId;
	private String productCategoryName;
	private Boolean isActive;
	private Integer sequenceId;
	private String imgurl;
	@JsonProperty(value = "products", access = JsonProperty.Access.WRITE_ONLY)
	private Set<ProductResponseDTO> productResponseDtos = new HashSet<ProductResponseDTO>();

}
