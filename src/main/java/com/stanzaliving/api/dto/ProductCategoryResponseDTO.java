/**
 * @author nipunaggarwal
 *
 */
package com.stanzaliving.api.dto;

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

	public ProductCategoryResponseDTO(Integer productCategoryId, String productCategoryName, Boolean isActive, Integer sequenceId, String imgurl) {
		this.productCategoryId = productCategoryId;
		this.productCategoryName = productCategoryName;
		this.isActive = isActive;
		this.sequenceId = sequenceId;
		this.imgurl = imgurl;
	}

	public ProductCategoryResponseDTO() {
	}

	@Override
	public String toString() {
		return "ProductCategoryResponseDTO [productCategoryId=" + productCategoryId + ", productCategoryName=" + productCategoryName + ", isActive=" + isActive + ", sequenceId=" + sequenceId
				+ ", imgurl=" + imgurl + "]";
	}

}
