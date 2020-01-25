/**
 * @author nipunaggarwal
 *
 */
package com.stanzaliving.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author nipunaggarwal
 *
 */
public class ProductVariantResponseDTO {

	private Integer productVariantId;
	private String productVariantName;
	private Boolean isActive;
	private Integer sequenceId;
	private String imgurl;
	@JsonProperty(value = "products", access = JsonProperty.Access.WRITE_ONLY)
	private ProductResponseDTO productResponseDTO;

	public Integer getProductVariantId() {
		return productVariantId;
	}

	public void setProductVariantId(Integer productVariantId) {
		this.productVariantId = productVariantId;
	}

	public String getProductVariantName() {
		return productVariantName;
	}

	public void setProductVariantName(String productVariantName) {
		this.productVariantName = productVariantName;
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

	public ProductResponseDTO getProductResponseDTO() {
		return productResponseDTO;
	}

	public void setProductResponseDTO(ProductResponseDTO productResponseDTO) {
		this.productResponseDTO = productResponseDTO;
	}

	public ProductVariantResponseDTO(Integer productVariantId, String productVariantName, Boolean isActive, Integer sequenceId, String imgurl, ProductResponseDTO productResponseDTO) {
		this.productVariantId = productVariantId;
		this.productVariantName = productVariantName;
		this.isActive = isActive;
		this.sequenceId = sequenceId;
		this.imgurl = imgurl;
		this.productResponseDTO = productResponseDTO;
	}

	public ProductVariantResponseDTO() {
	}

	@Override
	public String toString() {
		return "ProductVariantResponseDTO [productVariantId=" + productVariantId + ", productVariantName=" + productVariantName + ", isActive=" + isActive + ", sequenceId=" + sequenceId + ", imgurl="
				+ imgurl + ", productResponseDTO=" + productResponseDTO + "]";
	}

}
