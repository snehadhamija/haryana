package com.stanzaliving.api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "LUGGAGE_TRANSACTION_STATUS")
public class LuggageTransactionStatus {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	@JoinColumn(name = "LUGGAGE_TRANSACTION_ID")
	private LuggageTransaction luggageTransaction;

	@ManyToOne
	@JoinColumn(name = "LUGGAGE_ACTIVITY_STATUS_ID")
	private LuggageActivityStatus luggageActivityStatus;

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

	public LuggageActivityStatus getLuggageActivityStatus() {
		return luggageActivityStatus;
	}

	public void setLuggageActivityStatus(LuggageActivityStatus luggageActivityStatus) {
		this.luggageActivityStatus = luggageActivityStatus;
	}
}
