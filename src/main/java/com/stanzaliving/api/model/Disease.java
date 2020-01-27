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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * @author nipunaggarwal
 *
 */
@Entity
@Table(name = "DISEASE")
public class Disease {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "DISEASE_ID")
	private int diseaseId;

	@Column(name = "DISEASE_NAME", nullable = false)
	private String diseaseName;

	@Column(name = "IS_ACTIVE", nullable = false)
	private Boolean isActive = true;

	@Column(name = "SEQUENCE_ID", nullable = false)
	private int sequenceId = 0;

	@Column(name = "IMG_URL", nullable = true)
	private String imgurl;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "DISEASE_PRODUCT_CATEGORY", joinColumns = { @JoinColumn(name = "DISEASE_ID") }, inverseJoinColumns = {
			@JoinColumn(name = "PRODUCT_CATEGORY_ID") })
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private Set<ProductCategory> productCategories = new HashSet<ProductCategory>();

	public int getDiseaseId() {
		return diseaseId;
	}

	public void setDiseaseId(int diseaseId) {
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

	public Set<ProductCategory> getProductCategories() {
		return productCategories;
	}

	public void setProductCategories(Set<ProductCategory> productCategories) {
		this.productCategories = productCategories;
	}

	@Override
	public String toString() {
		return "Disease "
				+ "[diseaseId=" + diseaseId + ", "
				+ "diseaseName=" + diseaseName + ", "
				+ "isActive=" + isActive + ", "
				+ "sequenceId=" + sequenceId + ", "
				+ "imgurl=" + imgurl + ", "
				+ "productCategories=" + productCategories + "]";
	}
}
