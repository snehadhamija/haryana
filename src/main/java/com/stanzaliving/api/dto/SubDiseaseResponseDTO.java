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
public class SubDiseaseResponseDTO {

	private Integer subDiseaseId;
	private String subDiseaseName;
	private Boolean isActive;
	private Integer sequenceId;
	private String imgurl;
	@JsonProperty(value = "disease", access = JsonProperty.Access.WRITE_ONLY)
	private DiseaseResponseDTO diseaseResponseDTO;

	public Integer getSubDiseaseId() {
		return subDiseaseId;
	}

	public void setSubDiseaseId(Integer subDiseaseId) {
		this.subDiseaseId = subDiseaseId;
	}

	public String getSubDiseaseName() {
		return subDiseaseName;
	}

	public void setSubDiseaseName(String subDiseaseName) {
		this.subDiseaseName = subDiseaseName;
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

	public DiseaseResponseDTO getDiseaseResponseDTO() {
		return diseaseResponseDTO;
	}

	public void setDiseaseResponseDTO(DiseaseResponseDTO diseaseResponseDTO) {
		this.diseaseResponseDTO = diseaseResponseDTO;
	}

	public SubDiseaseResponseDTO(Integer subDiseaseId, String subDiseaseName, Boolean isActive, Integer sequenceId, String imgurl, DiseaseResponseDTO diseaseResponseDTO) {
		this.subDiseaseId = subDiseaseId;
		this.subDiseaseName = subDiseaseName;
		this.isActive = isActive;
		this.sequenceId = sequenceId;
		this.imgurl = imgurl;
		this.diseaseResponseDTO = diseaseResponseDTO;
	}

	public SubDiseaseResponseDTO() {
	}

	@Override
	public String toString() {
		return "SubDiseaseResponseDTO "
				+ "[subDiseaseId=" + subDiseaseId + ", "
				+ "subDiseaseName=" + subDiseaseName + ", "
				+ "isActive=" + isActive + ", "
				+ "sequenceId=" + sequenceId + ", "
				+ "imgurl=" + imgurl + ", "
				+ "disease=" + diseaseResponseDTO + "]";
	}
}
