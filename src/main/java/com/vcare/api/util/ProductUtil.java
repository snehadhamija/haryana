/**
 * @author nipunaggarwal
 *
 */
package com.vcare.api.util;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vcare.api.dto.ProductResponseDTO;
import com.vcare.api.model.Product;
import com.vcare.api.model.ProductCategory;
import com.vcare.api.service.ProductCategoryService;
import com.vcare.api.service.ProductService;

/**
 * @author nipunaggarwal
 *
 */
@Component
public class ProductUtil {

	@Autowired
	private ProductService productService;

	@Autowired
	private ProductCategoryService productCategoryService;

	public Comparator<ProductResponseDTO> compareBySequenceId() {
		return (ProductResponseDTO dto1, ProductResponseDTO dto2) -> dto1.getSequenceId().compareTo(dto2.getSequenceId());
	}

	public ProductResponseDTO convertProductEntityToProductResponseDto(Product product) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setAmbiguityIgnored(true);
		return modelMapper.map(product, ProductResponseDTO.class);
	}

	public List<ProductResponseDTO> getSortedProductResponseDtoList(Set<Product> products) {
		List<ProductResponseDTO> productResponseDtos =
				products
						.stream()
						.map(product -> convertProductEntityToProductResponseDto(product))
						.collect(Collectors.toList());
		Collections.sort(productResponseDtos, compareBySequenceId());
		return productResponseDtos;
	}

	public Set<Product> getProductSet(Integer categoryId) {
		Set<Product> products = new HashSet<Product>();
		if (Objects.isNull(categoryId)) {
			products = new HashSet<Product>(productService.findAllProducts());
		} else {
			ProductCategory productCategory = productCategoryService.findById(categoryId);
			return Objects.isNull(productCategory) ? null : productCategory.getProducts();
		}
		return products;
	}

}
