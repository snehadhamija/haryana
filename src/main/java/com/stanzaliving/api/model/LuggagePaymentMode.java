package com.stanzaliving.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "LUGGAGE_PAYMENT_MODE")
public class LuggagePaymentMode {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "PAYMENT_MODE_NAME", nullable = false)
	private String paymentModeName;

	@Column(name = "IS_ACTIVE", nullable = false)
	private Boolean isActive;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPaymentModeName() {
		return paymentModeName;
	}

	public void setPaymentModeName(String paymentModeName) {
		this.paymentModeName = paymentModeName;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "LuggagePaymentMode [id=" + id + ", paymentModeName=" + paymentModeName + ", isActive=" + isActive + "]";
	}

	public LuggagePaymentMode(int id, String paymentModeName, Boolean isActive) {
		super();
		this.id = id;
		this.paymentModeName = paymentModeName;
		this.isActive = isActive;
	}

	public LuggagePaymentMode() {
		super();
	}
}
