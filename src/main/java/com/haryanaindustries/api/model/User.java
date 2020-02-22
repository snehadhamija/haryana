package com.haryanaindustries.api.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "USER")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_ID")
	private int userId;

	@Column(name = "MOBILE_NUMBER", unique = true, nullable = false)
	private String mobileNumber;

	@Column(name = "PASSWORD", nullable = true)
	private String password;

	@Column(name = "FULL_NAME", nullable = false)
	private String fullName;

	@Column(name = "EMAIL", nullable = false)
	private String email;

	@Column(name = "IS_ACTIVE", nullable = false)
	private Boolean isActive = true;

	@Column(name = "GENDER", nullable = false)
	private String gender;

	@Column(name = "BLOOD_GROUP", nullable = true)
	private String bloodGroup;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_AT", updatable = false, nullable = false)
	private Date created_at;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATED_AT", updatable = true, nullable = false)
	private Date updated_at;

}
