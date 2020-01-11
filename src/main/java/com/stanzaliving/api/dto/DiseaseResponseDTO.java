/**
 * @author nipunaggarwal
 *
 */
package com.stanzaliving.api.dto;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author nipunaggarwal
 *
 */
public class DiseaseResponseDTO {

	private Integer diseaseId;
	private String diseaseName;
	private Boolean isActive;
	private Integer sequenceId;
	private String imgurl;
	@JsonProperty(value = "productCategories", access = JsonProperty.Access.WRITE_ONLY)
	private Set<ProductCategoryResponseDTO> productCategoryResponseDTOs = new HashSet<ProductCategoryResponseDTO>();

	public Integer getDiseaseId() {
		return diseaseId;
	}

	public void setDiseaseId(Integer diseaseId) {
		this.diseaseId = diseaseId;
	}

	public String getDiseaseName() {
		return diseaseName;
	}

	public void setDiseaseName(String diseaseName) {
		this.diseaseName = diseaseName;
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

	public Set<ProductCategoryResponseDTO> getProductCategoryResponseDTOs() {
		return productCategoryResponseDTOs;
	}

	public void setProductCategoryResponseDTOs(Set<ProductCategoryResponseDTO> productCategoryResponseDTOs) {
		this.productCategoryResponseDTOs = productCategoryResponseDTOs;
	}

	public DiseaseResponseDTO(Integer diseaseId, String diseaseName, Boolean isActive, Integer sequenceId, String imgurl, Set<ProductCategoryResponseDTO> productCategoryResponseDTOs) {
		this.diseaseId = diseaseId;
		this.diseaseName = diseaseName;
		this.isActive = isActive;
		this.sequenceId = sequenceId;
		this.imgurl = imgurl;
		this.productCategoryResponseDTOs = productCategoryResponseDTOs;
	}

	public DiseaseResponseDTO() {
	}

	@Override
	public String toString() {
		return "DiseaseResponseDTO [diseaseId=" + diseaseId + ", diseaseName=" + diseaseName + ", isActive=" + isActive + ", sequenceId=" + sequenceId + ", imgurl=" + imgurl
				+ ", productCategoryResponseDTOs=" + productCategoryResponseDTOs + "]";
	}
}
