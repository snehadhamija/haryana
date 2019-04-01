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
@Table(name = "LUGGAGE_TRANSACTION_DETAIL")
public class LuggageTransactionDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	@JoinColumn(name = "LUGGAGE_TRANSACTION_ID")
	private LuggageTransaction luggageTransaction;

	@ManyToOne
	@JoinColumn(name = "LUGGAGE_STATUS_ID")
	private LuggageStatus luggageStatus;

	@Column(name = "LUGGAGE_ID", nullable = false)
	private String luggageId;

	@Column(name = "WEIGHT", nullable = false)
	private String weight;

	@ManyToOne
	@JoinColumn(name = "LUGGAGE_CATEGORY_ID")
	private LuggageCategory luggageCategory;

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

	public String getLuggageId() {
		return luggageId;
	}

	public void setLuggageId(String luggageId) {
		this.luggageId = luggageId;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public LuggageCategory getLuggageCategory() {
		return luggageCategory;
	}

	public void setLuggageCategory(LuggageCategory luggageCategory) {
		this.luggageCategory = luggageCategory;
	}

	public LuggageStatus getLuggageStatus() {
		return luggageStatus;
	}

	public void setLuggageStatus(LuggageStatus luggageStatus) {
		this.luggageStatus = luggageStatus;
	}
}
