package com.stanzaliving.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "LUGGAGE_CHARGE")
public class LuggageCharge {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	@JoinColumn(name = "LUGGAGE_TRANSACTION_ID")
	private LuggageTransaction luggageTransaction;

	@Column(name = "CHARGE", nullable = false)
	private String charge;

	@ManyToOne
	@JoinColumn(name = "LUGGAGE_PAYMENT_MODE_ID")
	private LuggagePaymentMode luggagePaymentMode;

	@Column(name = "IS_PAID", nullable = false)
	private Boolean isPaid;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LuggageTransaction getLuggageTransaction() {
		return luggageTransaction;
	}

	public void setLuggageTransaction(LuggageTransaction luggageTransaction) {
		this.luggageTransaction = luggageTransaction;
	}

	public String getCharge() {
		return charge;
	}

	public void setCharge(String charge) {
		this.charge = charge;
	}

	public LuggagePaymentMode getLuggagePaymentMode() {
		return luggagePaymentMode;
	}

	public void setLuggagePaymentMode(LuggagePaymentMode luggagePaymentMode) {
		this.luggagePaymentMode = luggagePaymentMode;
	}

	public Boolean getIsPaid() {
		return isPaid;
	}

	public void setIsPaid(Boolean isPaid) {
		this.isPaid = isPaid;
	}
}
