/**
 * @author nipunaggarwal
 *
 */
package com.stanzaliving.api.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * @author nipunaggarwal
 *
 */
@Entity
@Table(name = "FAQ")
public class FAQ {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "FAQ_ID")
	private int faqId;

	@Column(name = "QUESTION", nullable = false)
	private String question;

	@Column(name = "IS_ACTIVE", nullable = false)
	private Boolean isActive = false;

	@Column(name = "SEQUENCE_ID", nullable = false)
	private int sequenceId = 0;

	@JsonBackReference
	@ManyToMany(mappedBy = "faqs", fetch = FetchType.LAZY)
	private Set<SubDisease> subDiseases = new HashSet<SubDisease>();

	public int getFaqId() {
		return faqId;
	}

	public void setFaqId(int faqId) {
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

	public int getSequenceId() {
		return sequenceId;
	}

	public void setSequenceId(int sequenceId) {
		this.sequenceId = sequenceId;
	}

	public Set<SubDisease> getSubDiseases() {
		return subDiseases;
	}

	public void setSubDiseases(Set<SubDisease> subDiseases) {
		this.subDiseases = subDiseases;
	}

	@Override
	public String toString() {
		return "FAQ "
				+ "[faqId=" + faqId + ", "
				+ "question=" + question + ", "
				+ "isActive=" + isActive + ", "
				+ "sequenceId=" + sequenceId + ", "
				+ "subDiseases=" + subDiseases + "]";
	}

}
