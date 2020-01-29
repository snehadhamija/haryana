/**
 * @author nipunaggarwal
 *
 */
package com.vcare.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.vcare.api.Enum.ContactMode;
import com.vcare.api.Enum.Gender;

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
@Table(name = "ASK_A_QUERY")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AskAQuery {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ASK_A_QUERY_ID")
	private int askAQueryId;

	@Column(name = "FIRST_NAME", nullable = false)
	private String firstName;

	@Column(name = "LAST_NAME", nullable = true)
	private String lastName;

	@Column(name = "MOBILE_NUMBER", nullable = false)
	private String mobileNumber;

	@Column(name = "EMAIL", nullable = false)
	private String email;

	@Enumerated(EnumType.STRING)
	@Column(name = "GENDER", columnDefinition = "varchar(255) NOT NULL", nullable = false)
	private Gender gender;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FAQ_ID")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private FAQ faq;

	@Enumerated(EnumType.STRING)
	@Column(name = "CONTACT_MODE", columnDefinition = "varchar(255) NOT NULL", nullable = false)
	private ContactMode contactMode;

}
