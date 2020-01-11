/**
 * @author nipunaggarwal
 *
 */
package com.stanzaliving.api.dto;

/**
 * @author nipunaggarwal
 *
 */
public class ProductResponseDTO {

	private Integer productId;
	private String productName;
	private Boolean isActive;
	private Integer sequenceId;
	private String imgurl;

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
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

	public ProductResponseDTO(Integer productId, String productName, Boolean isActive, Integer sequenceId, String imgurl) {
		this.productId = productId;
		this.productName = productName;
		this.isActive = isActive;
		this.sequenceId = sequenceId;
		this.imgurl = imgurl;
	}

	public ProductResponseDTO() {
	}

	@Override
	public String toString() {
		return "ProductResponseDTO [productId=" + productId + ", productName=" + productName + ", isActive=" + isActive + ", sequenceId=" + sequenceId + ", imgurl=" + imgurl + "]";
	}
}
