package com.stanzaliving.api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "LUGGAGE_LIFECYCLE")
public class LuggageLifecycle {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	@JoinColumn(name = "LUGGAGE_TRANSACTION_DETAIL_ID")
	private LuggageTransactionDetail luggageTransactionDetail;

	@ManyToOne
	@JoinColumn(name = "LUGGAGE_STATUS_ID")
	private LuggageStatus luggageStatus;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LuggageTransactionDetail getLuggageTransactionDetail() {
		return luggageTransactionDetail;
	}

	public void setLuggageTransactionDetail(LuggageTransactionDetail luggageTransactionDetail) {
		this.luggageTransactionDetail = luggageTransactionDetail;
	}

	public LuggageStatus getLuggageStatus() {
		return luggageStatus;
	}

	public void setLuggageStatus(LuggageStatus luggageStatus) {
		this.luggageStatus = luggageStatus;
	}

	@Override
	public String toString() {
		return "LuggageLifecycle [id=" + id + ", luggageTransactionDetail=" + luggageTransactionDetail
				+ ", luggageStatus=" + luggageStatus + "]";
	}

	public LuggageLifecycle(int id, LuggageTransactionDetail luggageTransactionDetail, LuggageStatus luggageStatus) {
		super();
		this.id = id;
		this.luggageTransactionDetail = luggageTransactionDetail;
		this.luggageStatus = luggageStatus;
	}

	public LuggageLifecycle() {
		super();
	}
}
