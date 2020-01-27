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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author nipunaggarwal
 *
 */
@Entity
@Table(name = "DISEASE")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
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

	@OneToMany(fetch = FetchType.LAZY)
	@JsonBackReference
	@JoinColumn(name = "DISEASE_ID")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private Set<SubDisease> subDiseases = new HashSet<SubDisease>();;

	@ManyToMany(fetch = FetchType.LAZY)
	@JsonManagedReference
	@JoinTable(name = "DISEASE_PRODUCT_CATEGORY", joinColumns = { @JoinColumn(name = "DISEASE_ID") }, inverseJoinColumns = {
			@JoinColumn(name = "PRODUCT_CATEGORY_ID") })
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private Set<ProductCategory> productCategories = new HashSet<ProductCategory>();

}
