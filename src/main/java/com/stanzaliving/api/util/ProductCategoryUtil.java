/**
 * @author nipunaggarwal
 *
 */
package com.stanzaliving.api.util;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.stanzaliving.api.dto.ProductCategoryResponseDTO;
import com.stanzaliving.api.model.Disease;
import com.stanzaliving.api.model.ProductCategory;
import com.stanzaliving.api.service.DiseaseService;
import com.stanzaliving.api.service.ProductCategoryService;

/**
 * @author nipunaggarwal
 *
 */
@Component
public class ProductCategoryUtil {
	
	@Autowired
	private ProductCategoryService productCategoryService;
	
	@Autowired
	private DiseaseService diseaseService;
	
	public Comparator<ProductCategoryResponseDTO> compareBySequenceId() {
		return (ProductCategoryResponseDTO dto1, ProductCategoryResponseDTO dto2) -> dto1.getSequenceId().compareTo(dto2.getSequenceId());
	}

	public ProductCategoryResponseDTO convertProductCategoryEntityToProductCategoryResponseDto(ProductCategory productCategory) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setAmbiguityIgnored(true);
		return modelMapper.map(productCategory, ProductCategoryResponseDTO.class);
	}

	public List<ProductCategoryResponseDTO> getSortedProductCategoryResponseDtoList(Set<ProductCategory> productCategories) {
		List<ProductCategoryResponseDTO> productCategoryResponseDTOs =
				productCategories
						.stream()
						.map(productCategory -> convertProductCategoryEntityToProductCategoryResponseDto(productCategory))
						.collect(Collectors.toList());
		Collections.sort(productCategoryResponseDTOs, compareBySequenceId());
		return productCategoryResponseDTOs;
	}
	
	public Set<ProductCategory> getProductCategorySet(Integer diseaseId) {
		Set<ProductCategory> productCategories = new HashSet<ProductCategory>();
		if (Objects.isNull(diseaseId)) {
			productCategories = new HashSet<ProductCategory>(productCategoryService.findAllProductCategories());
		} else {
			Disease disease = diseaseService.findById(diseaseId);
			return Objects.isNull(disease) ? null : disease.getProductCategories();
		}
		return productCategories;
	}
}