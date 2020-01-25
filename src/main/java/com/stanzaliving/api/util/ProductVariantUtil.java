/**
 * @author nipunaggarwal
 *
 */
package com.stanzaliving.api.util;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.stanzaliving.api.dto.ProductVariantResponseDTO;
import com.stanzaliving.api.model.ProductVariant;

/**
 * @author nipunaggarwal
 *
 */
@Component
public class ProductVariantUtil {

	public Comparator<ProductVariantResponseDTO> compareBySequenceId() {
		return (ProductVariantResponseDTO dto1, ProductVariantResponseDTO dto2) -> dto1.getSequenceId().compareTo(dto2.getSequenceId());
	}

	public ProductVariantResponseDTO convertProductVariantEntityToProductVariantResponseDto(ProductVariant productVariant) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setAmbiguityIgnored(true);
		return modelMapper.map(productVariant, ProductVariantResponseDTO.class);
	}

	public List<ProductVariantResponseDTO> getSortedProductVariantResponseDtoList(List<ProductVariant> productVariants) {
		List<ProductVariantResponseDTO> productVariantResponseDTOs =
				productVariants
						.stream()
						.map(productVariant -> convertProductVariantEntityToProductVariantResponseDto(productVariant))
						.collect(Collectors.toList());
		Collections.sort(productVariantResponseDTOs, compareBySequenceId());
		return productVariantResponseDTOs;
	}
}
