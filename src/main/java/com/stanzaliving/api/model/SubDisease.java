/**
 * @author nipunaggarwal
 *
 */
package com.stanzaliving.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * @author nipunaggarwal
 *
 */
@Entity
@Table(name = "SUB_DISEASE")
public class SubDisease {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SUB_DISEASE_ID")
	private int subDiseaseId;

	@Column(name = "SUB_DISEASE_NAME", nullable = false)
	private String subDiseaseName;

	@Column(name = "IS_ACTIVE", nullable = false)
	private Boolean isActive = false;

	@Column(name = "SEQUENCE_ID", nullable = false)
	private int sequenceId = 0;

	@Column(name = "IMG_URL", nullable = true)
	private String imgurl;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "DISEASE_ID")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private Disease disease;

	public int getSubDiseaseId() {
		return subDiseaseId;
	}

	public void setSubDiseaseId(int subDiseaseId) {
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

	public int getSequenceId() {
		return sequenceId;
	}

	public void setSequenceId(int sequenceId) {
		this.sequenceId = sequenceId;
	}

	public String getImgurl() {
		return imgurl;
	}

	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}

	public Disease getDisease() {
		return disease;
	}

	public void setDisease(Disease disease) {
		this.disease = disease;
	}

}
