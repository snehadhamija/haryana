package com.stanzaliving.api.model;

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
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "LUGGAGE_TRANSACTION_STATUS")
public class LuggageTransactionStatus {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "LUGGAGE_TRANSACTION_STATUS_LUGGAGE_TRANSACTION", joinColumns = {
			@JoinColumn(name = "LUGGAGE_TRANSACTION_STATUS_ID") }, inverseJoinColumns = {
					@JoinColumn(name = "LUGGAGE_TRANSACTION_ID") })
	private Set<LuggageTransaction> luggageTransactions = new HashSet<LuggageTransaction>();

	@Column(name = "USER_MOBILE", nullable = false)
	private String userMobile;

	@ManyToOne
	@JoinColumn(name = "LUGGAGE_ACTIVITY_STATUS_ID")
	private LuggageActivityStatus luggageActivityStatus;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Set<LuggageTransaction> getLuggageTransactions() {
		return luggageTransactions;
	}

	public void setLuggageTransactions(Set<LuggageTransaction> luggageTransactions) {
		this.luggageTransactions = luggageTransactions;
	}

	public String getUserMobile() {
		return userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

	public LuggageActivityStatus getLuggageActivityStatus() {
		return luggageActivityStatus;
	}

	public void setLuggageActivityStatus(LuggageActivityStatus luggageActivityStatus) {
		this.luggageActivityStatus = luggageActivityStatus;
	}

	@Override
	public String toString() {
		return "LuggageTransactionStatus [id=" + id + ", luggageTransactions=" + luggageTransactions + ", userMobile="
				+ userMobile + ", luggageActivityStatus=" + luggageActivityStatus + "]";
	}

	public LuggageTransactionStatus(int id, Set<LuggageTransaction> luggageTransactions, String userMobile,
			LuggageActivityStatus luggageActivityStatus) {
		super();
		this.id = id;
		this.luggageTransactions = luggageTransactions;
		this.userMobile = userMobile;
		this.luggageActivityStatus = luggageActivityStatus;
	}

	public LuggageTransactionStatus() {
		super();
	}
}
