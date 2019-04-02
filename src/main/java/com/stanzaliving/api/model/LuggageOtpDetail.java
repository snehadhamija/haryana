package com.stanzaliving.api.model;

import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "LUGGAGE_OTP_DETAIL")
public class LuggageOtpDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "SENT_BY", nullable = false)
	private String sentBy;

	@Column(name = "SENT_TO", nullable = false)
	private String sentTo;

	@Column(name = "OTP", nullable = false)
	private String otp;

	@Column(name = "IS_VALIDATED")
	private Boolean isValidated = false;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_AT")
	private Date createdAt;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATED_AT")
	private Date updatedAt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "VALID_TILL", nullable = true)
	private Date validTill;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "LUGGAGE_OTP_DETAIL_LUGGAGE_TRANSACTION_DETAIL", joinColumns = {
			@JoinColumn(name = "LUGGAGE_OTP_DETAIL_ID") }, inverseJoinColumns = {
					@JoinColumn(name = "LUGGAGE_TRANSACTION_DETAIL_ID") })
	private Set<LuggageTransactionDetail> luggageTransactionDetails = new HashSet<LuggageTransactionDetail>();

	public Set<LuggageTransactionDetail> getLuggageTransactionDetails() {
		return luggageTransactionDetails;
	}

	public void setLuggageTransactionDetails(Set<LuggageTransactionDetail> luggageTransactionDetails) {
		this.luggageTransactionDetails = luggageTransactionDetails;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSentBy() {
		return sentBy;
	}

	public void setSentBy(String sentBy) {
		this.sentBy = sentBy;
	}

	public String getSentTo() {
		return sentTo;
	}

	public void setSentTo(String sentTo) {
		this.sentTo = sentTo;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public Boolean getIsValidated() {
		return isValidated;
	}

	public void setIsValidated(Boolean isValidated) {
		this.isValidated = isValidated;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Date getValidTill() {
		return validTill;
	}

	public void setValidTill(Date validTill) {
		this.validTill = validTill;
	}

	@Override
	public String toString() {
		return "LuggageOtpDetail [id=" + id + ", sentBy=" + sentBy + ", sentTo=" + sentTo + ", otp=" + otp
				+ ", isValidated=" + isValidated + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt
				+ ", validTill=" + validTill + ", luggageTransactionDetails=" + luggageTransactionDetails + "]";
	}

	public LuggageOtpDetail(int id, String sentBy, String sentTo, String otp, Boolean isValidated, Date createdAt,
			Date updatedAt, Date validTill, Set<LuggageTransactionDetail> luggageTransactionDetails) {
		super();
		this.id = id;
		this.sentBy = sentBy;
		this.sentTo = sentTo;
		this.otp = otp;
		this.isValidated = isValidated;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.validTill = validTill;
		this.luggageTransactionDetails = luggageTransactionDetails;
	}

	public LuggageOtpDetail() {
		super();
	}
}
