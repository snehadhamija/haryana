/**
 * @author nipunaggarwal
 *
 */
package com.stanzaliving.api.dto;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author nipunaggarwal
 *
 */
public class ProductCategoryResponseDTO {

	private Integer productCategoryId;
	private String productCategoryName;
	private Boolean isActive;
	private Integer sequenceId;
	private String imgurl;
	@JsonProperty(value = "products", access = JsonProperty.Access.WRITE_ONLY)
	private Set<ProductResponseDTO> productResponseDtos = new HashSet<ProductResponseDTO>();

	public Integer getProductCategoryId() {
		return productCategoryId;
	}

	public void setProductCategoryId(Integer productCategoryId) {
		this.productCategoryId = productCategoryId;
	}

	public String getProductCategoryName() {
		return productCategoryName;
	}

	public void setProductCategoryName(String productCategoryName) {
		this.productCategoryName = productCategoryName;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Integer getSequenceId() {
		return sequenceId;
	}

	public void setSequenceId(Integer sequenceId) {
		this.sequenceId = sequenceId;
	}

	public String getImgurl() {
		return imgurl;
	}

	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}

	public Set<ProductResponseDTO> getProductResponseDtos() {
		return productResponseDtos;
	}

	public void setProductResponseDtos(Set<ProductResponseDTO> productResponseDtos) {
		this.productResponseDtos = productResponseDtos;
	}

	public ProductCategoryResponseDTO(Integer productCategoryId, String productCategoryName, Boolean isActive, Integer sequenceId, String imgurl, Set<ProductResponseDTO> productResponseDtos) {
		this.productCategoryId = productCategoryId;
		this.productCategoryName = productCategoryName;
		this.isActive = isActive;
		this.sequenceId = sequenceId;
		this.imgurl = imgurl;
		this.productResponseDtos = productResponseDtos;
	}

	public ProductCategoryResponseDTO() {
	}

	@Override
	public String toString() {
		return "ProductCategoryResponseDTO [productCategoryId=" + productCategoryId + ", productCategoryName=" + productCategoryName + ", isActive=" + isActive + ", sequenceId=" + sequenceId
				+ ", imgurl=" + imgurl + ", productResponseDtos=" + productResponseDtos + "]";
	}

}
