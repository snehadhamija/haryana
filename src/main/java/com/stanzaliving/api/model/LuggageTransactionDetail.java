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

	@Column(name = "LUGGAGE_ID", nullable = false)
	private String luggageId;

	@Column(name = "WEIGHT", nullable = true)
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

	@Override
	public String toString() {
		return "LuggageTransactionDetail [id=" + id + ", luggageTransaction=" + luggageTransaction + ", luggageId="
				+ luggageId + ", weight=" + weight + ", luggageCategory=" + luggageCategory + "]";
	}

	public LuggageTransactionDetail(int id, LuggageTransaction luggageTransaction, String luggageId, String weight,
			LuggageCategory luggageCategory) {
		super();
		this.id = id;
		this.luggageTransaction = luggageTransaction;
		this.luggageId = luggageId;
		this.weight = weight;
		this.luggageCategory = luggageCategory;
	}

	public LuggageTransactionDetail() {
		super();
	}
}
