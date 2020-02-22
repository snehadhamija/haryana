/**
 * @author nipunaggarwal
 *
 */
package com.haryanaindustries.api.dto;

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
public class ProductResponseDTO {

	private Integer productId;
	private String productName;
	private Boolean isActive;
	private Integer sequenceId;
	private String imgurl;

}
