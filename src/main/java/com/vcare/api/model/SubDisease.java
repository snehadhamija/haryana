/**
 * @author nipunaggarwal
 *
 */
package com.vcare.api.model;

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
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

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
@Table(name = "SUB_DISEASE")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonManagedReference
	@JoinColumn(name = "DISEASE_ID")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private Disease disease;

	@ManyToMany(fetch = FetchType.LAZY)
	@JsonManagedReference
	@JoinTable(name = "SUB_DISEASE_FAQ", joinColumns = { @JoinColumn(name = "SUB_DISEASE_ID") }, inverseJoinColumns = {
			@JoinColumn(name = "FAQ_ID") })
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private Set<FAQ> faqs = new HashSet<FAQ>();

}
