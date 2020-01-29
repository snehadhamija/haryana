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
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author nipunaggarwal
 *
 */
@Entity
@Table(name = "FAQ")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class FAQ {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "FAQ_ID")
	private int faqId;

	@Column(name = "QUESTION", nullable = false)
	@NonNull
	private String question;

	@Column(name = "IS_ACTIVE", nullable = false)
	private Boolean isActive = false;

	@Column(name = "SEQUENCE_ID", nullable = false)
	private int sequenceId = 0;

	@JsonBackReference
	@ManyToMany(mappedBy = "faqs", fetch = FetchType.LAZY)
	private Set<SubDisease> subDiseases = new HashSet<SubDisease>();

}
