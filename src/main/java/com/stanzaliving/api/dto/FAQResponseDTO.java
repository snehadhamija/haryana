/**
 * @author nipunaggarwal
 *
 */
package com.stanzaliving.api.dto;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author nipunaggarwal
 *
 */
public class FAQResponseDTO {

	private Integer faqId;
	private String question;
	private Boolean isActive;
	private Integer sequenceId;
	@JsonProperty(value = "subDiseases", access = JsonProperty.Access.WRITE_ONLY)
	private Set<SubDiseaseResponseDTO> subDiseaseResponseDTOs;

	public Integer getFaqId() {
		return faqId;
	}

	public void setFaqId(Integer faqId) {
		this.faqId = faqId;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
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

	public Set<SubDiseaseResponseDTO> getSubDiseaseResponseDTOs() {
		return subDiseaseResponseDTOs;
	}

	public void setSubDiseaseResponseDTOs(Set<SubDiseaseResponseDTO> subDiseaseResponseDTOs) {
		this.subDiseaseResponseDTOs = subDiseaseResponseDTOs;
	}

	public FAQResponseDTO(Integer faqId, String question, Boolean isActive, Integer sequenceId, Set<SubDiseaseResponseDTO> subDiseaseResponseDTOs) {
		this.faqId = faqId;
		this.question = question;
		this.isActive = isActive;
		this.sequenceId = sequenceId;
		this.subDiseaseResponseDTOs = subDiseaseResponseDTOs;
	}

	public FAQResponseDTO() {
	}

	@Override
	public String toString() {
		return "FAQResponseDTO "
				+ "[faqId=" + faqId + ", "
				+ "question=" + question + ", "
				+ "isActive=" + isActive + ", "
				+ "sequenceId=" + sequenceId + ", "
				+ "subDiseaseResponseDTOs=" + subDiseaseResponseDTOs + "]";
	}
}
